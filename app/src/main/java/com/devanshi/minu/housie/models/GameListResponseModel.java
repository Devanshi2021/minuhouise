package com.devanshi.minu.housie.models;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameListResponseModel {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("data")
    @Expose
    private ArrayList<GameData> data = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public GameListResponseModel withMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public ArrayList<GameData> getData() {
        return data;
    }

    public void setData(ArrayList<GameData> data) {
        this.data = data;
    }

    public GameListResponseModel withData(ArrayList<GameData> data) {
        this.data = data;
        return this;
    }
}
