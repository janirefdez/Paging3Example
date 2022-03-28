package com.janirefernandez.paging3example.di

import android.content.Context
import androidx.room.Room
import com.janirefernandez.paging3example.data.database.RepoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val QUOTE_DATABASE_NAME = "repo_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RepoDatabase::class.java, QUOTE_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideReposDao(db: RepoDatabase) = db.reposDao()

    @Singleton
    @Provides
    fun provideRemoteKeysDao(db: RepoDatabase) = db.remoteKeysDao()
}