package com.bilge.nicesongdude;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.Holder> {



    private ArrayList<String> nameList;
    private ArrayList<String> numberList;
    private ArrayList<String> musicList;
    private ArrayList<String> cityList;
    public adapter(ArrayList<String> nameList, ArrayList<String> numberList, ArrayList<String> musicList, ArrayList<String> cityList) {
        this.nameList = nameList;
        this.numberList = numberList;
        this.musicList = musicList;
        this.cityList = cityList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater lf = LayoutInflater.from(parent.getContext());
        View view = lf.inflate(R.layout.rec,parent,false);


        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.name.setText(nameList.get(position));
        holder.number.setText(numberList.get(position));
        holder.music.setText(musicList.get(position));
        holder.city.setText(cityList.get(position));


    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView name;
        TextView number;
        TextView music;
        TextView city;

        public Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameMusicText);
            number = itemView.findViewById(R.id.numberMusicText);
            music = itemView.findViewById(R.id.musicMusicText);
            city = itemView.findViewById(R.id.cityMusicText);


        }
    }


}
