package com.example.socialgrub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DirectionsAdapter extends RecyclerView.Adapter<DirectionsAdapter.DirectionsViewHolder>  {




    Context context;
    ArrayList<String> listOfDirectionsFromConfirmPage;


    public DirectionsAdapter (Context context, ArrayList<String> listOfDirectionsFromConfirmPage) {


        this.context = context;
        this.listOfDirectionsFromConfirmPage = listOfDirectionsFromConfirmPage;
    }

    @NonNull
    @Override
    public DirectionsAdapter.DirectionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DirectionsAdapter.DirectionsViewHolder(LayoutInflater.from(context).inflate(R.layout.directions_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DirectionsAdapter.DirectionsViewHolder holder, int position) {

        int indexOfDirection = position + 1;
        String convertNumberToString = Integer.toString(indexOfDirection);


        holder.directionNth.setText(listOfDirectionsFromConfirmPage.get(position));
        holder.directionNthIndex.setText(convertNumberToString);




    }

    @Override
    public int getItemCount() {
        return listOfDirectionsFromConfirmPage.size();
    }


    class DirectionsViewHolder extends RecyclerView.ViewHolder {

        TextView directionNth;
        TextView directionNthIndex;


        public DirectionsViewHolder(@NonNull View itemView) {
            super(itemView);

            directionNth = itemView.findViewById(R.id.adapterDirectionsNameId);
            directionNthIndex = itemView.findViewById(R.id.NthDirectionId);

        }
    }







}
