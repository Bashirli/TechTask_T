package com.bashirli.techtask_t.data.source.local

import com.bashirli.techtask_t.common.Resource
import com.bashirli.techtask_t.data.dto.local.CityEntityDTO
import com.bashirli.techtask_t.data.dto.local.CountryEntityDTO
import com.bashirli.techtask_t.data.dto.local.PeopleEntityDTO
import com.bashirli.techtask_t.data.service.local.RoomDAO
import com.bashirli.techtask_t.domain.model.local.EntityUiModel
import javax.inject.Inject

class RoomSourceImpl @Inject constructor(
    private val service: RoomDAO,
) : RoomSource {

    override suspend fun insertAllData(countries: List<EntityUiModel>): Int =
        service.insertAllData(countries)

    override suspend fun getCities(): Resource<List<CityEntityDTO>> =
        handleResponse { service.getCities() }

    override suspend fun getPeople(): Resource<List<PeopleEntityDTO>> =
        handleResponse { service.getPeople() }

    override suspend fun getCountries(): Resource<List<CountryEntityDTO>> =
        handleResponse { service.getCountries() }

    override suspend fun getFilteredCities(filter: List<Int>): Resource<List<CityEntityDTO>> =
        handleResponse { service.getFilteredCities(filter) }

    override suspend fun getFilteredPeople(filter: List<Int>): Resource<List<PeopleEntityDTO>> =
        handleResponse { service.getFilteredPeople(filter) }

    private suspend fun <T> handleResponse(response: suspend () -> T): Resource<T> {
        return try {
            val roomResponse = response.invoke()
            Resource.Success(roomResponse)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

}