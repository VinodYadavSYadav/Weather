package com.proficiency.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proficiency.myapplication.MainActivity;
import com.proficiency.myapplication.R;
import com.proficiency.myapplication.model.Wheatherbean;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyStateHolder> {
    List<Wheatherbean> stateBeans;
    Activity activity;



    public WeatherAdapter(List<Wheatherbean> stateBeans, Activity activity) {
        this.stateBeans = stateBeans;
        this.activity=activity;
    }


    @Override
    public MyStateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View stateview= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new MyStateHolder(stateview);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyStateHolder holder, int position) {

        final Wheatherbean products = stateBeans.get(position);

        holder.name.setText(products.getCity_name());
        holder.temp_minmax.setText(products.getTemp_min_max());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double temp = Double.parseDouble(products.getTemp());
                String s1= String.valueOf((Math.round(temp)));
                MainActivity.cur_temp.setText(s1);
                MainActivity.cur_main.setText(products.getWheatherMain());
                MainActivity.cur_tempmin_max.setText(products.getTemp_min_max());
                MainActivity.humidity.setText(products.getHumidity());
                MainActivity.cur_name.setText(products.getCity_name());


            }
        });



    }

    @Override
    public int getItemCount() {
        return stateBeans.size();
    }

    public class MyStateHolder extends RecyclerView.ViewHolder{

        public TextView name,temp_minmax;



        public MyStateHolder(View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            temp_minmax=itemView.findViewById(R.id.temp);


        }
    }
}

