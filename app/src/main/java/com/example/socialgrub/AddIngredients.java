package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
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
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AddIngredients extends AppCompatActivity {

    String ingredient1_name;
    double ingredient1_quantity;
    int measurementUnitIDforIngredient1;
    String ingredient2_name;
    double ingredient2_quantity;
    int measurementUnitIDforIngredient2;

    String additionalIngredientName;
    double additionalIngredientQuantity;
    int measurementIDforAdditionalIngredient;

    private EditText editIngredientName1;
    private EditText editValueOfIngredient1;
    private EditText editIngredientName2;
    private EditText editValueOfIngredient2;
    private EditText editAdditionalIngredientName;
    private EditText editValueOfAdditionalIngredient;

    private Button addBothIngredients;
    private Button addAdditionalIngredient;
    private Button toDirectionsPage;

    private List<String> measurementUnits;

    String recipeTitle;
    String recipeDescription;
    Uri imageUri;
    ArrayList<Ingredient>listOfIngredients = new ArrayList<Ingredient>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredients);

        addMeasureMentUnits();
        unwrapBundle();

        ArrayAdapter<String> measurementUnitAdapter;
        measurementUnitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, measurementUnits);
        measurementUnitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        editIngredientName1 = findViewById(R.id.editNameOfIngredient1);
        editValueOfIngredient1 = findViewById(R.id.editTextValueOfIngredient1);

        Spinner ingredient1_unit = (Spinner) findViewById(R.id.measurementUnits1);
        ingredient1_unit.setAdapter(measurementUnitAdapter);

        editIngredientName2 = findViewById(R.id.editNameOfIngredient2);
        editValueOfIngredient2 = findViewById(R.id.editTextValueOfIngredient2);

        Spinner ingredient2_unit = (Spinner) findViewById(R.id.measurementUnits2);
        ingredient2_unit.setAdapter(measurementUnitAdapter);

        addBothIngredients = findViewById(R.id.addBothIngredientsButton);
        addBothIngredients.setEnabled(false);
        addBothIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredient1_name = editIngredientName1.getText().toString();
                ingredient1_quantity = Double.parseDouble(editValueOfIngredient1.getText().toString());

                ingredient2_name = editIngredientName2.getText().toString();
                ingredient2_quantity = Double.parseDouble(editValueOfIngredient2.getText().toString());

                String measurementValue1 = "Measurement";
                String measurementValue2 = "Measurement";

                if(ingredient1_unit.getSelectedItem() == null) {

                    measurementValue1 = "Measurement Not Provided";
                }

                else {

                    measurementValue1 = ingredient1_unit.getSelectedItem().toString();
                }

               if(ingredient2_unit.getSelectedItem() == null) {

                   measurementValue2 = "Measurement Not Provided";
               }

               else {

                   measurementValue2 = ingredient2_unit.getSelectedItem().toString();
               }



                listOfIngredients.add(new Ingredient(ingredient1_name,ingredient1_quantity,measurementValue1));
                listOfIngredients.add(new Ingredient(ingredient2_name,ingredient2_quantity,measurementValue2));

                Toast.makeText(AddIngredients.this, "Ingredients added to list!", Toast.LENGTH_SHORT).show();


                editIngredientName1.getText().clear();
                editValueOfIngredient1.getText().clear();
                editIngredientName2.getText().clear();
                editValueOfIngredient2.getText().clear();

                toDirectionsPage.setEnabled(true);




            }
        });

        ingredient1_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals(""))
                {
                  // Do nothing
                }

                else
                {
                    String measurementUnitInput1 = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editIngredientName1.addTextChangedListener(addBothIngredientsTextWatcher);
        editValueOfIngredient1.addTextChangedListener(addBothIngredientsTextWatcher);

        ingredient2_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals(""))
                {
                    // do nothing
                }

                else
                {
                    String measurementUnitInput2 = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editIngredientName2.addTextChangedListener(addBothIngredientsTextWatcher);
        editValueOfIngredient2.addTextChangedListener(addBothIngredientsTextWatcher);






        editAdditionalIngredientName = findViewById(R.id.editNameOfAdditionalIngredient);
        editValueOfAdditionalIngredient = findViewById(R.id.editTextValueOfAdditionalIngredient);
        Spinner additionalIngredient_unit = (Spinner) findViewById(R.id.measurementUnitsAdditional);
        additionalIngredient_unit.setAdapter(measurementUnitAdapter);

        addAdditionalIngredient = findViewById(R.id.addAdditionalIngredientButton);
        addAdditionalIngredient.setEnabled(false);
        addAdditionalIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                additionalIngredientName = editAdditionalIngredientName.getText().toString();
                additionalIngredientQuantity = Double.parseDouble(editValueOfAdditionalIngredient.getText().toString());

                String measurementValueAdditional;

                if(additionalIngredient_unit.getSelectedItem() == null) {

                    measurementValueAdditional = "Measurement Not Provided";

                }

                else {

                    measurementValueAdditional = additionalIngredient_unit.getSelectedItem().toString();
                }



                listOfIngredients.add(new Ingredient(additionalIngredientName,additionalIngredientQuantity,measurementValueAdditional));



                Toast.makeText(AddIngredients.this, "Ingredient added to list!", Toast.LENGTH_SHORT).show();

                editAdditionalIngredientName.getText().clear();
                editValueOfAdditionalIngredient.getText().clear();
            }
        });

        additionalIngredient_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        editAdditionalIngredientName.addTextChangedListener(addAdditionalIngredientTextWatcher);
        editValueOfAdditionalIngredient.addTextChangedListener(addAdditionalIngredientTextWatcher);

        toDirectionsPage = findViewById(R.id.toDirectionPageButtons);
        toDirectionsPage.setEnabled(false);
        toDirectionsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent directionsIntent = new Intent(AddIngredients.this, AddDirections.class);
                directionsIntent.putExtras(buildBundle());
                startActivity(directionsIntent);
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

        return bundle;
    }
    private void unwrapBundle()
    {
        recipeTitle = Parcels.unwrap(getIntent().getParcelableExtra("title"));
        recipeDescription = Parcels.unwrap(getIntent().getParcelableExtra("description"));
        imageUri = Parcels.unwrap(getIntent().getParcelableExtra("imageURI"));
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
    private TextWatcher addBothIngredientsTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String ingredientName1_input = editIngredientName1.getText().toString();
            String ingredientValue1_input = editValueOfIngredient1.getText().toString();

            String ingredientName2_input = editIngredientName2.getText().toString();
            String ingredientValue2_input = editValueOfIngredient2.getText().toString();

            addBothIngredients.setEnabled(!ingredientName1_input.isEmpty() && !ingredientValue1_input.isEmpty()
            && !ingredientName2_input.isEmpty() && !ingredientValue2_input.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };





    private TextWatcher addAdditionalIngredientTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String additionalIngredientName_input = editAdditionalIngredientName.getText().toString();
            String additionalIngredientValue_input = editValueOfAdditionalIngredient.getText().toString();

            addAdditionalIngredient.setEnabled(!additionalIngredientName_input.isEmpty()
                    && !additionalIngredientValue_input.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}