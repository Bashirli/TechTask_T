package com.bashirli.techtask_t.domain.model.remote


data class CountryDataUiModel(
    val cityList: List<CityUiModel>,
    val countryId: Int,
    val name: String,
)