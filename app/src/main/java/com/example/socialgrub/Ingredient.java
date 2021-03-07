package com.example.socialgrub;

public class Ingredient {
    public String nameOfIngredient;
    public double measurementValue;
    public String measurementUnit;

    public Ingredient(String nameOfIngredient, double measurementValue, String measurementUnit) {
        this.nameOfIngredient = nameOfIngredient;
        this.measurementValue = measurementValue;
        this.measurementUnit = measurementUnit;
    }

    public void setNameOfIngredient(String nameOfIngredient) {
        this.nameOfIngredient = nameOfIngredient;
    }

    public String getNameOfIngredient() {
        return nameOfIngredient;
    }

    public void setMeasurementValue(int measurementValue) {
        this.measurementValue = measurementValue;
    }

    public double getMeasurementValue() {
        return measurementValue;
    }

    public void setMeasurementUnit(int measurementUnitCode) {
        switch (measurementUnitCode) {
            case 0: this.measurementUnit = "";
            break;
            case 1: this.measurementUnit = "cups";
            break;
            case 2: this.measurementUnit = "tablespoons";
            break;
            case 3: this.measurementUnit = "teaspoons";
            break;
            case 4: this.measurementUnit = "pounds";
            break;
            case 5: this.measurementUnit = "ounces";
            break;
            case 6: this.measurementUnit = "fluid ounces";
            break;
            case 7: this.measurementUnit = "grams";
            break;
            case 8: this.measurementUnit = "milligrams";
            break;
            case 9: this.measurementUnit = "liters";
            break;
            case 10: this.measurementUnit = "milliliters";
            break;
        }
    }

    public String getMeasurementUnit() {
        return this.measurementUnit;
    }
}
