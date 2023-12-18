package com.bashirli.techtask_t.data.source.remote

import com.bashirli.techtask_t.common.Resource
import com.bashirli.techtask_t.data.dto.remote.CountryDTO
import com.bashirli.techtask_t.data.service.remote.Service
import retrofit2.Response
import javax.inject.Inject

class ApiSourceImpl @Inject constructor(
    private val service: Service,
) : ApiSource {

    override suspend fun getData(): Resource<CountryDTO> = handleResponse { service.getData() }

    private suspend fun <T> handleResponse(response: suspend () -> Response<T>): Resource<T> {
        return try {
            val apiResponse = response.invoke()
            if (apiResponse.isSuccessful) {
                apiResponse.body()?.let {
                    Resource.Success(it)
                } ?: Resource.Error(Exception("Error"))
            } else {
                Resource.Error(Exception("Error"))
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

}