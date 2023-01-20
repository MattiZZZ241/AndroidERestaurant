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
import com.google.gson.Gson
import fr.isen.bernet.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.bernet.androidrestaurant.FoodDataResult
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
            handleAPIData(it.toString())
        }, {
            Log.w("CategoryActivity", "erreur : $it")
            Toast.makeText(this@CategoryActivity, "Erreur API", Toast.LENGTH_SHORT).show()
        })
        Volley.newRequestQueue(this).add(jsonRequest)
    }

    private fun handleAPIData(data: String) {
        var dishesResult = Gson().fromJson(data, FoodDataResult::class.java)
        dishesResult.data[0].nameFr
    }
}

