package com.example.drivercarpaking.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.drivercarpaking.Adapter.BookingAdapter;
import com.example.drivercarpaking.Adapter.DriverTripAdapter;
import com.example.drivercarpaking.R;
import com.example.drivercarpaking.api.API;
import com.example.drivercarpaking.models.BookingReponse;
import com.example.drivercarpaking.models.DriverTripReponDriver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBookingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookingAdapter bookingAdapter;
    private List<BookingReponse> bookingReponses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_booking);

        recyclerView = findViewById(R.id.listBooking_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookingReponses = new ArrayList<>();
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        bookingAdapter = new BookingAdapter(bookingReponses, this, preferences);
        recyclerView.setAdapter(bookingAdapter);

        Intent intent = getIntent();
        if (intent != null) {
            // Use getLongExtra to retrieve Long values
            Long driverTripId = intent.getLongExtra("DRIVER_TRIP_ID", -1);


            String token = preferences.getString("access_token", null);

            if (driverTripId != -1 && token != null) {
                getData(driverTripId,token);
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private  void getData(Long id, String token){
        API.apiDriverTrip.getListBooking(id, "Bearer " + token)
                .enqueue(new Callback<List<BookingReponse>>() {
                    @Override
                    public void onResponse(Call<List<BookingReponse>> call, Response<List<BookingReponse>> response) {
                        bookingReponses =  response.body();
                        Log.d("Response Body", response.body().toString());
                        bookingAdapter.setData(bookingReponses);
                        Toast.makeText(ListBookingActivity.this,"OK", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<List<BookingReponse>> call, Throwable t) {
                        Log.e("API Call", "Failed: " + t.getMessage(), t);
                        Toast.makeText(ListBookingActivity.this,"ERR", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_more, menu);
        return true;
    }

    private void removeAuthData() {
        // Get SharedPreferences instance
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        // Get SharedPreferences editor
        SharedPreferences.Editor editor = preferences.edit();

        // Remove the access token and phone number
        editor.remove("access_token");
        editor.remove("phone_number");

        // Apply changes
        editor.apply();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_back) {

            finish();

            return true;
        } else if (item.getItemId() == R.id.log_out) {
            removeAuthData();
            Intent intent = new Intent(ListBookingActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}