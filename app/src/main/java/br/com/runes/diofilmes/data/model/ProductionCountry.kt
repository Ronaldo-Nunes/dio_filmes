package br.com.runes.diofilmes.data.model

import com.google.gson.annotations.SerializedName

data class ProductionCountry (
    @SerializedName("iso_3166_1")
    val iso3166_1: String,

    val name: String
)