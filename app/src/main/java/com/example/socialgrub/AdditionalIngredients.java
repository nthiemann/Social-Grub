package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class AdditionalIngredients extends AppCompatActivity {

    String additionalIngredientString;
    double additionalIngredientQuantity;



    EditText editAdditionalIngredient;
    EditText editTextValueIngredient;

    Spinner additionalIngredientUnit;

    Button continueToConfirmPost;
    Button addAdditionalIngredientButton;


    String recipeTitle;
    String recipeDescription;
    Uri imageUri;
    ArrayList<Ingredient> listOfIngredients;
    ArrayList<String> directions;
    ArrayList<Tag> tags;

    List<String> measurementUnits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_ingredients);

        addMeasureMentUnits();
        unwrapBundle();


        ArrayAdapter<String> measurementUnitAdapter;
        measurementUnitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, measurementUnits);
        measurementUnitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        editAdditionalIngredient = findViewById(R.id.editNameOfAdditionalIngredientId);
        editTextValueIngredient = findViewById(R.id.editTextValueOfAdditionalIngredientId);


        continueToConfirmPost = findViewById(R.id.toConfirmPageButtons);
        continueToConfirmPost.setEnabled(false);

        additionalIngredientUnit = (Spinner) findViewById(R.id.measurementUnitsAdditionalId);
        additionalIngredientUnit.setAdapter(measurementUnitAdapter);

        addAdditionalIngredientButton = findViewById(R.id.addAdditionalIngredientButtonId);
        addAdditionalIngredientButton.setEnabled(false);

        addAdditionalIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               additionalIngredientString = editAdditionalIngredient.getText().toString();
               additionalIngredientQuantity = Double.parseDouble(editTextValueIngredient.getText().toString());


                String measurementValue1;


                if(additionalIngredientUnit.getSelectedItem() == null) {

                    measurementValue1 = "Measurement Not Provided";
                }

                else {

                    measurementValue1 = additionalIngredientUnit.getSelectedItem().toString();
                }



               listOfIngredients.add(new Ingredient(additionalIngredientString,additionalIngredientQuantity,measurementValue1));
                Toast.makeText(AdditionalIngredients.this, "Additional ingredient added to list!", Toast.LENGTH_SHORT).show();
               editAdditionalIngredient.getText().clear();
               editTextValueIngredient.getText().clear();

               continueToConfirmPost.setEnabled(true);

            }
        });






        additionalIngredientUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals(""))
                {
                    // do nothing
                }

                else
                {
                    String measurementUnitInputAdditional = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editAdditionalIngredient.addTextChangedListener(addAdditionalIngredientTextWatcher);
        editTextValueIngredient.addTextChangedListener(addAdditionalIngredientTextWatcher);



        continueToConfirmPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent confirmIngredientsAndDirections = new Intent(AdditionalIngredients.this, ConfirmPost.class);
                confirmIngredientsAndDirections.putExtras(buildBundle());
                startActivity(confirmIngredientsAndDirections);
            }
        });


    }













    private Bundle buildBundle()
    {
        Bundle bundle = new Bundle();

        bundle.putParcelable("title", Parcels.wrap(recipeTitle));
        bundle.putParcelable("description", Parcels.wrap(recipeDescription));
        bundle.putParcelable("imageURI", Parcels.wrap(imageUri));
        bundle.putParcelable("ingredients", Parcels.wrap(listOfIngredients));
        bundle.putParcelable("directions", Parcels.wrap(directions));
        bundle.putParcelable("tags", Parcels.wrap(tags));

        return bundle;
    }




    private void unwrapBundle()
    {
        recipeTitle = Parcels.unwrap(getIntent().getParcelableExtra("title"));
        recipeDescription = Parcels.unwrap(getIntent().getParcelableExtra("description"));
        imageUri = Parcels.unwrap(getIntent().getParcelableExtra("imageURI"));
        listOfIngredients = Parcels.unwrap(getIntent().getParcelableExtra("ingredients"));
        directions = Parcels.unwrap(getIntent().getParcelableExtra("directions"));
        tags = Parcels.unwrap(getIntent().getParcelableExtra("tags"));
    }



    private void addMeasureMentUnits()
    {
        measurementUnits = new ArrayList<>();
        measurementUnits.add(0, "");
        measurementUnits.add("Cups");
        measurementUnits.add("Tablespoons");
        measurementUnits.add("Teaspoons");
        measurementUnits.add("Pounds(lb)");
        measurementUnits.add("Ounces(oz)");
        measurementUnits.add("Fluid Ounces");
        measurementUnits.add("Grams(g)");
        measurementUnits.add("Milligrams(mg)");
        measurementUnits.add("Liters(L)");
        measurementUnits.add("Milliliters(mL)");
    }



    private TextWatcher addAdditionalIngredientTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String additionalIngredientName_input = editAdditionalIngredient.getText().toString();
            String additionalIngredientValue_input = editTextValueIngredient.getText().toString();

            addAdditionalIngredientButton.setEnabled(!additionalIngredientName_input.isEmpty()
                    && !additionalIngredientValue_input.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}