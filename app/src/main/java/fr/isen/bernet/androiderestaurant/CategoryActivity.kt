package fr.isen.bernet.androiderestaurant

import CustomAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.bernet.androiderestaurant.databinding.ActivityCategoryBinding
import org.json.JSONObject

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()

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

        recyclerView.adapter = CustomAdapter(arrayListOf()){
            val intent = Intent(this@CategoryActivity, FoodDetailsActivity::class.java)
            intent.putExtra("mealTitle", it)
            startActivity(intent)
        }

        loadDishesFromAPI()
    }
    private fun loadDishesFromAPI() {
        Volley.newRequestQueue(this)

        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject, {
            Log.w("CategoryActivity", "response : $it")
            /*val dataArray = jsonObject.getJSONArray("data")
            for(i in 0 until dataArray.length()) {
                val categoryObject = dataArray.getJSONObject(i)
                val nameFr = categoryObject.getString("name_fr")
                val dishesArray = categoryObject.getJSONArray("items")
                for(j in 0 until dishesArray.length()) {
                    val dishesObject = dishesArray.getJSONObject(j)
                    val dishesId = dishesObject.getString("id")
                    val nameFrDishes = dishesObject.getString("name_fr")
                    val idCategory = dishesObject.getString("id_category")
                    val images = dishesObject.getJSONArray("images")
                    val ingredientsArray = dishesObject.getJSONArray("ingredients")
                    for (k in 0 until ingredientsArray.length()) {
                        val ingredientObject = ingredientsArray.getJSONObject(k)
                        val ingredientNameFr = ingredientObject.getString("name_fr")
                    }
                }
            }*/
        }, {
            Log.w("CategoryActivity", "erreur : $it")
            Toast.makeText(this@CategoryActivity, "Erreur API", Toast.LENGTH_SHORT).show()
        })
        Volley.newRequestQueue(this).add(jsonRequest)
    }
}

