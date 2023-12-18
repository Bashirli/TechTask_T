package com.bashirli.techtask_t.data.dto.remote


import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("humanId")
    val humanId: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("surname")
    val surname: String?,
)