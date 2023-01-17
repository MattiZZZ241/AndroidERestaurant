package fr.isen.bernet.androiderestaurant

import CustomAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.bernet.androiderestaurant.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = binding.titleCategory
        val recyclerView = binding.recyclerCategory

        title.text = intent.extras?.getString("titleCategory")?:"No title available"

        recyclerView.layoutManager = LinearLayoutManager(this)

        val dishes = when (title.text) {
            "Entrées" -> resources.getStringArray(R.array.tab_starters).toList() as ArrayList<String>
            "Plats" -> resources.getStringArray(R.array.tab_mains).toList() as ArrayList<String>
            "Desserts" -> resources.getStringArray(R.array.tab_desserts).toList() as ArrayList<String>
            else -> ArrayList()
        }
        recyclerView.adapter = CustomAdapter(dishes) {
            val intent = Intent(this, FoodDetailsActivity::class.java)
            intent.putExtra("mealTitle", title.text)
            startActivity(intent)
        }
    }
}
