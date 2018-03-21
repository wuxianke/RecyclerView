package com.android.recyclerview;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.recyclerview.base.BaseRvCell;
import com.android.recyclerview.base.BaseRvViewHolder;
import com.android.recyclerview.info.Image;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;

import java.io.IOException;
import java.net.URI;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/20.
 */

public class ImageCell extends BaseRvCell<Image> {
    private static final String TAG = "ImageCell";

    public ImageCell(Image i) {
        super(i);

    }

    @Override
    public int getItemType() {
        return 1;
    }

    @Override
    public BaseRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.image_layout, parent, false);
        return new BaseRvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRvViewHolder holder, int position) {
        ImageView imageView = holder.getImageView(R.id.image);
        Glide.with(imageView.getContext())
                .load(mData.url)
                .into(imageView);
    }
}
