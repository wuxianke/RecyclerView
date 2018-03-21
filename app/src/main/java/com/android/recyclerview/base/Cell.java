package com.android.recyclerview.base;

import android.view.ViewGroup;

/**
 *
 * @author zhouwei
 * @date 17/1/19
 */

public interface Cell {
    /**
     * 回收资源
     *
     */
    public void releaseResource();

    /**
     * 获取viewType
     * @return
     */
    public  int getItemType();

    /**
     * 创建ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 数据绑定
     * @param holder
     * @param position
     */
    public  void onBindViewHolder(BaseRvViewHolder holder, int position);
}
