package com.janirefernandez.paging3example.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.janirefernandez.paging3example.data.model.Repo
import com.janirefernandez.paging3example.data.network.ApiService
import com.janirefernandez.paging3example.di.Constants.Companion.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RepoRepository @Inject constructor(
    private val api: ApiService,
) {
    /**
     * Search repositories whose names match the query, exposed as a stream of data that will emit
     * every time we get more data from the network.
     */
    fun getSearchResultStream(query: String): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { RepoPagingSource(api, query) }
        ).flow
    }

}