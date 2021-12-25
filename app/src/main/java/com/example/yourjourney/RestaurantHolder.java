package com.example.yourjourney;

import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class RestaurantHolder extends RecyclerView.Adapter<RestaurantHolder.MyViewHolder>  {

    Context context;
    ArrayList<Restaurant> restaurants;


    public RestaurantHolder(Context context, ArrayList<Restaurant> restaurants) {
        this.context = context;
        this.restaurants = restaurants;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.place, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Restaurant r = restaurants.get(position);
        holder.rTitle.setText(r.getTitle());
        Picasso.get().load(r.getImage()).into(holder.rImage);
        holder.rButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String googleMapsLink = r.getMaps();
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(googleMapsLink));
                context.startActivity(viewIntent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView rTitle ;
        ImageView rImage ;
        Button rButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rTitle = itemView.findViewById(R.id.rTextview);
            rImage = itemView.findViewById(R.id.rImageView);
            rButton = itemView.findViewById(R.id.rButtonView);

        }
    }






    public void setDetails(Context context, String image, String maps, String phone, String title){
        //TextView rTitle = view.findViewById(R.id.rTextview);
        //ImageView rImage = view.findViewById(R.id.rImageView);

        //rTitle.setText(title);
        //Picasso.get().load(image).into(rImage);
    }
}
