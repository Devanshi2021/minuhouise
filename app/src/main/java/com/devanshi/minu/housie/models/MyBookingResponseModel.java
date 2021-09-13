package com.devanshi.minu.housie.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.*;

public class MyBookingResponseModel {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("data")
    @Expose
    private ArrayList<ContestList> data = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public MyBookingResponseModel withMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public ArrayList<ContestList> getData() {
        return data;
    }

    public void setData(ArrayList<ContestList> data) {
        this.data = data;
    }

    public MyBookingResponseModel withData(ArrayList<ContestList> data) {
        this.data = data;
        return this;
    }

}
