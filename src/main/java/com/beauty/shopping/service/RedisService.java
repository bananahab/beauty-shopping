package com.beauty.shopping.service;

/**
 * @author wuzhenxian
 * @date 2021/03/03
 */
public interface RedisService {
    /**
     * 存储数据
     * @param key 键
     * @param value 值
     */
    void set(String key, String value);

    /**
     * 获取数据
     * @param key 键
     * @return 值
     */
    String get(String key);

    /**
     * 删除数据
     * @param key 键
     */
    void remove(String key);

    /**
     * 设置超期时间
     * @param key 键
     * @param expire 超期时间
     * @return 结果
     */
    boolean expire(String key, long expire);

    /**
     * 自增操作
     * @param key 键
     * @param delta 自增步长
     * @return 结果
     */
    Long increment(String key, long delta);
}
