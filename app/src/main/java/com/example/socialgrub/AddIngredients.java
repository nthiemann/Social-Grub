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

public class AddIngredients extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private EditText editIngredientName1;
    private EditText editValueOfIngredient1;
    private EditText editIngredientName2;
    private EditText editValueOfIngredient2;
    private EditText editAdditionalIngredientName;
    private EditText editValueOfAdditionalIngredient;

    private Button addBothIngredients;
    private Button addAdditionalIngredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredients);

        ArrayAdapter<String> measurementUnitAdapter = new ArrayAdapter<String>(AddIngredients.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.measurememntUnits));
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

        ingredient1_unit.setOnItemClickListener(this);

        editAdditionalIngredientName = findViewById(R.id.editNameOfAdditionalIngredient);
        editValueOfAdditionalIngredient = findViewById(R.id.editTextValueOfAdditionalIngredient);
        Spinner additionalIngredient_unit = (Spinner) findViewById(R.id.measurementUnitsAdditional);
        additionalIngredient_unit.setAdapter(measurementUnitAdapter);
        addAdditionalIngredient = findViewById(R.id.addAdditionalIngredientButton);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedUnit = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}