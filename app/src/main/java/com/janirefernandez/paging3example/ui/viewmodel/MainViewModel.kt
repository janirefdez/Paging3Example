package com.janirefernandez.paging3example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.janirefernandez.paging3example.data.model.Repo
import com.janirefernandez.paging3example.domain.GetGithubRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGithubRepositoriesUseCase: GetGithubRepositoriesUseCase
) : ViewModel() {

    fun getPagingData(query: String): Flow<PagingData<Repo>> {
        return getGithubRepositoriesUseCase(query).cachedIn(viewModelScope)
    }
}