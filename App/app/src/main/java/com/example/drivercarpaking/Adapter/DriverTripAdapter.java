package com.example.drivercarpaking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivercarpaking.Activity.ListBookingActivity;
import com.example.drivercarpaking.R;
import com.example.drivercarpaking.models.DriverTripReponDriver;

import java.util.ArrayList;
import java.util.List;

public class DriverTripAdapter extends RecyclerView.Adapter<DriverTripAdapter.DriverTripHolder>{
    private List<DriverTripReponDriver> list;
    private Context context;

    public void setData(List<DriverTripReponDriver> newData) {
        list.clear();
        list.addAll(newData);
        notifyDataSetChanged();
    }

    public DriverTripAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DriverTripHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drivertrip_item,parent,false);
        return  new DriverTripAdapter.DriverTripHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverTripHolder holder, int position) {
        DriverTripReponDriver driverTripReponDriver = list.get(position);

        holder.iddrivertrip.setText("Mã chuyến: "+driverTripReponDriver.getId().toString());
        holder.datedrivertrip.setText("Ngày đi: "+driverTripReponDriver.getDate().toString());

        holder.rout.setText("Tuyến đường: "+driverTripReponDriver.getPickupLocation() + " -- " + driverTripReponDriver.getDropoffLocation());
        holder.hour.setText("Giờ đi: " + driverTripReponDriver.getPickupTime() + " -- " + driverTripReponDriver.getDropoffTime());
        holder.car.setText("Xe: " + driverTripReponDriver.getNameCar() + " -- " + driverTripReponDriver.getLicenseplates());

        holder.imageView_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Long driverTripId = driverTripReponDriver.getId();

                // Create Intent and add the driver trip ID as an extra
                Intent intent = new Intent(context, ListBookingActivity.class);
                intent.putExtra("DRIVER_TRIP_ID", driverTripId);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public DriverTripAdapter(List<DriverTripReponDriver> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public List<DriverTripReponDriver> getList() {
        return list;
    }

    public void setList(List<DriverTripReponDriver> list) {
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    class DriverTripHolder extends RecyclerView.ViewHolder{

        private CardView carview_driverTrip;
        private TextView iddrivertrip;
        private TextView datedrivertrip;
        private TextView rout;
        private TextView hour;
        private TextView car;

        private ImageView imageView_more;

        public DriverTripHolder(@NonNull View itemView) {
            super(itemView);
            carview_driverTrip = itemView.findViewById(R.id.carview_driverTrip);

            iddrivertrip = itemView.findViewById(R.id.iddrivertrip);
            datedrivertrip = itemView.findViewById(R.id.datedrivertrip);
            rout = itemView.findViewById(R.id.rout);
            hour = itemView.findViewById(R.id.hour);
            car = itemView.findViewById(R.id.car);
            imageView_more = itemView.findViewById(R.id.imageView_more);
        }
    }
}
