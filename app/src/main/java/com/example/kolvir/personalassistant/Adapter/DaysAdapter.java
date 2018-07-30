package com.example.kolvir.personalassistant.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kolvir.personalassistant.R;
import com.example.kolvir.personalassistant.pojo.Day;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DaysViewHolder>{

    private List<Day> dayList = new ArrayList<>();

    @NonNull
    @Override
    public DaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_days, parent,false);
        return new DaysViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysViewHolder holder, int position) {
        holder.bind(dayList.get(position));
    }

    @Override
    public int getItemCount() {
        return dayList.size();
}

    public void setItems(Collection<Day> days){
        dayList.addAll(days);
        notifyDataSetChanged();
    }

    public void clearItems(){
        dayList.clear();
        notifyDataSetChanged();
    }


    class DaysViewHolder extends RecyclerView.ViewHolder {

        private TextView titleText;
        private TextView descText;

        private DaysViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.days_title);
            descText = itemView.findViewById(R.id.days_desc);
        }

        public void bind(Day day){
            titleText.setText(day.getTitle());
            descText.setText(day.getDesc());
        }
    }
}
