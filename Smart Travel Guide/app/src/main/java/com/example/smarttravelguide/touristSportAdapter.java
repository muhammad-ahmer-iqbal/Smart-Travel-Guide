package com.example.smarttravelguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class touristSportAdapter extends RecyclerView.Adapter<touristSportAdapter.touristSportViewHolder> {
    List<touristSpot> touristSpotList;
    Context mCtx;

    public touristSportAdapter(Context mCtx, List<touristSpot> touristSpotList) {
        this.touristSpotList = touristSpotList;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public touristSportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card, null);
        return new touristSportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull touristSportViewHolder holder, int position) {
        touristSpot touristSpot = touristSpotList.get(position);
        holder.txtTitle.setText(touristSpot.getTitle());
        holder.txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i("title ",String.valueOf(touristSpot.getTitle())+String.valueOf(touristSpot.getDescription()));
                Intent i = new Intent(mCtx,spotInfo.class);
                Log.i("desc",String.valueOf(touristSpot.getDescription()));
                i.putExtra("Title",touristSpot.getTitle());
                i.putExtra("Description",touristSpot.getDescription());
                i.putExtra("Weather",touristSpot.getWeather());
                i.putExtra("Video",touristSpot.getVideo());
                mCtx.startActivity(i);
            }
        });
        holder.txtTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent i = new Intent(mCtx,spotMap.class);
                i.putExtra("Latitude",touristSpot.getLatitude());
                i.putExtra("Longitude",touristSpot.getLongitude());
                mCtx.startActivity(i);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return touristSpotList.size();
    }

    public class touristSportViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        ImageView imageView;
        public touristSportViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.textView4);
            imageView = itemView.findViewById(R.id.imageView1);
        }
    }
}
