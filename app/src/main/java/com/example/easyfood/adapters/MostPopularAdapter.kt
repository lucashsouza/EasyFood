package com.example.easyfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyfood.databinding.PopularItemsBinding
import com.example.easyfood.data.pojo.MealsbyCategory

class MostPopularAdapter(): RecyclerView.Adapter<MostPopularAdapter.PopularItemsViewHolder>() {

    lateinit var onItemClick:((MealsbyCategory) -> Unit)
    var onLongItemClick:((MealsbyCategory) -> Unit)?=null

    private var mealsList = ArrayList<MealsbyCategory>()

    fun setMeals(mealsList: ArrayList<MealsbyCategory>) {
        this.mealsList = mealsList
        notifyDataSetChanged()
    }

    inner class PopularItemsViewHolder(val binding: PopularItemsBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularItemsViewHolder {
        return PopularItemsViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(holder: PopularItemsViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.ivPopularMealItem)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealsList[position])
        }

        holder.itemView.setOnLongClickListener {
            onLongItemClick?.invoke(mealsList[position])
            true
        }
    }

}