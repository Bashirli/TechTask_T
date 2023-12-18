package com.bashirli.techtask_t.data.source.local

import com.bashirli.techtask_t.common.Resource
import com.bashirli.techtask_t.data.dto.local.CityEntityDTO
import com.bashirli.techtask_t.data.dto.local.CountryEntityDTO
import com.bashirli.techtask_t.data.dto.local.PeopleEntityDTO
import com.bashirli.techtask_t.domain.model.local.EntityUiModel

interface RoomSource {

    suspend fun insertAllData(countries: List<EntityUiModel>): Int

    suspend fun getCities(): Resource<List<CityEntityDTO>>

    suspend fun getPeople(): Resource<List<PeopleEntityDTO>>

    suspend fun getCountries(): Resource<List<CountryEntityDTO>>

    suspend fun getFilteredCities(filter: List<Int>): Resource<List<CityEntityDTO>>

    suspend fun getFilteredPeople(filter: List<Int>): Resource<List<PeopleEntityDTO>>

}