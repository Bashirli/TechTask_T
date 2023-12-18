package com.bashirli.techtask_t.di

import android.content.Context
import androidx.room.Room
import com.bashirli.techtask_t.common.utils.Constants
import com.bashirli.techtask_t.data.repository.local.RoomRepositoryImpl
import com.bashirli.techtask_t.data.service.local.RoomDAO
import com.bashirli.techtask_t.data.service.local.RoomDB
import com.bashirli.techtask_t.data.source.local.RoomSource
import com.bashirli.techtask_t.data.source.local.RoomSourceImpl
import com.bashirli.techtask_t.domain.repository.local.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @Singleton
    @Provides
    fun injectRoomDb(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        RoomDB::class.java,
        Constants.LOCAL_DB
    ).build()

    @Singleton
    @Provides
    fun injectDao(roomDB: RoomDB) = roomDB.getDao()

    @Singleton
    @Provides
    fun injectSource(roomDAO: RoomDAO) = RoomSourceImpl(roomDAO) as RoomSource

    @Singleton
    @Provides
    fun injectRepo(source: RoomSource) = RoomRepositoryImpl(source) as RoomRepository

}