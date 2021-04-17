package com.androiddevs.githubjobsclient.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "articles"
)
data class Article(
    @PrimaryKey val id: String,
    val company: String,
    val company_logo: String,
    val company_url: String,
    val created_at: String,
    val description: String,
    val how_to_apply: String,
    val location: String,
    val title: String,
    val type: String,
    val url: String


) : Serializable