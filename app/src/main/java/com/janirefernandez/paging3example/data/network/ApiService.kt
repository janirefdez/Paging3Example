package com.janirefernandez.paging3example.data.network

import com.janirefernandez.paging3example.data.model.RepoSearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiService @Inject constructor(private val api: ApiClient) {

    suspend fun searchRepos(query: String, page: Int, itemsPerPage: Int): RepoSearchResponse {
        return withContext(Dispatchers.IO) {
            val response = api.searchRepos(query, page, itemsPerPage)
            response
        }
    }
}