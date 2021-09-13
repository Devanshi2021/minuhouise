package com.devanshi.minu.housie.models;

import com.google.gson.annotations.*;

public class BuyTicketResponseModel {
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("data")
    @Expose
    private BuyTicketData data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public BuyTicketResponseModel withMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public BuyTicketData getData() {
        return data;
    }

    public void setData(BuyTicketData data) {
        this.data = data;
    }

    public BuyTicketResponseModel withData(BuyTicketData data) {
        this.data = data;
        return this;
    }
}
