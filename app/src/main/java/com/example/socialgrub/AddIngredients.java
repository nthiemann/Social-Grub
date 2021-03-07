package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AddIngredients extends AppCompatActivity {

    private EditText editIngredientName1;
    private EditText editValueOfIngredient1;
    private EditText editIngredientName2;
    private EditText editValueOfIngredient2;
    private EditText editAdditionalIngredientName;
    private EditText editValueOfAdditionalIngredient;

    private Button addBothIngredients;
    private Button addAdditionalIngredient;

    private boolean measurementUnitSelectedForIngredient1 = false;
    private boolean measurementUnitSelectedForIngredient2 = false;
    private boolean measurementUnitSelectedForAdditionalIngredient = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredients);

        List<String> measurementUnits = new ArrayList<>();
        measurementUnits.add(0, "Choose Measurement Unit");
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
        ArrayAdapter<String> measurementUnitAdapter;
        measurementUnitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, measurementUnits);
        measurementUnitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        editIngredientName1 = findViewById(R.id.editNameOfIngredient1);
        editValueOfIngredient1 = findViewById(R.id.editTextValueOfIngredient1);

        Spinner ingredient1_unit = (Spinner) findViewById(R.id.measurementUnits1);
        ingredient1_unit.setAdapter(measurementUnitAdapter);
        ingredient1_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose Measurement Unit"))
                {
                    // The user can not add an ingredient to the ingredient list if he/she does not
                    // select a valid measurement unit
                    measurementUnitSelectedForIngredient1 = false;
                }

                else
                {
                    measurementUnitSelectedForIngredient1 = true;
                    String measurementUnitInput1 = parent.getItemAtPosition(position).toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editIngredientName2 = findViewById(R.id.editNameOfIngredient2);
        editValueOfIngredient2 = findViewById(R.id.editTextValueOfIngredient2);

        Spinner ingredient2_unit = (Spinner) findViewById(R.id.measurementUnits2);
        ingredient2_unit.setAdapter(measurementUnitAdapter);
        ingredient2_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose Measurement Unit")) {
                    // The user can not add an ingredient to the ingredient list if he/she does not
                    // select a valid measurement unit
                    measurementUnitSelectedForIngredient2 = false;
                }

                else
                {
                    measurementUnitSelectedForIngredient2 = true;
                    String measurementUnitInput2 = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addBothIngredients = findViewById(R.id.addBothIngredientsButton);
        editIngredientName1.addTextChangedListener(addBothIngredientsTextWatcher);
        editValueOfIngredient1.addTextChangedListener(addBothIngredientsTextWatcher);
        editIngredientName2.addTextChangedListener(addBothIngredientsTextWatcher);
        editValueOfIngredient2.addTextChangedListener(addBothIngredientsTextWatcher);

        editAdditionalIngredientName = findViewById(R.id.editNameOfAdditionalIngredient);
        editValueOfAdditionalIngredient = findViewById(R.id.editTextValueOfAdditionalIngredient);

        Spinner additionalIngredient_unit = (Spinner) findViewById(R.id.measurementUnitsAdditional);
        additionalIngredient_unit.setAdapter(measurementUnitAdapter);
        additionalIngredient_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose Measurement Unit"))
                {
                    // The user can not add an ingredient to the ingredient list if he/she does not
                    // select a valid measurement unit
                    measurementUnitSelectedForAdditionalIngredient = false;
                }

                else
                {
                    measurementUnitSelectedForAdditionalIngredient = true;
                    String measurementUnitInputAdditional = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addAdditionalIngredient = findViewById(R.id.addAdditionalIngredientButton);
        editAdditionalIngredientName.addTextChangedListener(addAdditionalIngredientTextWatcher);
        editValueOfAdditionalIngredient.addTextChangedListener(addAdditionalIngredientTextWatcher);
    }

    private TextWatcher addBothIngredientsTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String ingredientName1_input = editIngredientName1.getText().toString();
            String ingredientValue1_input = editValueOfIngredient1.getText().toString();
            double ingredient1_value = Double.parseDouble(ingredientValue1_input);

            String ingredientName2_input = editIngredientName2.getText().toString();
            String ingredientValue2_input = editValueOfIngredient2.getText().toString();
            double ingredient2_value = Double.parseDouble(ingredientValue2_input);

            addBothIngredients.setEnabled(!ingredientName1_input.isEmpty() && !ingredientValue1_input.isEmpty()
            && measurementUnitSelectedForIngredient1 && !ingredientName2_input.isEmpty() && !ingredientValue2_input.isEmpty()
                    && measurementUnitSelectedForIngredient2);
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
            double additionalIngredient_value = Double.parseDouble(additionalIngredientValue_input);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}