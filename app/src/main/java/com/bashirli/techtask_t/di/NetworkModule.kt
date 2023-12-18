package com.bashirli.techtask_t.di

import com.bashirli.techtask_t.common.utils.Constants.BASE_URL
import com.bashirli.techtask_t.data.repository.remote.ApiRepositoryImpl
import com.bashirli.techtask_t.data.service.remote.Service
import com.bashirli.techtask_t.data.source.remote.ApiSource
import com.bashirli.techtask_t.data.source.remote.ApiSourceImpl
import com.bashirli.techtask_t.domain.repository.remote.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun injectRetrofit() =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

    @Singleton
    @Provides
    fun injectService(retrofit: Retrofit) = retrofit.create(Service::class.java)

    @Singleton
    @Provides
    fun injectApiSource(service: Service) = ApiSourceImpl(service) as ApiSource

    @Singleton
    @Provides
    fun injectApiRepository(source: ApiSource) = ApiRepositoryImpl(source) as ApiRepository


}