package com.bashirli.techtask_t.data.mapper

import com.bashirli.techtask_t.data.dto.local.CityEntityDTO
import com.bashirli.techtask_t.data.dto.local.CountryEntityDTO
import com.bashirli.techtask_t.data.dto.local.PeopleEntityDTO
import com.bashirli.techtask_t.data.dto.remote.City
import com.bashirli.techtask_t.data.dto.remote.Country
import com.bashirli.techtask_t.data.dto.remote.People
import com.bashirli.techtask_t.domain.model.local.CityEntityUiModel
import com.bashirli.techtask_t.domain.model.local.CountryEntityUiModel
import com.bashirli.techtask_t.domain.model.local.EntityUiModel
import com.bashirli.techtask_t.domain.model.local.PeopleEntityUiModel


fun List<Country>.toCountryEntityDTO() = map {
    with(it) {
        CountryEntityDTO(
            countryId ?: 0,
            name.orEmpty()
        )
    }
}

fun List<City>.toCityDTO(countryId: Int?) = map {
    with(it) {
        CityEntityDTO(
            cityId ?: 0, name.orEmpty(), countryId ?: 0
        )
    }
}

fun List<People>.toPeopleDTO(cityId: Int?) = map {
    with(it) {
        PeopleEntityDTO(
            humanId ?: 0, name.orEmpty(), surname.orEmpty(), cityId ?: 0
        )
    }
}

fun List<PeopleEntityDTO>.toPeopleUiModel() = map {
    with(it) {
        PeopleEntityUiModel(humanId ?: 0, name.orEmpty(), surname.orEmpty(), cityId ?: 0)
    }
}

fun List<CountryEntityDTO>.toCountryUiModel() = map {
    with(it) {
        CountryEntityUiModel(
            countryId ?: 0, name.orEmpty(), true
        )
    }
}

fun List<CityEntityDTO>.toCityUiModel() = map {
    with(it) {
        CityEntityUiModel(
            cityId ?: 0, name.orEmpty(), countryId ?: 0, true
        )
    }
}


fun List<Country>?.toEntityUiModel(): List<EntityUiModel> =
    this?.flatMap { country ->
        country.cityList?.flatMap { city ->
            city.peopleList?.toPeopleDTO(city.cityId) ?: emptyList()
        }?.let { peopleList ->
            listOf(
                EntityUiModel(
                    peopleList,
                    cityList = country.cityList.toCityDTO(country.countryId),
                    country = this.toCountryEntityDTO()
                )
            )
        } ?: emptyList()
    } ?: emptyList()

