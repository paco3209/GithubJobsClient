package com.androiddevs.githubjobsclient.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.githubjobsclient.R

import com.androiddevs.githubjobsclient.models.JobsItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_article_preview.view.*



class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<JobsItem>() {
        override fun areItemsTheSame(oldItem: JobsItem, newItem: JobsItem): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: JobsItem, newItem: JobsItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((JobsItem) -> Unit)? = null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this).load(article.company_logo).into(ivArticleImage)
            tvSource.text = article.location
            tvTitle.text = article.title
            tvDescription.text = article.type



            //tvPublishedAt.text = article.created_at



            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    fun setOnItemClickListener(listener: (JobsItem) -> Unit) {
        onItemClickListener = listener
    }

}













