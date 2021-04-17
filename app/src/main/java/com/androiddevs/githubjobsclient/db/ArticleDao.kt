package com.androiddevs.githubjobsclient.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androiddevs.githubjobsclient.models.JobsItem

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: JobsItem): Long

    @Query("SELECT * FROM jobs")
    fun getAllArticles(): LiveData<List<JobsItem>>

    @Delete
    suspend fun deleteArticle(article: JobsItem)
}