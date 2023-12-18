package com.bashirli.techtask_t.data.dto.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PeopleEntityDTO(
    @PrimaryKey val humanId: Int?,
    val name: String?,
    val surname: String?,
    val cityId: Int?,
)