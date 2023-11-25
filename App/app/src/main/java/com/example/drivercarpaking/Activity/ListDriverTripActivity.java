package com.example.drivercarpaking.Activity;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.drivercarpaking.Adapter.DriverTripAdapter;
import com.example.drivercarpaking.R;
import com.example.drivercarpaking.api.API;
import com.example.drivercarpaking.models.DriverTripReponDriver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDriverTripActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DriverTripAdapter driverTripAdapter;
    private List<DriverTripReponDriver> driverTripReponDrivers;


    private EditText selectedDateEditText;
    private Button findStudentButton;
    List<DriverTripReponDriver> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_driver_trip);

        recyclerView = findViewById(R.id.listdrivertrip_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        driverTripReponDrivers = new ArrayList<>();
        driverTripAdapter = new DriverTripAdapter(driverTripReponDrivers, this);
        recyclerView.setAdapter(driverTripAdapter);

        Intent intent = getIntent();
        if (intent != null) {
            String accessToken = intent.getStringExtra("ACCESS_TOKEN");
            String phoneNumber = intent.getStringExtra("PHONE_NUMBER");

            // Use accessToken and phoneNumber as needed
            // For example, you can include them in your API call
            getData(accessToken, phoneNumber);
        }

        selectedDateEditText = findViewById(R.id.selectedDateEditText);
        findStudentButton = findViewById(R.id.find_student);

        findStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                driverTripAdapter.setData(driverTripReponDrivers);
            }
        });

        selectedDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });




        //getData();
    }

    private boolean compareDates(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Format both dates to strings
        String dateString1 = sdf.format(date1);
        String dateString2 = sdf.format(date2);

        // Compare the strings
        return dateString1.equals(dateString2);
    }

    private void showDatePicker() {
        // Get the current date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create and show a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Update the selected date in the EditText
                        Calendar selectedDateCalendar = Calendar.getInstance();
                        selectedDateCalendar.set(year, monthOfYear, dayOfMonth);
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        selectedDateEditText.setText(selectedDate);
                        list.clear();
                        for(DriverTripReponDriver driverTripReponDriver : driverTripReponDrivers){
                            if(compareDates(selectedDateCalendar.getTime(), driverTripReponDriver.getDate())){
                                list.add(driverTripReponDriver);
                                driverTripAdapter.setData(list);
                            }

                        }
                    }
                }, year, month, day);

        // Show the date picker dialog
        datePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_more, menu);
        return true;
    }

    private  void getData(String accessToken, String phoneNumber){
        API.apiDriverTrip.getListDriverTrip(phoneNumber, "Bearer " + accessToken).enqueue(new Callback<List<DriverTripReponDriver>>() {
            @Override
            public void onResponse(Call<List<DriverTripReponDriver>> call, Response<List<DriverTripReponDriver>> response) {
                driverTripReponDrivers = response.body();
                driverTripAdapter.setData(driverTripReponDrivers);
            }

            @Override
            public void onFailure(Call<List<DriverTripReponDriver>> call, Throwable t) {
                Log.e("API Call", "Failed: " + t.getMessage());
                Toast.makeText(ListDriverTripActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Toast.makeText(ListDriverTripActivity.this, "Error",Toast.LENGTH_SHORT).show();
            }
        });
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
            Intent intent = new Intent(ListDriverTripActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
            
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}