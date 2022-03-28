package com.janirefernandez.paging3example.data.network

import com.janirefernandez.paging3example.data.model.RepoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("search/repositories?sort=stars")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): RepoSearchResponse
}