package com.hmall.common.utils;

public class UserContext {
    private static final ThreadLocal<Long> tl = new ThreadLocal<>();

    /**
     * 儲存目前登入使用者資訊到ThreadLocal
     * @param userId 使用者id
     */
    public static void setUser(Long userId) {
        tl.set(userId);
    }

    /**
     * 取得目前登入使用者資訊
     * @return 使用者id
     */
    public static Long getUser() {
        return tl.get();
    }

    /**
     * 移除目前登入使用者訊息
     */
    public static void removeUser(){
        tl.remove();
    }
}
