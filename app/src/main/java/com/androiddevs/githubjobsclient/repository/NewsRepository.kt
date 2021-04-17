package com.androiddevs.githubjobsclient.repository

import com.androiddevs.githubjobsclient.api.RetrofitInstance
import com.androiddevs.githubjobsclient.db.JobsDatabase
import com.androiddevs.githubjobsclient.models.JobsItem

class NewsRepository(
    val db: JobsDatabase
) {
    suspend fun getBreakingNews() =
        RetrofitInstance.api.getJobs()

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchJobs(searchQuery, pageNumber)

    suspend fun getJob(id: String) =
        RetrofitInstance.api.getJob(id)


    suspend fun upsert(job: JobsItem) = db.getArticleDao().upsert(job)

    fun getSaveJobs() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(job: JobsItem) = db.getArticleDao().deleteArticle(job)


}