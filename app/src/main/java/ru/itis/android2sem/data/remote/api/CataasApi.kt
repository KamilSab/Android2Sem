package ru.itis.android2sem.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.itis.android2sem.data.remote.dto.CatDto

interface CataasApi {
    @GET("cat")
    suspend fun getRandomCat(): CatDto

    @GET("cat/says/{text}")
    suspend fun getCatWithText(
        @Path("text") text: String,
        @Query("size") size: Int = 50,
        @Query("color") color: String = "white"
    ): CatDto
}