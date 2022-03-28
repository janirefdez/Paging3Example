package com.janirefernandez.paging3example.domain

import androidx.paging.PagingData
import com.janirefernandez.paging3example.data.RepoRepository
import com.janirefernandez.paging3example.data.model.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetGithubRepositoriesUseCase @Inject constructor(
    private val repoRepository: RepoRepository
) {
    operator fun invoke(query: String): Flow<PagingData<Repo>> =
        repoRepository.getSearchResultStream(query)
}