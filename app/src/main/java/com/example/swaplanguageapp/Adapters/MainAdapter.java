package com.example.swaplanguageapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.swaplanguageapp.Models.Model;
import com.example.swaplanguageapp.R;
import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ArrayList<Model> modelArrayList;
    Context context;

    public MainAdapter(ArrayList<Model> modelArrayList, Context context) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_event.setText(modelArrayList.get(position).getEvent());
        holder.tv_eventusers.setText(modelArrayList.get(position).getEventUsers());
        holder.tv_series.setText(modelArrayList.get(position).getSeries());
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_event, tv_eventusers, tv_series;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_event = itemView.findViewById(R.id.tv_event);
                tv_eventusers = itemView.findViewById(R.id.tv_eventusers);
                tv_series = itemView.findViewById(R.id.tv_series);
              }
             }
        }