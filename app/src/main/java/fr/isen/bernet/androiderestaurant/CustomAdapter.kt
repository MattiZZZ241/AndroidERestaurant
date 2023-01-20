import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.bernet.androiderestaurant.R
import fr.isen.bernet.androidrestaurant.Data
import fr.isen.bernet.androidrestaurant.Items

class CustomAdapter(var mList: ArrayList<Items>, val onItemClickListener: (mealTitle: Items) -> Unit) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        holder.textView.text = itemsViewModel.nameFr

        holder.itemView.setOnClickListener() {
            onItemClickListener(itemsViewModel)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.cardTitle)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(dishesFromAPI: ArrayList<Items>) {
        mList = dishesFromAPI
        notifyDataSetChanged()
    }
}
