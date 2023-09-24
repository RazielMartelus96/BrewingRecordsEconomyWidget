package io.curiositycore.brewingrecordseconomywidget.model.ingredients;

import java.util.HashMap;
import java.util.Map;

public class IngredientManager {

    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    private static IngredientManager instance;
    public static IngredientManager getInstance(){
        if(instance == null){
            instance = new IngredientManager();
        }
        return instance;
    }
    public void register(Ingredient ingredientToRegister){
        this.ingredientMap.put(ingredientToRegister.getName(),ingredientToRegister);
    }

    public void unregister(String nameOfIngredientToUnregister){
        this.ingredientMap.remove(nameOfIngredientToUnregister);
    }

    public boolean isRegistered(String ingredientNameToCheck){
        String trueIngredientName = ingredientNameToCheck.substring(0,ingredientNameToCheck.indexOf("/")).replace("-"," ");
        return this.ingredientMap.values().stream()
                .anyMatch(ingredient ->
                        ingredient.getName().contains(ingredientNameToCheck.substring(0,ingredientNameToCheck.indexOf("/")))
                        &&
                        ingredient instanceof CustomBrewIngredient);
    }

    public Ingredient getCustomIngredient(String ingredientName){
        return this.ingredientMap.get(ingredientName);
    }
}
