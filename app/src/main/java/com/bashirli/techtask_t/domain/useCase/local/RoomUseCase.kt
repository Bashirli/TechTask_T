package com.bashirli.techtask_t.domain.useCase.local

import com.bashirli.techtask_t.domain.model.local.EntityUiModel
import com.bashirli.techtask_t.domain.repository.local.RoomRepository
import javax.inject.Inject

class RoomUseCase @Inject constructor(
    private val repo: RoomRepository,
) {

    suspend fun insertAllData(countries: List<EntityUiModel>) = repo.insertAllData(countries)

    suspend fun getPeopleData() = repo.getPeopleData()

    suspend fun getCitiesData() = repo.getCitiesData()

    suspend fun getCountriesData() = repo.getCountriesData()

    suspend fun getFilteredCities(filter: List<Int>) = repo.getFilteredCities(filter)

    suspend fun getFilteredPeople(filter: List<Int>) = repo.getFilteredPeople(filter)

}