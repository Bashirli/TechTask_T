package com.bashirli.techtask_t.data.dto.remote


import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("cityId")
    val cityId: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("peopleList")
    val peopleList: List<People>?,
)