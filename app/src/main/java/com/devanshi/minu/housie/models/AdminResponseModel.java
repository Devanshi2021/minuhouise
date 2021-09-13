package com.devanshi.minu.housie.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.*;

public class AdminResponseModel {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("data")
    @Expose
    private ArrayList<AdminData> data = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public AdminResponseModel withMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public ArrayList<AdminData> getData() {
        return data;
    }

    public void setData(ArrayList<AdminData> data) {
        this.data = data;
    }

    public AdminResponseModel withData(ArrayList<AdminData> data) {
        this.data = data;
        return this;
    }

}
