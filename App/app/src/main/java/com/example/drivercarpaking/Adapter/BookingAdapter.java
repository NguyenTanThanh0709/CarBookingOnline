package com.example.drivercarpaking.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivercarpaking.Activity.ListBookingActivity;
import com.example.drivercarpaking.R;
import com.example.drivercarpaking.api.API;
import com.example.drivercarpaking.models.BookingReponse;
import com.example.drivercarpaking.models.DriverTripReponDriver;
import com.example.drivercarpaking.models.SeatReponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingHolder>{
    private List<BookingReponse> list;
    private Context context;

    public BookingAdapter(List<BookingReponse> list, Context context, SharedPreferences preferences) {
        this.list = list;
        this.context = context;
        this.preferences = preferences;
    }

    private SharedPreferences preferences;

    public void setData(List<BookingReponse> newData) {
        list.clear();
        list.addAll(newData);
        notifyDataSetChanged();
    }

    public BookingAdapter(List<BookingReponse> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public BookingAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public BookingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item,parent,false);
        return  new BookingAdapter.BookingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHolder holder, int position) {
            BookingReponse bookingReponse = list.get(position);
        holder.id.setText("Mã Booking: "+bookingReponse.getId().toString());
        holder.fareAmount.setText("Số tiền: "+bookingReponse.getFareAmount());
        holder.status.setText("Trạng thái: "+bookingReponse.getStatus());
        holder.user.setText("Khách hàng: "+bookingReponse.getUser().getName() + " - " + bookingReponse.getUser().getPhone());
        holder.date.setText("Ngày book: "+bookingReponse.getDate());
        if(bookingReponse.getDescription() != null){
            holder.description.setText("Mô tả: "+bookingReponse.getDescription());
        }


        if(bookingReponse.getLocationDetailPickUp() != null){
            holder.locationDetailPickUp.setText("Điểm đón: "+bookingReponse.getLocationDetailPickUp().getDetailLocation() + " -- " + bookingReponse.getLocationDetailPickUp().getTime());
        }
        if(bookingReponse.getLocationDetailPickUp() != null){
            holder.locationDetailDropOff.setText("Điểm trả: "+bookingReponse.getLocationDetailDropOff().getDetailLocation()+ " -- " + bookingReponse.getLocationDetailPickUp().getTime());
        }

        String seat = "";
        for(SeatReponse seat1 : bookingReponse.getSeats()){
            seat += seat1.getName() + " - ";
        }
        holder.seats.setText("CHỗ ngồi: "+seat);

        String token = preferences.getString("access_token", null);


        holder.dadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                API.apiDriverTrip.updateStatus(bookingReponse.getId(),"Đã Thanh Toán","Bearer " + token).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        ((ListBookingActivity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                holder.status.setText("Đã Thanh Toán");
                                notifyDataSetChanged();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }
        });

        holder.dahuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                API.apiDriverTrip.updateStatus(bookingReponse.getId(),"Bị Hủy","Bearer " + token).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        ((ListBookingActivity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                                holder.status.setText("Bị Hủy");
                                notifyDataSetChanged();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<BookingReponse> getList() {
        return list;
    }

    public void setList(List<BookingReponse> list) {
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    class BookingHolder extends RecyclerView.ViewHolder{
        private CardView carview_booking;
        private TextView id;
        private TextView fareAmount;
        private TextView status;
        private TextView date;
        private TextView description;
        private TextView locationDetailPickUp;

        private TextView locationDetailDropOff;
        private TextView user;
        private TextView seats;
        private Button dadi;
        private Button dahuy;

        public BookingHolder(@NonNull View itemView) {
            super(itemView);
            carview_booking = itemView.findViewById(R.id.carview_booking);
            id = itemView.findViewById(R.id.id);
            fareAmount = itemView.findViewById(R.id.fareAmount);
            status = itemView.findViewById(R.id.status);
            date = itemView.findViewById(R.id.date);
            description = itemView.findViewById(R.id.description);
            locationDetailPickUp = itemView.findViewById(R.id.locationDetailPickUp);
            locationDetailDropOff = itemView.findViewById(R.id.locationDetailDropOff);
            seats = itemView.findViewById(R.id.seats);
            dadi = itemView.findViewById(R.id.dadi);
            dahuy = itemView.findViewById(R.id.dahuy);
            user = itemView.findViewById(R.id.user);
        }
    }
}
