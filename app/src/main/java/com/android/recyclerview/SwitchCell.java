package com.android.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.android.recyclerview.base.BaseRvCell;
import com.android.recyclerview.base.BaseRvViewHolder;

/**
 * Created by Administrator on 2018/3/20.
 */

public class SwitchCell extends BaseRvCell<com.android.recyclerview.info.Switch> {
    public SwitchCell(com.android.recyclerview.info.Switch s) {
        super(s);
    }

    @Override
    public int getItemType() {
        return 2;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.switch_layout, parent, false);
        return new BaseRvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, int position) {
        TextView text = holder.getTextView(R.id.title_text);
        Switch aSwitch = (Switch) holder.getView(R.id.a_switch);

        text.setText(mData.text);
        aSwitch.setChecked(mData.on);
    }
}
