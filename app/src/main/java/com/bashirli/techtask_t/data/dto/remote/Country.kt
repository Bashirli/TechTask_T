package com.bashirli.techtask_t.data.dto.remote


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("cityList")
    val cityList: List<City>?,
    @SerializedName("countryId")
    val countryId: Int?,
    @SerializedName("name")
    val name: String?,
)