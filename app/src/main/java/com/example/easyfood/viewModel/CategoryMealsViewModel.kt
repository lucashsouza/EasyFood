package com.example.easyfood.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.data.pojo.MealList
import com.example.easyfood.data.pojo.MealsByCategoryList
import com.example.easyfood.data.pojo.MealsbyCategory
import com.example.easyfood.data.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsViewModel : ViewModel() {

    private val TAG: String = "CategoryMealsViewModel"

    val mealsLiveData = MutableLiveData<List<MealsbyCategory>>()

    fun getMealsByCategory(categoryName: String) {
        RetrofitInstance.api.getMealsByCategory(categoryName)
            .enqueue(object : Callback<MealsByCategoryList> {
                override fun onResponse(
                    call: Call<MealsByCategoryList>,
                    response: Response<MealsByCategoryList>
                ) {
                    response.body()?.let { mealsList ->
                        mealsLiveData.postValue(mealsList.meals)
                    }
                }

                override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                    Log.e(TAG, "getMealsByCategory (onFailure): Error while querying server!", t)
                }
            })
    }

    fun observeMealsLiveData(): LiveData<List<MealsbyCategory>> {
        return mealsLiveData
    }
}