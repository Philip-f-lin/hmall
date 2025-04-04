package com.hmall.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.NumberUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 繼承自 hutool 的集合工具類
 */
public class CollUtils extends CollectionUtil {

    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }

    public static <T> Set<T> emptySet() {
        return Collections.emptySet();
    }

    public static <K,V> Map<K, V> emptyMap() {
        return Collections.emptyMap();
    }

    public static <T> Set<T> singletonSet(T t) {
        return Collections.singleton(t);
    }

    public static <T> List<T> singletonList(T t) {
        return Collections.singletonList(t);
    }

    public static List<Integer> convertToInteger(List<String> originList){
        return CollUtils.isNotEmpty(originList) ? originList.stream().map(NumberUtil::parseInt).collect(Collectors.toList()) : null;
    }

    public static List<Long> convertToLong(List<String> originLIst){
        return CollUtils.isNotEmpty(originLIst) ? originLIst.stream().map(NumberUtil::parseLong).collect(Collectors.toList()) : null;
    }

    /**
     * 以 conjunction 為分隔符號將集合轉換為字串 如果集合元素為陣列、Iterable或Iterator，則遞歸組合其為字串
     * @param collection 集合
     * @param conjunction 分隔符
     * @param <T> 集合元素類型
     * @return 連接後的字串
     * See Also: IterUtil.join(Iterator, CharSequence)
     */
    public static <T> String join(Collection<T> collection, CharSequence conjunction) {
        if (null == collection || collection.isEmpty()) {
            return null;
        }
        return IterUtil.join(collection.iterator(), conjunction);
    }

    public static <T> String joinIgnoreNull(Collection<T> collection, CharSequence conjunction) {
        if (null == collection || collection.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (T t : collection) {
            if(t == null) continue;
            sb.append(t).append(",");
        }
        if(sb.length() <= 0){
            return null;
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}