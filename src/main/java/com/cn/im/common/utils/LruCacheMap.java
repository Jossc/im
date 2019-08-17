package com.cn.im.common.utils;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 用LinkedHashMap 实现一个lru规则的缓存
 * 使用时间来做限制
 *
 * @ClassName LruCacheMap
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-13 22:03
 **/
public class LruCacheMap<K, V> {

    private LinkedHashMap<K, Element<V>> data;

    /**
     * 最大长度
     */
    private int capacity;
    /**
     * 存活时间
     */
    private long keepTime;

    /**
     * 是否使用lru
     */
    private boolean useLru;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 写锁
     */
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    /**
     * 读锁
     */
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();


    public LruCacheMap(int capacity, long keepTime, boolean useLru) {
        this.capacity = capacity;
        this.keepTime = keepTime;
        this.useLru = useLru;
        this.data = new LinkedHashMap<K, Element<V>>(capacity, 0.75f, useLru) {
            private static final long serialVersionUID = 1L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<K, Element<V>> eldest) {
                if (size() > capacity) {
                    return true;
                }
                long eleAliveTime = System.currentTimeMillis() - eldest.getValue().bornTime;
                return (eleAliveTime > keepTime);
            }
        };
    }


    /**
     * put
     * 如果有值就替换
     *
     * @param key   key
     * @param value value
     * @return
     */
    public V put(K key, V value) {
        V preValue = null;
        Element<V> newValue = new Element<>(value);
        readLock.lock();
        try {
            if (data.containsValue(key)) {
                preValue = data.get(key).value;
            }
            data.put(key, newValue);
            return preValue;
        } finally {
            readLock.unlock();
        }
    }

    /**
     * get
     * 如果使用lru，那么元素重新回到队列就有写操作
     * 那么的加上写锁
     *
     * @param key
     * @return
     */
    public V get(K key) {
        Lock lock = this.useLru ? this.writeLock : this.readLock;
        lock.lock();
        try {
            Element<V> target = data.get(key);
            return target != null ? target.value : null;
        } finally {
            lock.unlock();
        }
    }

    /**
     * size
     *
     * @return
     */
    public int size() {
        this.readLock.lock();
        try {
            return this.data.size();
        } finally {
            this.readLock.unlock();
        }
    }

    /**
     * 清除
     */
    public void clear() {
        this.writeLock.lock();
        try {
            this.data.clear();
        } finally {
            this.writeLock.unlock();
        }
    }

    class Element<V> {
        V value;
        /**
         * 元素添加时的时间戳
         */
        long bornTime;

        Element(V v) {
            this.value = v;
            this.bornTime = System.currentTimeMillis();
        }

        @Override
        public String toString() {
            return "Element [value=" + value + "]";
        }
    }


    public static void main(String[] args) throws InterruptedException {
        LruCacheMap<String ,String> lruCacheMap = new LruCacheMap<>(3,10,true);
        lruCacheMap.put("1","a");
        lruCacheMap.put("2","b");
        lruCacheMap.put("3","c");
        System.err.println(lruCacheMap.get("1"));
        Thread.currentThread().sleep(1000);


        lruCacheMap.put("4","b");
        lruCacheMap.put("5","c");
        System.err.println(lruCacheMap.size());
    }


}
