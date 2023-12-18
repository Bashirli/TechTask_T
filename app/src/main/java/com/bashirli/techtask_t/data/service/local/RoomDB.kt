package com.bashirli.techtask_t.data.service.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bashirli.techtask_t.data.dto.local.CityEntityDTO
import com.bashirli.techtask_t.data.dto.local.CountryEntityDTO
import com.bashirli.techtask_t.data.dto.local.PeopleEntityDTO

@Database([CountryEntityDTO::class, CityEntityDTO::class, PeopleEntityDTO::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun getDao(): RoomDAO
}