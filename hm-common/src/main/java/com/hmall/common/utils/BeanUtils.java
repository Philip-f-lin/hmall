package com.hmall.common.utils;

import cn.hutool.core.bean.BeanUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 繼承自 hutool 的BeanUtil，增加了bean轉換時自訂轉換器的功能
 */
public class BeanUtils extends BeanUtil {

    /**
     * 將原始對象轉換成目標對象，對於欄位不匹配的欄位可以使用轉換器處理
     *
     * @param source  原對象
     * @param clazz   目標物件的class
     * @param convert 轉換器
     * @param <R>     原物件類型
     * @param <T>     目標物件類型
     * @return 目標對象
     */
    public static <R, T> T copyBean(R source, Class<T> clazz, Convert<R, T> convert) {
        T target = copyBean(source, clazz);
        if (convert != null) {
            convert.convert(source, target);
        }
        return target;
    }
    /**
     * 將原始對象轉換成目標對象，對於欄位不匹配的欄位可以使用轉換器處理
     *
     * @param source  原對象
     * @param clazz   目標物件的class
     * @param <R>     原物件類型
     * @param <T>     目標物件類型
     * @return 目標對象
     */
    public static <R, T> T copyBean(R source, Class<T> clazz){
        if (source == null) {
            return null;
        }
        return toBean(source, clazz);
    }

    public static <R, T> List<T> copyList(List<R> list, Class<T> clazz) {
        if (list == null || list.size() == 0) {
            return CollUtils.emptyList();
        }
        return copyToList(list, clazz);
    }

    public static <R, T> List<T> copyList(List<R> list, Class<T> clazz, Convert<R, T> convert) {
        if (list == null || list.size() == 0) {
            return CollUtils.emptyList();
        }
        return list.stream().map(r -> copyBean(r, clazz, convert)).collect(Collectors.toList());
    }
}