package com.bashirli.techtask_t.domain.model.remote


data class CityUiModel(
    val cityId: Int,
    val name: String,
    val peopleList: List<PeopleUiModel>,
)
