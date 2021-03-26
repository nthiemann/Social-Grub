package com.example.socialgrub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {



    Context context;
    ArrayList<Ingredient> lisOfIngredientsFromConfirmPost;

    public IngredientsAdapter(Context context, ArrayList<Ingredient> lisOfIngredientsFromConfirmPost) {


        this.context = context;
        this.lisOfIngredientsFromConfirmPost = lisOfIngredientsFromConfirmPost;
    }


    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {



        return new IngredientViewHolder(LayoutInflater.from(context).inflate(R.layout.ingredient_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.IngredientViewHolder holder, int position) {




        holder.ingredientName.setText(lisOfIngredientsFromConfirmPost.get(position).getNameOfIngredient());
        holder.ingredientMeasurement.setText(lisOfIngredientsFromConfirmPost.get(position).getMeasurementUnit());
        String convertDoubleToString = Double.toString(lisOfIngredientsFromConfirmPost.get(position).getMeasurementValue());
        holder.ingredientAmount.setText(convertDoubleToString);


    }

    @Override
    public int getItemCount() {
        return lisOfIngredientsFromConfirmPost.size();
    }


    class IngredientViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientName;
        TextView ingredientAmount;
        TextView ingredientMeasurement;


        public IngredientViewHolder(@NonNull View itemView) {

            super(itemView);

            ingredientAmount = itemView.findViewById(R.id.amountIngredientId);
            ingredientName = itemView.findViewById(R.id.adapterIngredientNameId);
            ingredientMeasurement = itemView.findViewById(R.id.measurementIngredientId);
        }

    }



}
