package fr.isen.bernet.androiderestaurant

import CustomAdapter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.bernet.androiderestaurant.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val title = findViewById<TextView>(R.id.titleCategory)
        title.text = intent.extras?.getString("titleCategory")?:"No title available"

        binding.recyclerCategory.layoutManager = LinearLayoutManager(this)

        when (title.text) {
            "Entrées" -> binding.recyclerCategory.adapter = CustomAdapter(resources.getStringArray(R.array.tab_starters).toList() as ArrayList<String>)
            "Plats" -> binding.recyclerCategory.adapter = CustomAdapter(resources.getStringArray(R.array.tab_mains).toList() as ArrayList<String>)
            "Desserts" -> binding.recyclerCategory.adapter = CustomAdapter(resources.getStringArray(R.array.tab_desserts).toList() as ArrayList<String>)
        }
    }
}
