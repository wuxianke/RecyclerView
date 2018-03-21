package com.android.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.recyclerview.base.BaseRvCell;
import com.android.recyclerview.base.BaseRvViewHolder;
import com.android.recyclerview.info.Temperature;

/**
 * Created by Administrator on 2018/3/20.
 */

public class TextCell extends BaseRvCell<Temperature> {

    public TextCell(Temperature t) {
        super(t);
    }

    @Override
    public int getItemType() {
        return 4;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.text_layout, parent, false);
        return new BaseRvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, int position) {
        TextView title = holder.getTextView(R.id.temperature_text);
        TextView temperature = holder.getTextView(R.id.temperature);

        title.setText(mData.text);
        temperature.setText(mData.now);
    }
}
