package com.bashirli.techtask_t.domain.repository.remote

import com.bashirli.techtask_t.common.Resource
import com.bashirli.techtask_t.domain.model.local.EntityUiModel
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun getData(): Flow<Resource<List<EntityUiModel>>>

}