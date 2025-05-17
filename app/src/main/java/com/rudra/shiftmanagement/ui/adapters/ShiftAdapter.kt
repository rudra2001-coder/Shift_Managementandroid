package com.rudra.shiftmanagement.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rudra.shiftmanagement.databinding.ItemShiftBinding
import com.rudra.shiftmanagement.model.Shift

class ShiftAdapter : ListAdapter<Shift, ShiftAdapter.ShiftViewHolder>(ShiftComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShiftViewHolder {
        val binding = ItemShiftBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ShiftViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShiftViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class ShiftViewHolder(private val binding: ItemShiftBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(shift: Shift) {
            binding.apply {
                tvEmployeeName.text = shift.employeeName
                tvShiftDate.text = shift.shiftDate
                tvShiftTime.text = "${shift.startTime} - ${shift.endTime}"
                tvPosition.text = shift.position

                root.setOnClickListener {
                    // Handle item click (navigate to edit)
                }
            }
        }
    }

    class ShiftComparator : DiffUtil.ItemCallback<Shift>() {
        override fun areItemsTheSame(oldItem: Shift, newItem: Shift): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Shift, newItem: Shift): Boolean {
            return oldItem == newItem
        }
    }
}