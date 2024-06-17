package com.dicoding.chownow.ui.dashboard.resto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.data.model.FoodItem
import com.dicoding.chownow.databinding.ItemFoodBinding

class FoodItemAdapter(private val foodItems: MutableList<FoodItem>, private val onQuantityChange: () -> Unit) :
    RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {

    class FoodItemViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        val foodItem = foodItems[position]
        with(holder.binding) {

            // set the view (initial values)
            tvNamaMakanan.text = foodItem.name
            tvHarga.text = "Rp ${foodItem.price}"
            tvQuantity.text = foodItem.quantity.toString()

            // set the click listeners for + and - buttons
            btnIncrease.setOnClickListener {
                if (foodItem.quantity > 0) {
                    foodItem.quantity++
                    tvQuantity.text = foodItem.quantity.toString()
                    onQuantityChange()
                    if (foodItem.quantity == 0) {
                        removeItem(position)
                    }
                }
            }

            btnDecrease.setOnClickListener {
                if (foodItem.quantity > 0) {
                    foodItem.quantity--
                    tvQuantity.text = foodItem.quantity.toString()
                    onQuantityChange()

                    if (foodItem.quantity == 0) {
                        removeItem(position)
                    }
                }
            }
        }
    }

    override fun getItemCount() = foodItems.size

    private fun removeItem(position: Int){
        foodItems.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }
}
