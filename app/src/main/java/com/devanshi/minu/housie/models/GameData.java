
package com.devanshi.minu.housie.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.*;

public class GameData {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("expert")
    @Expose
    private String expert;
    @SerializedName("serial_index_id")
    @Expose
    private Integer serialIndexId;
    @SerializedName("game_date")
    @Expose
    private String gameDate;
    @SerializedName("game_time")
    @Expose
    private String gameTime;
    @SerializedName("six_column_amount")
    @Expose
    private Integer sixColumnAmount;
    @SerializedName("twelve_column_amount")
    @Expose
    private Integer twelveColumnAmount;
    @SerializedName("eighteen_column_amount")
    @Expose
    private Integer eighteenColumnAmount;
    @SerializedName("twentyfour_column_amount")
    @Expose
    private Integer twentyfourColumnAmount;
    @SerializedName("whatsapp_group_url")
    @Expose
    private String whatsappGroupUrl;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GameData withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GameData withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GameData withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert;
    }

    public GameData withExpert(String expert) {
        this.expert = expert;
        return this;
    }

    public Integer getSerialIndexId() {
        return serialIndexId;
    }

    public void setSerialIndexId(Integer serialIndexId) {
        this.serialIndexId = serialIndexId;
    }

    public GameData withSerialIndexId(Integer serialIndexId) {
        this.serialIndexId = serialIndexId;
        return this;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public GameData withGameDate(String gameDate) {
        this.gameDate = gameDate;
        return this;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public GameData withGameTime(String gameTime) {
        this.gameTime = gameTime;
        return this;
    }

    public Integer getSixColumnAmount() {
        return sixColumnAmount;
    }

    public void setSixColumnAmount(Integer sixColumnAmount) {
        this.sixColumnAmount = sixColumnAmount;
    }

    public GameData withSixColumnAmount(Integer sixColumnAmount) {
        this.sixColumnAmount = sixColumnAmount;
        return this;
    }

    public Integer getTwelveColumnAmount() {
        return twelveColumnAmount;
    }

    public void setTwelveColumnAmount(Integer twelveColumnAmount) {
        this.twelveColumnAmount = twelveColumnAmount;
    }

    public GameData withTwelveColumnAmount(Integer twelveColumnAmount) {
        this.twelveColumnAmount = twelveColumnAmount;
        return this;
    }

    public Integer getEighteenColumnAmount() {
        return eighteenColumnAmount;
    }

    public void setEighteenColumnAmount(Integer eighteenColumnAmount) {
        this.eighteenColumnAmount = eighteenColumnAmount;
    }

    public GameData withEighteenColumnAmount(Integer eighteenColumnAmount) {
        this.eighteenColumnAmount = eighteenColumnAmount;
        return this;
    }

    public Integer getTwentyfourColumnAmount() {
        return twentyfourColumnAmount;
    }

    public void setTwentyfourColumnAmount(Integer twentyfourColumnAmount) {
        this.twentyfourColumnAmount = twentyfourColumnAmount;
    }

    public GameData withTwentyfourColumnAmount(Integer twentyfourColumnAmount) {
        this.twentyfourColumnAmount = twentyfourColumnAmount;
        return this;
    }

    public String getWhatsappGroupUrl() {
        return whatsappGroupUrl;
    }

    public void setWhatsappGroupUrl(String whatsappGroupUrl) {
        this.whatsappGroupUrl = whatsappGroupUrl;
    }

    public GameData withWhatsappGroupUrl(String whatsappGroupUrl) {
        this.whatsappGroupUrl = whatsappGroupUrl;
        return this;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public GameData withCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public GameData withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public GameData withDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    @NotNull
    public String toString() {
        return this.getClass().getSimpleName() +
                " {\"id\":\"" + id + "\"," +
                " \"game_title\":\"" + title + "\"," +
                " \"description:\"" + description + "\"," +
                " \"expert:\"" + expert + "\"," +
                " \"serialIndexId:\"" + serialIndexId + "\"," +
                " \"gameDate:\"" + gameDate + "\"," +
                " \"gameTime:\"" + gameTime + "\"," +
                " \"sixColumnAmount:\"" + sixColumnAmount + "\"," +
                " \"twelveColumnAmount:\"" + twelveColumnAmount + "\"," +
                " \"eighteenColumnAmount:\"" + eighteenColumnAmount + "\"," +
                " \"twentyfourColumnAmount:\"" + twentyfourColumnAmount + "\"," +
                " \"whatsappGroupUrl:\"" + whatsappGroupUrl + "\","+
                " \"createdAt:\"" + createdAt + "\"}";
    }
}
