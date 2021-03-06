package com.janirefernandez.paging3example.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.janirefernandez.paging3example.databinding.ActivityMainBinding
import com.janirefernandez.paging3example.ui.adapters.FooterAdapter
import com.janirefernandez.paging3example.ui.adapters.RepoAdapter
import com.janirefernandez.paging3example.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var repoAdapter: RepoAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView()
        binding.searchRepo.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding.searchRepo.text.trim().let {
                    if (it.isNotEmpty()) {
                        binding.recyclerView.scrollToPosition(0)
                        search(query = it.toString())
                    }
                }
            }
            true
        }
    }

    private fun setRecyclerView() {
        // Add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(decoration)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        repoAdapter = RepoAdapter {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            startActivity(intent)
        }
        binding.recyclerView.adapter = repoAdapter.withLoadStateFooter(FooterAdapter {
            repoAdapter.retry()
        })

        search(binding.searchRepo.text.toString())

        lifecycleScope.launch {
            repoAdapter.loadStateFlow.collect { loadState ->
                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && repoAdapter.itemCount == 0

                binding.emptyList.isVisible = isListEmpty
                binding.recyclerView.isVisible = !isListEmpty
                binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                errorState?.let {
                    Toast.makeText(
                        this@MainActivity,
                        "\uD83D\uDE28 Wooops ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }


    private fun search(query: String) {
        lifecycleScope.launch {
            viewModel.getPagingData(query).collect {
                repoAdapter.submitData(it)
            }
        }
    }
}