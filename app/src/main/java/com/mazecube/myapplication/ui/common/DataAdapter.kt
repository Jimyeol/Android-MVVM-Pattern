package com.mazecube.myapplication.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.mazecube.myapplication.AppExecutors
import com.mazecube.myapplication.MainViewModel
import com.mazecube.myapplication.R
import com.mazecube.myapplication.data.model.Facts
import com.mazecube.myapplication.databinding.ListItemFactsBinding

class DataAdapter (
    appExecutors: AppExecutors,
    private val viewModel: MainViewModel
): DataBoundListAdapter<Facts, ListItemFactsBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Facts>() {
        override fun areItemsTheSame(oldItem: Facts, newItem: Facts): Boolean {
            return oldItem._id == newItem._id
                    && oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Facts, newItem: Facts): Boolean {
            return oldItem._id == newItem._id
                    && oldItem._id == newItem._id
        }
    }
) {
    override fun createBinding(parent: ViewGroup): ListItemFactsBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_facts,
            parent,
            false
        )
    }

    override fun bind(binding: ListItemFactsBinding, item: Facts) {
        binding.item = item
        binding.viewModel = viewModel
    }
}