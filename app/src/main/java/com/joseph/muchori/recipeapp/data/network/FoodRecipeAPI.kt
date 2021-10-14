package com.joseph.muchori.recipeapp.data.network

import com.joseph.muchori.recipeapp.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipeAPI {

    //getting all recipes form the API
    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries:Map<String, String>
    ): Response<FoodRecipe>

    //searching for a specific query recipe on the API
    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(
        @QueryMap searchQuery:Map<String, String>
    ): Response<FoodRecipe>
}