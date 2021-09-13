package com.devanshi.minu.housie.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContestList {

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
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("joined_game_status")
    @Expose
    private Integer joinedGameStatus;
    @SerializedName("player_id")
    @Expose
    private Integer playerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ContestList withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ContestList withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ContestList withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert;
    }

    public ContestList withExpert(String expert) {
        this.expert = expert;
        return this;
    }

    public Integer getSerialIndexId() {
        return serialIndexId;
    }

    public void setSerialIndexId(Integer serialIndexId) {
        this.serialIndexId = serialIndexId;
    }

    public ContestList withSerialIndexId(Integer serialIndexId) {
        this.serialIndexId = serialIndexId;
        return this;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public ContestList withGameDate(String gameDate) {
        this.gameDate = gameDate;
        return this;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public ContestList withGameTime(String gameTime) {
        this.gameTime = gameTime;
        return this;
    }

    public Integer getSixColumnAmount() {
        return sixColumnAmount;
    }

    public void setSixColumnAmount(Integer sixColumnAmount) {
        this.sixColumnAmount = sixColumnAmount;
    }

    public ContestList withSixColumnAmount(Integer sixColumnAmount) {
        this.sixColumnAmount = sixColumnAmount;
        return this;
    }

    public Integer getTwelveColumnAmount() {
        return twelveColumnAmount;
    }

    public void setTwelveColumnAmount(Integer twelveColumnAmount) {
        this.twelveColumnAmount = twelveColumnAmount;
    }

    public ContestList withTwelveColumnAmount(Integer twelveColumnAmount) {
        this.twelveColumnAmount = twelveColumnAmount;
        return this;
    }

    public Integer getEighteenColumnAmount() {
        return eighteenColumnAmount;
    }

    public void setEighteenColumnAmount(Integer eighteenColumnAmount) {
        this.eighteenColumnAmount = eighteenColumnAmount;
    }

    public ContestList withEighteenColumnAmount(Integer eighteenColumnAmount) {
        this.eighteenColumnAmount = eighteenColumnAmount;
        return this;
    }

    public Integer getTwentyfourColumnAmount() {
        return twentyfourColumnAmount;
    }

    public void setTwentyfourColumnAmount(Integer twentyfourColumnAmount) {
        this.twentyfourColumnAmount = twentyfourColumnAmount;
    }

    public ContestList withTwentyfourColumnAmount(Integer twentyfourColumnAmount) {
        this.twentyfourColumnAmount = twentyfourColumnAmount;
        return this;
    }

    public String getWhatsappGroupUrl() {
        return whatsappGroupUrl;
    }

    public void setWhatsappGroupUrl(String whatsappGroupUrl) {
        this.whatsappGroupUrl = whatsappGroupUrl;
    }

    public ContestList withWhatsappGroupUrl(String whatsappGroupUrl) {
        this.whatsappGroupUrl = whatsappGroupUrl;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public ContestList withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ContestList withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public ContestList withDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ContestList withImage(String image) {
        this.image = image;
        return this;
    }

    public Integer getJoinedGameStatus() {
        return joinedGameStatus;
    }

    public void setJoinedGameStatus(Integer joinedGameStatus) {
        this.joinedGameStatus = joinedGameStatus;
    }

    public ContestList withJoinedGameStatus(Integer joinedGameStatus) {
        this.joinedGameStatus = joinedGameStatus;
        return this;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public ContestList withPlayerId(Integer playerId) {
        this.playerId = playerId;
        return this;
    }
}
