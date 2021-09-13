
package com.devanshi.minu.housie.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.*;

public class WalletModel {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("data")
    @Expose
    private ArrayList<WalletData> data = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public WalletModel withMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public ArrayList<WalletData> getData() {
        return data;
    }

    public void setData(ArrayList<WalletData> data) {
        this.data = data;
    }

    public WalletModel withData(ArrayList<WalletData> data) {
        this.data = data;
        return this;
    }
}
