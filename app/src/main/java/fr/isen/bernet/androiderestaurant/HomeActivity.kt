package fr.isen.bernet.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import fr.isen.bernet.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeStarters.setOnClickListener {
            switchActivity(binding.homeStarters)
        }

        binding.homeMains.setOnClickListener {
            switchActivity(binding.homeMains)
        }

        binding.homeDesserts.setOnClickListener {
            switchActivity(binding.homeDesserts)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Destroy", "Activity $this has been destroyed")
    }

    fun switchActivity(textView: TextView){
        val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
        intent.putExtra("titleCategory", textView.text)
        startActivity(intent)
    }
}