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


public class PlacesHolder extends RecyclerView.Adapter<PlacesHolder.MyViewHolder>  {

    Context context;
    ArrayList<Place> places;


    public PlacesHolder(Context context, ArrayList<Place> places) {
        this.context = context;
        this.places = places;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.place, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Place place = places.get(position);
        holder.rTitle.setText(place.getTitle());
        Picasso.get().load(place.getImage()).into(holder.rImage);
        holder.rButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String googleMapsLink = place.getMaps();
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(googleMapsLink));
                context.startActivity(viewIntent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return places.size();
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
