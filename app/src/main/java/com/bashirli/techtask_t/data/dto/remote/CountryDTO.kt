package com.bashirli.techtask_t.data.dto.remote


import com.google.gson.annotations.SerializedName

data class CountryDTO(
    @SerializedName("countryList")
    val countryList: List<Country>?,
)