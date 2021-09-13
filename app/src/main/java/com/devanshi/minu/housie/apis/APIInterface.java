package com.devanshi.minu.housie.apis;

import com.devanshi.minu.housie.models.*;

import retrofit2.*;
import retrofit2.http.*;

public interface APIInterface {

    @FormUrlEncoded
    @POST(ServerConfig.LOGIN_API)
    Call<UserModel> loginApi(@Field("email") String username,
                             @Field("password") String password,
                             @Field("device_type") String device_type,
                             @Field("device_token") String device_token);

    @FormUrlEncoded
    @POST(ServerConfig.REGISTER_API)
    Call<UserModel> registerApi(@Field("name") String name,
                                @Field("email") String email,
                                @Field("password") String password,
                                @Field("mobile_no") String mobile_no,
                                @Field("connected_admin_id") String connected_admin_id,
                                @Field("referral_code") String referral_code,
                                @Field("device_token") String device_token,
                                @Field("device_type")String device_type);

    @FormUrlEncoded
    @POST(ServerConfig.FORGOT_PASSWORD_API)
    Call<UserModel> forgotPasswordApi(@Field("email") String email);

    @FormUrlEncoded
    @POST(ServerConfig.PROFILE_DETAIL_API)
    Call<UserModel> getProfileDetailApi(@Field("username") String username);

    @FormUrlEncoded
    @POST(ServerConfig.BUY_TICKET_API)
    Call<BuyTicketResponseModel> getBuyTicketsApi(@Field("game_id") Integer gameId,
                                                  @Field("user_id") String userId,
                                                  @Field("column_type") String column_type,
                                                  @Field("amount") Integer amount);

    @FormUrlEncoded
    @POST(ServerConfig.WALLET_BALANCE_API)
    Call<WalletModel> getWalletBalanceApi(@Field("user_id") String username);

    @FormUrlEncoded
    @POST(ServerConfig.CHANGE_PASSWORD_API)
    Call<UserModel> changePasswordApi(@Field("user_id") String username,
                                      @Field("current_password") String old_password,
                                      @Field("new_password") String new_password,
                                      @Field("confirm_password") String confirm_password);

    @FormUrlEncoded
    @POST(ServerConfig.UPDATE_PROFILE_API)
    Call<UserModel> updateProfileApi(
            @Field("user_id") String username,
            @Field("name") String firstName);

    @FormUrlEncoded
    @POST(ServerConfig.DASHBOARD_API)
    Call<GameListResponseModel> getDashboardApi(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST(ServerConfig.CONTEST_LIST_API)
    Call<ContestListModel> getContestListApi(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST(ServerConfig.LIST_ADMIN)
    Call<AdminResponseModel> getConnectedAdminsApi(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST(ServerConfig.JOIN_GROUP)
    Call<ContestListModel> joinGroupApi(@Field("game_id") Integer game_id,
                                        @Field("player_id") Integer user_id);
}
