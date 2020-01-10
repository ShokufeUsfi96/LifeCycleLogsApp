package com.usefi.lifecyclelogsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.usefi.lifecyclelogsapp.movie.Result;
import com.usefi.lifecyclelogsapp.movie.SearchMoviesClass;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.TestViewHolder> {

    List<String> titles, dates, rates,images;
List<Integer> ids;
    private Context context;
    MovieRecyclerAdapter(List<String> titles, List<String> dates, List<String> rates, List<String> images, List<Integer> ids, Context context){
        this.titles = titles;
        this.dates = dates;
        this.rates = rates;
        this.images = images;
        this.ids = ids;
        this.context = context;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_recycler_item,parent,false);
        TestViewHolder holder = new TestViewHolder(v);
        return  holder;
    }


    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        String title = titles.get(position);
        String date = dates.get(position);
        String rate = rates.get(position);
        String imageAddress = "http://image.tmdb.org/t/p/w45" + images.get(position);
        holder.textTitle.setText(title);
        holder.textDate.setText(date);
        holder.textRate.setText(rate);
        Glide.with(holder.imageItem.getContext()).load(imageAddress).into(holder.imageItem);

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    class  TestViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle, textDate, textRate;
        ImageView imageItem;
        Intent intent;
        public TestViewHolder(@NonNull final View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.titleItem);
            textDate = itemView.findViewById(R.id.dateItem);
            textRate = itemView.findViewById(R.id.rateItem);
            imageItem = itemView.findViewById(R.id.imgItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    intent = new Intent(itemView.getContext(), DetailMoviesActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putIntegerArrayListExtra("IDs", (ArrayList<Integer>)ids);
                    intent.putExtra("position", pos);
                    context.startActivity(intent);

                }
            });
        }
    }
}
