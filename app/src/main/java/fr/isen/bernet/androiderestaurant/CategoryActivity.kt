package fr.isen.bernet.androiderestaurant

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val title = findViewById<TextView>(R.id.titleCategory)
        title.text = intent.extras?.getString("titleCategory")?:"No title available"

    }
}