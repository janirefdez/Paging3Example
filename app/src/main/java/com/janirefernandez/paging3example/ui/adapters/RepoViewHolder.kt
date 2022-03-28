package com.janirefernandez.paging3example.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.janirefernandez.paging3example.R
import com.janirefernandez.paging3example.data.model.Repo
import com.janirefernandez.paging3example.databinding.RepoItemBinding

class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = RepoItemBinding.bind(view)

    fun render(repo: Repo, onClickListener: (Repo) -> Unit) {
        binding.repoName.text = repo.name
        binding.repoDescription.text = repo.description
        binding.repoStars.text = repo.stars.toString()
        binding.repoLanguage.text = repo.language
        binding.repoForks.text = repo.forks.toString()
        itemView.setOnClickListener {
            onClickListener(repo)
        }
    }

    companion object {
        fun create(parent: ViewGroup): RepoViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
            return RepoViewHolder(view)
        }
    }
}