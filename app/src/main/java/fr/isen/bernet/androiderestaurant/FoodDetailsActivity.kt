package fr.isen.bernet.androiderestaurant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.bernet.androiderestaurant.databinding.ActivityFoodDetailsBinding
import fr.isen.bernet.androidrestaurant.Items

class FoodDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodDetailsBinding

    private lateinit var dish: Items
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = ActivityFoodDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}