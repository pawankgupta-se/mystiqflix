package com.gowittgroup.mystiqflix.data.remote

import com.gowittroup.mystiqflix.BuildKonfig.THE_MOVIE_DB_API_KEY
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


private const val API_BASE_URL = "https://api.themoviedb.org"
private val API_KEY = THE_MOVIE_DB_API_KEY
internal abstract class KtorApi {
    val client = HttpClient{
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    fun HttpRequestBuilder.pathUrl(path: String) {
        url{
            takeFrom(API_BASE_URL)
            path("3", path)
            parameter("api_key", API_KEY)
        }
    }
}