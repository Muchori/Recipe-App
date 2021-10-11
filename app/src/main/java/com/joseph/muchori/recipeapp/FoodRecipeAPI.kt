package com.joseph.muchori.recipeapp

import com.joseph.muchori.recipeapp.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipeAPI {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries:Map<String, String>
    ): Response<FoodRecipe>
}