package com.janirefernandez.paging3example.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
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
}