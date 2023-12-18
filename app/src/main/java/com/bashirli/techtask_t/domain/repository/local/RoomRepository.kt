package com.bashirli.techtask_t.domain.repository.local

import com.bashirli.techtask_t.common.Resource
import com.bashirli.techtask_t.domain.model.local.CityEntityUiModel
import com.bashirli.techtask_t.domain.model.local.CountryEntityUiModel
import com.bashirli.techtask_t.domain.model.local.EntityUiModel
import com.bashirli.techtask_t.domain.model.local.PeopleEntityUiModel
import kotlinx.coroutines.flow.Flow

interface RoomRepository {

    suspend fun insertAllData(countries: List<EntityUiModel>): Int

    suspend fun getPeopleData(): Flow<Resource<List<PeopleEntityUiModel>>>

    suspend fun getCountriesData(): Flow<Resource<List<CountryEntityUiModel>>>

    suspend fun getCitiesData(): Flow<Resource<List<CityEntityUiModel>>>

    suspend fun getFilteredCities(filter: List<Int>): Flow<Resource<List<CityEntityUiModel>>>

    suspend fun getFilteredPeople(filter: List<Int>): Flow<Resource<List<PeopleEntityUiModel>>>


}