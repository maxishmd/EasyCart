package com.example.demo.api.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

@DatabaseTable(tableName = "ingredient")
@Data
public class Ingredient {
    @DatabaseField(generatedId = true, columnName = "ingredientId")
    private int id;

    @DatabaseField(columnName = "name")
    private String name;

    @DatabaseField(columnName = "amount")
    private String amount;

    @DatabaseField(columnName = "vegetarian")
    private boolean vegetarian;

    @DatabaseField(columnName = "vegan")
    private boolean vegan;

    @DatabaseField(columnName = "glutenFree")
    private boolean glutenFree;

    // ORMLite unterstützt keine direkte ManyToOne-Beziehung,
    // daher wird die Referenz hier in der Regel nicht als Fremdschlüssel behandelt.
    @DatabaseField(foreign = true, columnName = "recipeId")
    private Recipe recipe;

    // Standard-Konstruktor
    public Ingredient() {}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
