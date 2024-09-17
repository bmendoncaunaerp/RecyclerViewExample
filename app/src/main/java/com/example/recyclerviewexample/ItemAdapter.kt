package com.example.recyclerviewexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.databinding.ItemViewBinding

class ItemAdapter(
    private val items: MutableList<ItemData?>,
    private val onClick: (ItemData) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private var creationCount = 0;
    private var recyclingCount = 0;

    inner class ViewHolder(
        private val binding: ItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentItem: ItemData? = null

        init {
            itemView.setOnClickListener {
                currentItem?.let {
                    onClick(it)
                }
            }
        }

        fun bind(item: ItemData) {
            currentItem = item

            binding.creationId.text = item.creationId.toString()
            binding.recyclingId.text = item.recyclingId.toString()
        }
    }

    /* Creates and inflates view and return FlowerViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context))
        creationCount++
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        recyclingCount++
        if(items[position] == null) {
            items[position] = ItemData(creationCount, recyclingCount)
        }
        val item = items[position]
        holder.bind(item!!)
    }

    override fun getItemCount(): Int = items.size
}

object ItemDiffCallback : DiffUtil.ItemCallback<ItemData>() {
    override fun areItemsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
        return oldItem == newItem
    }
}