package com.example.leepper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    //se que lo de abajo es una cagada, pero estoy viendo videos de POO para entender un poco mas sobre arrayList y arreglar la pequeña cagadita. :)
    ArrayList<String> personNames;
    ArrayList<Integer>notas1;
    ArrayList<Integer>notas2;
    ArrayList<Integer>notas3;
    ArrayList<Float>medias;
    Context context;

    public CustomAdapter(Context context, ArrayList<String> personNames, ArrayList<Integer> notas1, ArrayList<Integer> notas2, ArrayList<Integer> notas3, ArrayList<Float> medias) {
        this.context = context;
        this.personNames = personNames;
        this.notas1 = notas1;
        this.notas2 = notas2;
        this.notas3 = notas3;
        this.medias = medias;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        // set the data in items
        holder.name.setText(personNames.get(position));

        holder.nota1.setText(notas1.get(position).toString());
        holder.nota2.setText(notas2.get(position).toString());
        holder.nota3.setText(notas3.get(position).toString());
        holder.media.setText(String.valueOf(medias.get(position)));

        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, personNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return personNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;// init the item view's
        TextView nota1;// init the item view's
        TextView nota2;// init the item view's
        TextView nota3;// init the item view's
        TextView media;// init the item view's



        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);
            nota1 = (TextView) itemView.findViewById(R.id.nota1);
            nota2 = (TextView) itemView.findViewById(R.id.nota2);
            nota3 = (TextView) itemView.findViewById(R.id.nota3);
            media = (TextView) itemView.findViewById(R.id.media);
        }
    }
}