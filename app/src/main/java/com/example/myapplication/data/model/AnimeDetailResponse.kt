package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

data class AnimeSearchResponse(
    val pagination: Pagination?, // optional, but useful
    @SerializedName("data") val data2: List<AnimeDetailData> = emptyList()
)

data class Pagination(
    val last_visible_page: Int,
    val has_next_page: Boolean,
    val current_page: Int,
    val items: PaginationItems
)

data class PaginationItems(
    val count: Int,
    val total: Int,
    val per_page: Int
)
data class AnimeDetailResponse(
    val data: AnimeDetailData
)

data class AnimeDetailData(
    @SerializedName("mal_id") val malId: Int,
    val url: String,
    val images: AnimeImages?,
    val trailer: Trailer?,
    val approved: Boolean,
    val titles: List<AnimeTitle>,
    val title: String?,
    @SerializedName("title_english") val titleEnglish: String?,
    @SerializedName("title_japanese") val titleJapanese: String?,
    @SerializedName("title_synonyms") val titleSynonyms: List<String>?,
    val type: String?,
    val source: String?,
    val episodes: Int?,
    val status: String?,
    val airing: Boolean,
    val aired: Aired?,
    val duration: String?,
    val rating: String?,
    val score: Float?,
    @SerializedName("scored_by") val scoredBy: Int?,
    val rank: Int?,
    val popularity: Int?,
    val members: Int?,
    val favorites: Int?,
    val synopsis: String?,
    val background: String?,
    val season: String?,
    val year: Int?,
    val broadcast: Broadcast?,
    val producers: List<AnimeEntity>?,
    val licensors: List<AnimeEntity>?,
    val studios: List<AnimeEntity>?,
    val genres: List<AnimeEntity>?,
    @SerializedName("explicit_genres") val explicitGenres: List<AnimeEntity>?,
    val themes: List<AnimeEntity>?,
    val demographics: List<AnimeEntity>?
)

data class AnimeTitle(
    val type: String?,
    val title: String?
)

data class AnimeImages(
    val jpg: JpgImage,
    val webp: WebpImage
)

data class JpgImage(
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("small_image_url") val smallImageUrl: String?,
    @SerializedName("large_image_url") val largeImageUrl: String?
)

data class WebpImage(
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("small_image_url") val smallImageUrl: String?,
    @SerializedName("large_image_url") val largeImageUrl: String?
)

data class Trailer(
    @SerializedName("youtube_id") val youtubeId: String?,
    val url: String?,
    @SerializedName("embed_url") val embedUrl: String?
)

data class Aired(
    val from: String?,
    val to: String?,
    val prop: AiredProp?
)

data class AiredProp(
    val from: AiredDate?,
    val to: AiredDate?,
    val string: String?
)

data class AiredDate(
    val day: Int?,
    val month: Int?,
    val year: Int?
)

data class Broadcast(
    val day: String?,
    val time: String?,
    val timezone: String?,
    val string: String?
)

data class AnimeEntity(
    @SerializedName("mal_id") val malId: Int,
    val type: String,
    val name: String,
    val url: String
)
