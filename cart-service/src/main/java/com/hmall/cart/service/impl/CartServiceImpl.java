package com.hmall.cart.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.cart.domain.dto.CartFormDTO;
import com.hmall.cart.domain.dto.ItemDTO;
import com.hmall.cart.domain.po.Cart;
import com.hmall.cart.domain.vo.CartVO;
import com.hmall.cart.mapper.CartMapper;
import com.hmall.cart.service.ICartService;
import com.hmall.common.exception.BizIllegalException;
import com.hmall.common.utils.BeanUtils;
import com.hmall.common.utils.CollUtils;
import com.hmall.common.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

    private final RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    @Override
    public void addItem2Cart(CartFormDTO cartFormDTO) {
        // 1.取得登入用戶
        Long userId = UserContext.getUser();

        // 2.判斷是否已經存在
        if(checkItemExists(cartFormDTO.getItemId(), userId)){
            // 2.1.存在，則更新數量
            baseMapper.updateNum(cartFormDTO.getItemId(), userId);
            return;
        }
        // 2.2.不存在，判斷是否超過購物車數量
        checkCartsFull(userId);

        // 3.新增購物車條目
        // 3.1.轉換PO
        Cart cart = BeanUtils.copyBean(cartFormDTO, Cart.class);
        // 3.2.儲存目前用戶
        cart.setUserId(userId);
        // 3.3.儲存到資料庫
        save(cart);
    }

    @Override
    public List<CartVO> queryMyCarts() {
        // 1.查詢我的購物車列表
        List<Cart> carts = lambdaQuery().eq(Cart::getUserId, 1L/* TODO UserContext.getUser()*/).list();
        if (CollUtils.isEmpty(carts)) {
            return CollUtils.emptyList();
        }

        // 2.轉換VO
        List<CartVO> vos = BeanUtils.copyList(carts, CartVO.class);

        // 3.處理VO中的商品訊息
        handleCartItems(vos);

        // 4.返回
        return vos;
    }

    private void handleCartItems(List<CartVO> vos) {
        // TODO 1.取得商品id
        Set<Long> itemIds = vos.stream().map(CartVO::getItemId).collect(Collectors.toSet());
        // 2.查詢商品
        // 2.1 根據服務名稱獲取服務的實例列表
        List<ServiceInstance> instances = discoveryClient.getInstances("item-service");
        if (CollUtil.isEmpty(instances)){
            return;
        }
        // 2.2 手寫負載均衡，從實力列表中挑選一個實例
        ServiceInstance instance = instances.get(RandomUtil.randomInt(instances.size()));
        //List<ItemDTO> items = itemService.queryItemByIds(itemIds);
        // 2.1 利用 RestTemplate 發起 http 請求，得到 http 的響應
        ResponseEntity<List<ItemDTO>> response = restTemplate.exchange(
                instance.getUri() + "/items?ids={ids}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ItemDTO>>() {
                },
                Map.of("ids", CollUtil.join(itemIds, ","))
        );
        // 2.2 解析響應
        if (!response.getStatusCode().is2xxSuccessful()){
            // 查詢失敗，直接結束
            return;
        }
        List<ItemDTO> items = response.getBody();
        if (CollUtils.isEmpty(items)) {
            return;
        }
        // 3.轉為 id 到 item的map
        Map<Long, ItemDTO> itemMap = items.stream().collect(Collectors.toMap(ItemDTO::getId, Function.identity()));
        // 4.寫入vo
        for (CartVO v : vos) {
            ItemDTO item = itemMap.get(v.getItemId());
            if (item == null) {
                continue;
            }
            v.setNewPrice(item.getPrice());
            v.setStatus(item.getStatus());
            v.setStock(item.getStock());
        }
    }

    @Override
    public void removeByItemIds(Collection<Long> itemIds) {
        // 1.建構刪除條件，userId和itemId
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>();
        queryWrapper.lambda()
                .eq(Cart::getUserId, UserContext.getUser())
                .in(Cart::getItemId, itemIds);
        // 2.刪除
        remove(queryWrapper);
    }

    private void checkCartsFull(Long userId) {
        int count = lambdaQuery().eq(Cart::getUserId, userId).count();
        if (count >= 10) {
            throw new BizIllegalException(StrUtil.format("用戶購物車課程不能超過{}", 10));
        }
    }

    private boolean checkItemExists(Long itemId, Long userId) {
        int count = lambdaQuery()
                .eq(Cart::getUserId, userId)
                .eq(Cart::getItemId, itemId)
                .count();
        return count > 0;
    }
}
