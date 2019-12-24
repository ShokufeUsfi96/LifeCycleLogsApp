package com.usefi.lifecyclelogsapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TestRecyclerAdapter extends RecyclerView.Adapter<TestRecyclerAdapter.TestViewHolder> {

    ArrayList<String> mylist;

    TestRecyclerAdapter(ArrayList<String> list){
        mylist = list;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
         TestViewHolder holder = new TestViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        String item = mylist.get(position);
        holder.txtItem.setText(item);


    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    static class TestViewHolder extends RecyclerView.ViewHolder {

        TextView txtItem;
        public TestViewHolder(@NonNull final View itemView) {
            super(itemView);

            txtItem = itemView.findViewById(R.id.txtItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (getAdapterPosition()){
                        case 0 :
                            Intent Pintent = new Intent(view.getContext(), EditProfileActivity.class);
                            break;
                    }
                }
            });

        }


    }
}
