package com.cn.im.config.data;

/**
 * @ClassName DynamicDataSourceHolder
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-08 21:44
 **/
public class DynamicDataSourceHolder {

    private static final ThreadLocal<DynamicDataSourceGlobal> holder = new ThreadLocal<>();

    public static void putDataSource(DynamicDataSourceGlobal dataSource) {
        holder.set(dataSource);
    }

    public static DynamicDataSourceGlobal getDataSource() {
        return holder.get();
    }
    public static void clearDataSource() {
        holder.remove();
    }

}
