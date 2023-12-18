package com.bashirli.techtask_t.data.dto.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryEntityDTO(
    @PrimaryKey val countryId: Int?,
    val name: String?,
)
