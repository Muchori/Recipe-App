package com.joseph.muchori.recipeapp.data

import com.joseph.muchori.recipeapp.data.network.FoodRecipeAPI
import com.joseph.muchori.recipeapp.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor (
    private val foodRecipeAPI: FoodRecipeAPI
        ) {
    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe>{
        return foodRecipeAPI.getRecipes(queries)
    }

    suspend fun searchRecipes(searchQuery:  Map<String, String>): Response<FoodRecipe>{
        return foodRecipeAPI.searchRecipes(searchQuery)
    }
}