package com.joseph.muchori.recipeapp.util

class Constants {
    companion object {

        const val BASE_URL = "https://api.spoonacular.com"
        const val API_KEY = "134c975480954133963b85fb9d6aab67"

        //API Query keys
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_IN_INGREDIENTS = "fillIngredients"




        //ROOM DATABASE
        const val DATABASE_NAME = "recipe_database"
        const val RECIPES_TABLE = "recipes_table"

        //Bottom sheet and Preferences
        const val DEFAULT_RECIPE_NUMBER = "100"
        const val DEFAULT_MEAL_TYPE = "main course"
        const val DEFAULT_DIET_TYPE = "gluten free"

        const val PREFERENCES_NAME = "recipes_preferences"
        const val PREFERENCES_MEAL_TYPE = "mealType"
        const val PREFERENCES_MEAL_TYPE_ID = "mealTypeId"
        const val PREFERENCES_DIET_TYPE = "dietType"
        const val PREFERENCES_DIET_TYPE_ID = "dietTypeId"


    }
}