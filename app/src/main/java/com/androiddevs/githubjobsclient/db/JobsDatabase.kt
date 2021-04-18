package com.androiddevs.githubjobsclient.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.androiddevs.githubjobsclient.models.JobsItem

@Database(
    entities = [JobsItem::class],
    version = 1
)

abstract class JobsDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        @Volatile
        private var instance: JobsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                JobsDatabase::class.java,
                "jobs_db.db"
            ).build()
    }
}