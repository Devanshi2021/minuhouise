
package com.devanshi.minu.housie.models;

import com.google.gson.annotations.*;

public class BuyTicketData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("player_id")
    @Expose
    private Integer playerId;
    @SerializedName("game_id")
    @Expose
    private Integer gameId;
    @SerializedName("player_name")
    @Expose
    private String playerName;
    @SerializedName("serial_index_id")
    @Expose
    private Integer serialIndexId;
    @SerializedName("column_id")
    @Expose
    private Integer columnId;
    @SerializedName("ticket_id")
    @Expose
    private Integer ticketId;
    @SerializedName("created_by_id")
    @Expose
    private Integer createdById;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("transaction_id")
    @Expose
    private Object transactionId;
    @SerializedName("game_on")
    @Expose
    private String gameOn;
    @SerializedName("payed_by")
    @Expose
    private String payedBy;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("created_by_name")
    @Expose
    private String createdByName;
    @SerializedName("alpabet_serial_index")
    @Expose
    private String alpabetSerialIndex;
    @SerializedName("ticket_column_title")
    @Expose
    private String ticketColumnTitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BuyTicketData withId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public BuyTicketData withPlayerId(Integer playerId) {
        this.playerId = playerId;
        return this;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public BuyTicketData withGameId(Integer gameId) {
        this.gameId = gameId;
        return this;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public BuyTicketData withPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public Integer getSerialIndexId() {
        return serialIndexId;
    }

    public void setSerialIndexId(Integer serialIndexId) {
        this.serialIndexId = serialIndexId;
    }

    public BuyTicketData withSerialIndexId(Integer serialIndexId) {
        this.serialIndexId = serialIndexId;
        return this;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public BuyTicketData withColumnId(Integer columnId) {
        this.columnId = columnId;
        return this;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public BuyTicketData withTicketId(Integer ticketId) {
        this.ticketId = ticketId;
        return this;
    }

    public Integer getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }

    public BuyTicketData withCreatedById(Integer createdById) {
        this.createdById = createdById;
        return this;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BuyTicketData withImage(String image) {
        this.image = image;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BuyTicketData withAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public Object getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Object transactionId) {
        this.transactionId = transactionId;
    }

    public BuyTicketData withTransactionId(Object transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getGameOn() {
        return gameOn;
    }

    public void setGameOn(String gameOn) {
        this.gameOn = gameOn;
    }

    public BuyTicketData withGameOn(String gameOn) {
        this.gameOn = gameOn;
        return this;
    }

    public String getPayedBy() {
        return payedBy;
    }

    public void setPayedBy(String payedBy) {
        this.payedBy = payedBy;
    }

    public BuyTicketData withPayedBy(String payedBy) {
        this.payedBy = payedBy;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public BuyTicketData withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BuyTicketData withUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public BuyTicketData withDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public BuyTicketData withCreatedByName(String createdByName) {
        this.createdByName = createdByName;
        return this;
    }

    public String getAlpabetSerialIndex() {
        return alpabetSerialIndex;
    }

    public void setAlpabetSerialIndex(String alpabetSerialIndex) {
        this.alpabetSerialIndex = alpabetSerialIndex;
    }

    public BuyTicketData withAlpabetSerialIndex(String alpabetSerialIndex) {
        this.alpabetSerialIndex = alpabetSerialIndex;
        return this;
    }

    public String getTicketColumnTitle() {
        return ticketColumnTitle;
    }

    public void setTicketColumnTitle(String ticketColumnTitle) {
        this.ticketColumnTitle = ticketColumnTitle;
    }

    public BuyTicketData withTicketColumnTitle(String ticketColumnTitle) {
        this.ticketColumnTitle = ticketColumnTitle;
        return this;
    }
}
