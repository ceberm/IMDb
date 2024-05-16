package com.tobi.imdb.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HttpServiceGenerator {

    private val BASE_URL = "https://imdb-top-100-movies.p.rapidapi.com"

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit: Retrofit = builder.build()

    fun createService(): RapidApiService {
        return retrofit.create(RapidApiService::class.java)
    }

}