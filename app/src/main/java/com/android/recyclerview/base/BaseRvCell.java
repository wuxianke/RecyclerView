package com.android.recyclerview.base;

/**
 *
 * @author zhouwei
 * @date 17/1/19
 */

public  abstract class BaseRvCell<T> implements Cell {

    public BaseRvCell(T t){
        mData = t;
    }
    public T mData;

    @Override
    public void releaseResource() {
        // do nothing
        // 如果有需要回收的资源，子类自己实现
    }
}
