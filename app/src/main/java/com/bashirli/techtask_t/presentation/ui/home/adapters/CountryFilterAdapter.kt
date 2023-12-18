package com.bashirli.techtask_t.presentation.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.techtask_t.common.base.BaseAdapter
import com.bashirli.techtask_t.databinding.ItemCountryFilterBinding
import com.bashirli.techtask_t.domain.model.local.CountryEntityUiModel

class CountryFilterAdapter : BaseAdapter<CountryEntityUiModel>(selected = true) {

    var onClickItemListener: (CountryEntityUiModel) -> Unit = {}

    inner class CountryVH(private val binding: ItemCountryFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CountryEntityUiModel) {
            with(binding) {
                countryData = item
                executePendingBindings()

                checkCountry.setOnClickListener {
                    onClickItemListener(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int,
    ): RecyclerView.ViewHolder =
        CountryVH(ItemCountryFilterBinding.inflate(inflater, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CountryVH -> {
                items.getOrNull(position)?.let { item ->
                    holder.bind(item)
                }
            }
        }
    }

}