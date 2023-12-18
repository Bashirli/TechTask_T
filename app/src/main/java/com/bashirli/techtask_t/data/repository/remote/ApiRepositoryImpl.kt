package com.bashirli.techtask_t.data.repository.remote

import com.bashirli.techtask_t.common.Resource
import com.bashirli.techtask_t.data.mapper.toEntityUiModel
import com.bashirli.techtask_t.data.source.remote.ApiSource
import com.bashirli.techtask_t.domain.model.local.EntityUiModel
import com.bashirli.techtask_t.domain.repository.remote.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val source: ApiSource,
) : ApiRepository {
    override suspend fun getData(): Flow<Resource<List<EntityUiModel>>> = flow {
        emit(Resource.Loading)
        when (val response = source.getData()) {
            is Resource.Error -> emit(Resource.Error(response.throwable))
            Resource.Loading -> Unit
            is Resource.Success -> emit(Resource.Success(response.result?.countryList?.toEntityUiModel()))
        }
    }
}