package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class RecommendationResponse(
    val data : List<RecommendationGroup>
)

data class RecommendationGroup(
    val entry: List<AnimeEntry>,
    val votes : Int
)

data class AnimeEntry(
    @SerializedName("mal_id") val malId : Int,
    val title : String?,
    val url : String?,
    val images : Images?
)

data class Images (
    val jpg : Jpg?
)
data class Jpg(
    @SerializedName("large_image_url") val imageUrl : String?
)