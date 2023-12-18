package com.bashirli.techtask_t.domain.model.local

import com.bashirli.techtask_t.data.dto.local.CityEntityDTO
import com.bashirli.techtask_t.data.dto.local.CountryEntityDTO
import com.bashirli.techtask_t.data.dto.local.PeopleEntityDTO

data class EntityUiModel(
    val humanList: List<PeopleEntityDTO>,
    val cityList: List<CityEntityDTO>,
    val country: List<CountryEntityDTO>,
)
