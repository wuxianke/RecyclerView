package com.android.recyclerview.info;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/3/21.
 */

public class Info {
    @SerializedName("Switch")
    public List<Switch> switchList;
    @SerializedName("Progress")
    public List<Progress> progressList;
    @SerializedName("Image")
    public List<Image> imageList;
    @SerializedName("Temperature")
    public List<Temperature> temperatureList;
}
