package com.cursokotlin.retrofitkotlinexample.retrofit2

import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by aristidesguimeraorozco on 29/4/18.
 */
interface APIService {
    @GET
    suspend fun getCharacterByName(@Url url:String): DogsResponse
}