package com.hmall.item.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmall.api.dto.ItemDTO;
import com.hmall.api.dto.OrderDetailDTO;
import com.hmall.common.domain.PageDTO;
import com.hmall.common.domain.PageQuery;
import com.hmall.common.utils.BeanUtils;
import com.hmall.item.domain.po.Item;
import com.hmall.item.service.IItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品管理相關介面")
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final IItemService itemService;

    @ApiOperation("分頁查詢商品")
    @GetMapping("/page")
    public PageDTO<ItemDTO> queryItemByPage(PageQuery query) {
        // 1.分頁查詢
        Page<Item> result = itemService.page(query.toMpPage("update_time", false));
        // 2.封裝並返回
        return PageDTO.of(result, ItemDTO.class);
    }

    @ApiOperation("根據id批量查詢商品")
    @GetMapping
    public List<ItemDTO> queryItemByIds(@RequestParam("ids") List<Long> ids){
        return itemService.queryItemByIds(ids);
    }

    @ApiOperation("根據id查詢商品")
    @GetMapping("{id}")
    public ItemDTO queryItemById(@PathVariable("id") Long id) {
        return BeanUtils.copyBean(itemService.getById(id), ItemDTO.class);
    }

    @ApiOperation("新增商品")
    @PostMapping
    public void saveItem(@RequestBody ItemDTO item) {
        // 新增
        itemService.save(BeanUtils.copyBean(item, Item.class));
    }

    @ApiOperation("更新商品狀態")
    @PutMapping("/status/{id}/{status}")
    public void updateItemStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status){
        Item item = new Item();
        item.setId(id);
        item.setStatus(status);
        itemService.updateById(item);
    }

    @ApiOperation("更新商品")
    @PutMapping
    public void updateItem(@RequestBody ItemDTO item) {
        // 不允許修改商品狀態，所以強制設定為null，更新時，就會忽略該字段
        item.setStatus(null);
        // 更新
        itemService.updateById(BeanUtils.copyBean(item, Item.class));
    }

    @ApiOperation("根據id刪除商品")
    @DeleteMapping("{id}")
    public void deleteItemById(@PathVariable("id") Long id) {
        itemService.removeById(id);
    }

    @ApiOperation("批量扣減庫存")
    @PutMapping("/stock/deduct")
    public void deductStock(@RequestBody List<OrderDetailDTO> items){
        itemService.deductStock(items);
    }
}
