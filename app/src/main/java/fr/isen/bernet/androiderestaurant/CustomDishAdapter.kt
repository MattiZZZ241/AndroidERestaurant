package fr.isen.bernet.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CustomDishAdapter(private val images: ArrayList<String>) : RecyclerView.Adapter<CustomDishAdapter.ViewHolder>() {
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomDishAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_view_dish, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomDishAdapter.ViewHolder, position: Int) {
        val itemsViewModel = images[position]
        if (itemsViewModel.isNotEmpty()) {
            Picasso.get().load(itemsViewModel).into(holder.imageView)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}