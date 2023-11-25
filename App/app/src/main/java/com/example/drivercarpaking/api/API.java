package com.example.drivercarpaking.api;

import com.example.drivercarpaking.models.AuthResponse;
import com.example.drivercarpaking.models.BookingReponse;
import com.example.drivercarpaking.models.DriverTripReponDriver;
import com.example.drivercarpaking.models.LoginRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)  // Set your desired connection timeout duration here
            .build();
    API apiDriverTrip = new Retrofit.Builder()

            .baseUrl("http://192.168.137.226:8085/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(API.class);

    @POST("api/v1/auth/authenticate")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);

    @GET("api/v1/driver/listdriver/{phone}")
    Call<List<DriverTripReponDriver>> getListDriverTrip(@Path("phone") String phone,  @Header("Authorization") String token);

    @GET("api/v1/user/booking/trip")
    Call<List<BookingReponse>> getListBooking(@Query("iddrivertrip") Long iddrivertrip,  @Header("Authorization") String token);

    @PATCH("/api/v1/staff/booking/{id}/status")
    Call<String> updateStatus(
            @Path("id") Long id,
            @Query("status") String status,
            @Header("Authorization") String token
    );



}
