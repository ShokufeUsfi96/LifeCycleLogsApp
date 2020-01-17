package com.usefi.lifecyclelogsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.AppGlideModule;
import com.usefi.lifecyclelogsapp.movie.Result;
import com.usefi.lifecyclelogsapp.movie.SearchMoviesClass;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.TestViewHolder>  {

    List<Result> resultItems = new ArrayList<>();
    AdapterItemClicked itemCLicked = null;

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_recycler_item,parent,false);
        TestViewHolder holder = new TestViewHolder(v);
        return  holder;
    }

    public void setAdapterItemClicked(AdapterItemClicked itemClicked){
        this.itemCLicked =itemClicked;
    }
    public void setResults(List<Result> resultItems){
        this.resultItems = resultItems;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, final int position) {

        holder.txtTitle.setText(resultItems.get(position).getTitle());
        holder.txtDate.setText(resultItems.get(position).getReleaseDate());
        holder.txtRate.setText(resultItems.get(position).getVoteAverage().toString());
        String imgAddress = "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + resultItems.get(position).getPosterPath();
        Glide.with(holder.imgItem.getContext()).load( imgAddress ).into(holder.imgItem);

        holder.itemContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCLicked.itemClicked(resultItems.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (resultItems == null)
            return 0;
        else
        return resultItems.size();
    }

    class  TestViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitle, txtDate, txtRate;
        ImageView imgItem;
        RelativeLayout itemContext;
        public TestViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.titleItem);
            txtDate = itemView.findViewById(R.id.dateItem);
            txtRate = itemView.findViewById(R.id.rateItem);
            imgItem = itemView.findViewById(R.id.imgItem);
            itemContext = itemView.findViewById(R.id.itemContext);
        }


    }

    public  interface AdapterItemClicked{
        public void itemClicked(int Id);
    }

}
