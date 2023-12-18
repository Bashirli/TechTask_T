package com.bashirli.techtask_t.presentation.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.techtask_t.common.base.BaseAdapter
import com.bashirli.techtask_t.databinding.ItemCityFilterBinding
import com.bashirli.techtask_t.domain.model.local.CityEntityUiModel

class CityFilterAdapter : BaseAdapter<CityEntityUiModel>(selected = true) {

    var onClickItemListener: (CityEntityUiModel) -> Unit = {}

    inner class CityVH(private val binding: ItemCityFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CityEntityUiModel) {
            with(binding) {
                cityData = item
                executePendingBindings()

                checkCity.setOnClickListener {
                    onClickItemListener(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int,
    ): RecyclerView.ViewHolder = CityVH(ItemCityFilterBinding.inflate(inflater, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CityVH -> {
                items.getOrNull(position)?.let { item ->
                    holder.bind(item)
                }
            }
        }
    }

}