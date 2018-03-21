package com.android.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.android.recyclerview.base.BaseRvCell;
import com.android.recyclerview.base.BaseRvViewHolder;
import com.android.recyclerview.info.Progress;

/**
 * Created by Administrator on 2018/3/20.
 */

public class SeekBarCell extends BaseRvCell<Progress> {

    public SeekBarCell(Progress p) {
        super(p);
    }

    @Override
    public int getItemType() {
        return 3;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.seekbar_layout, parent, false);
        return new BaseRvViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, int position) {
        SeekBar seekBar = (SeekBar) holder.getView(R.id.seek_bar);
        seekBar.setMax(mData.max);
        seekBar.setProgress(mData.now);
    }
}
