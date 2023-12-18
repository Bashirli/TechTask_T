package com.bashirli.techtask_t.data.service.remote

import com.bashirli.techtask_t.data.dto.remote.CountryDTO
import retrofit2.Response
import retrofit2.http.GET

interface Service {

    @GET("getdata")
    suspend fun getData(): Response<CountryDTO>

}