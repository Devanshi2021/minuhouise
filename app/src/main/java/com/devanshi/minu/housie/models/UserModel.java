
package com.devanshi.minu.housie.models;

import com.google.gson.annotations.*;

public class UserModel {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("data")
    @Expose
    private UserData data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public UserModel withMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public UserModel withData(UserData data) {
        this.data = data;
        return this;
    }

}
