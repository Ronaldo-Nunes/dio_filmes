package br.com.runes.diofilmes.data.model

import com.google.gson.annotations.SerializedName

data class SpokenLanguage (
    @SerializedName("iso_639_1")
    val iso639_1: String,

    val name: String
)
