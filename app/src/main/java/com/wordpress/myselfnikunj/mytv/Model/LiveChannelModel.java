package com.wordpress.myselfnikunj.mytv.Model;

import android.content.Context;

public class LiveChannelModel {
    private Context context;
    private String name, imageUrl, channelLink;

    public LiveChannelModel() {
    }

    public LiveChannelModel(Context context, String name, String imageUrl, String channelLink) {
        this.context = context;
        this.name = name;
        this.imageUrl = imageUrl;
        this.channelLink = channelLink;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getChannelLink() {
        return channelLink;
    }

    public void setChannelLink(String channelLink) {
        this.channelLink = channelLink;
    }
}
