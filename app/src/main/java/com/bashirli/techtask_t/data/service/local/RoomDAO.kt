package com.bashirli.techtask_t.data.service.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bashirli.techtask_t.data.dto.local.CityEntityDTO
import com.bashirli.techtask_t.data.dto.local.CountryEntityDTO
import com.bashirli.techtask_t.data.dto.local.PeopleEntityDTO
import com.bashirli.techtask_t.domain.model.local.EntityUiModel

@Dao
interface RoomDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<CountryEntityDTO>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(cities: List<CityEntityDTO>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(people: List<PeopleEntityDTO>): List<Long>


    @Query("SELECT * FROM CityEntityDTO")
    suspend fun getCities(): List<CityEntityDTO>

    @Query("SELECT * FROM PeopleEntityDTO")
    suspend fun getPeople(): List<PeopleEntityDTO>

    @Query("select * from CountryEntityDTO")
    suspend fun getCountries(): List<CountryEntityDTO>

    @Query("select * from CityEntityDTO where countryId in (:filter)")
    suspend fun getFilteredCities(filter: List<Int>): List<CityEntityDTO>

    @Query("select * from PeopleEntityDTO where cityId in (:filter)")
    suspend fun getFilteredPeople(filter: List<Int>): List<PeopleEntityDTO>


    suspend fun insertAllData(
        data: List<EntityUiModel>,
    ): Int {
        var humanResponse = emptyList<Long>()
        var countryResponse = emptyList<Long>()
        var cityResponse = emptyList<Long>()

        data.forEach {
            humanResponse = insertPeople(it.humanList)
            countryResponse = insertCountries(it.country)
            cityResponse = insertCities(it.cityList)
        }
        return if (!(humanResponse.any { it == 0L } || countryResponse.any { it == 0L } || cityResponse.any { it == 0L })) 1 else 0
    }

}