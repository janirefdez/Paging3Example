package com.janirefernandez.paging3example.ui.adapters

import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.janirefernandez.paging3example.databinding.FooterItemBinding

class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = FooterItemBinding.bind(view)

    fun render(loadState: LoadState, onClickListener: () -> Unit) {
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.retryButton.setOnClickListener {
            onClickListener()
        }


    }
}