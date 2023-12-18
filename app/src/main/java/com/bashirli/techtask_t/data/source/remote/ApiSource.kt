package com.bashirli.techtask_t.data.source.remote

import com.bashirli.techtask_t.common.Resource
import com.bashirli.techtask_t.data.dto.remote.CountryDTO

interface ApiSource {

    suspend fun getData(): Resource<CountryDTO>


}