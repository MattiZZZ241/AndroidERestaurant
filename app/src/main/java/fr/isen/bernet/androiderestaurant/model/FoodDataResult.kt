package fr.isen.bernet.androidrestaurant

import com.google.gson.annotations.SerializedName


data class FoodDataResult (

  @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()

) : java.io.Serializable