package com.bashirli.techtask_t.domain.useCase.remote

import com.bashirli.techtask_t.domain.repository.remote.ApiRepository
import javax.inject.Inject

class GetDataUseCase @Inject constructor(private val repo: ApiRepository) {

    suspend fun getData() = repo.getData()

}