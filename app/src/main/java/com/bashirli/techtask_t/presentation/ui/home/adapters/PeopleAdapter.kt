package com.bashirli.techtask_t.presentation.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.techtask_t.common.base.BaseAdapter
import com.bashirli.techtask_t.databinding.ItemPeopleBinding
import com.bashirli.techtask_t.domain.model.local.PeopleEntityUiModel

class PeopleAdapter : BaseAdapter<PeopleEntityUiModel>() {

    inner class PeopleVH(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PeopleEntityUiModel) {
            with(binding) {
                peopleData = item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int,
    ): RecyclerView.ViewHolder = PeopleVH(ItemPeopleBinding.inflate(inflater, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PeopleVH -> {
                items.getOrNull(position)?.let { item ->
                    holder.bind(item)
                }
            }
        }
    }
}