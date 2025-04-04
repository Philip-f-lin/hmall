package com.hmall.common.utils;

/**
 * 對原物件進行計算，設定到目標物件中
 **/
public interface Convert<R,T>{
    void convert(R origin, T target);
}