package com.bashirli.techtask_t.data.repository.local

import com.bashirli.techtask_t.common.Resource
import com.bashirli.techtask_t.data.mapper.toCityUiModel
import com.bashirli.techtask_t.data.mapper.toCountryUiModel
import com.bashirli.techtask_t.data.mapper.toPeopleUiModel
import com.bashirli.techtask_t.data.source.local.RoomSource
import com.bashirli.techtask_t.domain.model.local.CityEntityUiModel
import com.bashirli.techtask_t.domain.model.local.CountryEntityUiModel
import com.bashirli.techtask_t.domain.model.local.EntityUiModel
import com.bashirli.techtask_t.domain.model.local.PeopleEntityUiModel
import com.bashirli.techtask_t.domain.repository.local.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val source: RoomSource,
) : RoomRepository {

    override suspend fun insertAllData(countries: List<EntityUiModel>): Int {
        return source.insertAllData(countries)
    }

    override suspend fun getPeopleData(): Flow<Resource<List<PeopleEntityUiModel>>> = flow {
        emit(Resource.Loading)
        when (val response = source.getPeople()) {
            is Resource.Error -> emit(Resource.Error(response.throwable))
            Resource.Loading -> Unit
            is Resource.Success -> emit(Resource.Success(response.result?.toPeopleUiModel()))
        }
    }

    override suspend fun getCountriesData(): Flow<Resource<List<CountryEntityUiModel>>> = flow {
        emit(Resource.Loading)
        when (val response = source.getCountries()) {
            is Resource.Error -> emit(Resource.Error(response.throwable))
            Resource.Loading -> Unit
            is Resource.Success -> emit(Resource.Success(response.result?.toCountryUiModel()))
        }
    }


    override suspend fun getCitiesData(): Flow<Resource<List<CityEntityUiModel>>> = flow {
        emit(Resource.Loading)
        when (val response = source.getCities()) {
            is Resource.Error -> emit(Resource.Error(response.throwable))
            Resource.Loading -> Unit
            is Resource.Success -> emit(Resource.Success(response.result?.toCityUiModel()))
        }
    }

    override suspend fun getFilteredCities(filter: List<Int>): Flow<Resource<List<CityEntityUiModel>>> =
        flow {
            emit(Resource.Loading)
            when (val response = source.getFilteredCities(filter)) {
                is Resource.Error -> emit(Resource.Error(response.throwable))
                Resource.Loading -> Unit
                is Resource.Success -> emit(Resource.Success(response.result?.toCityUiModel()))
            }
        }


    override suspend fun getFilteredPeople(filter: List<Int>): Flow<Resource<List<PeopleEntityUiModel>>> =
        flow {
            emit(Resource.Loading)
            when (val response = source.getFilteredPeople(filter)) {
                is Resource.Error -> emit(Resource.Error(response.throwable))
                Resource.Loading -> Unit
                is Resource.Success -> emit(Resource.Success(response.result?.toPeopleUiModel()))
            }
        }


}