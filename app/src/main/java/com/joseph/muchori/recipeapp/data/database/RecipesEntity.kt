package com.joseph.muchori.recipeapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joseph.muchori.recipeapp.models.FoodRecipe
import com.joseph.muchori.recipeapp.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity (
    var foodRecipe : FoodRecipe
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}