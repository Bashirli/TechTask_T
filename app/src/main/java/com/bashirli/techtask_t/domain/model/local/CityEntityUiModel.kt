package com.bashirli.techtask_t.domain.model.local

data class CityEntityUiModel(
    val cityId: Int,
    val name: String,
    val countryId: Int,
    var isSelected: Boolean,
)
