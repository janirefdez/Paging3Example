package com.janirefernandez.paging3example.ui.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.janirefernandez.paging3example.R
import com.janirefernandez.paging3example.data.model.Repo
import com.janirefernandez.paging3example.ui.viewmodel.UiModel

class RepoAdapter(private val onClickListener: (Repo) -> Unit) :
    PagingDataAdapter<UiModel, RecyclerView.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == R.layout.repo_item) {
            RepoViewHolder.create(parent)
        } else {
            SeparatorViewHolder.create(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UiModel.RepoItem -> R.layout.repo_item
            is UiModel.SeparatorItem -> R.layout.separator_item
            null -> throw UnsupportedOperationException("Unknown view")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val uiModel = getItem(position)
        uiModel.let {
            when (uiModel) {
                is UiModel.RepoItem -> (holder as RepoViewHolder).render(
                    uiModel.repo,
                    onClickListener
                )
                is UiModel.SeparatorItem -> (holder as SeparatorViewHolder).render(uiModel.description)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<UiModel>() {
            override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return (oldItem is UiModel.RepoItem && newItem is UiModel.RepoItem &&
                        oldItem.repo.fullName == newItem.repo.fullName) ||
                        (oldItem is UiModel.SeparatorItem && newItem is UiModel.SeparatorItem &&
                                oldItem.description == newItem.description)
            }

            override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean =
                oldItem == newItem
        }
    }

}