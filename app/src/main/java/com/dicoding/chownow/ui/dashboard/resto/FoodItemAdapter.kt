package com.dicoding.chownow.ui.dashboard.resto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.data.model.FoodItem
import com.dicoding.chownow.databinding.ItemFoodBinding

class FoodItemAdapter(private val foodItems: List<FoodItem>) :
    RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {

    class FoodItemViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        val foodItem = foodItems[position]
        with(holder.binding) {
            tvFoodName.text = foodItem.name
            tvFoodPrice.text = "Rp ${foodItem.price}"
            tvQuantity.text = foodItem.quantity.toString()
            // Set the click listeners for + and - buttons
        }
    }

    override fun getItemCount() = foodItems.size
}
