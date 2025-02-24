package com.example.musicapplication.utils

import android.content.Context
import com.example.musicapplication.data.repository.Recent.RecentSongRepositoryImpl
import com.example.musicapplication.data.repository.song.SongRepositoryImpl
import com.example.musicapplication.data.source.local.AppDatabase
import com.example.musicapplication.data.source.local.SongDataSource
import com.example.musicapplication.data.source.local.recent.LocalRecentSongDataSource
import com.example.musicapplication.data.source.local.song.LocalSongDataSource

object InjectionUtils {
    fun provideRecentSongDataSource(
        context: Context
    ): LocalRecentSongDataSource {
        val database = AppDatabase.getInstance(context)
        return LocalRecentSongDataSource(database.recentSongDao())
    }

    fun provideRecentSongRepository(
        dataSource: LocalRecentSongDataSource
    ): RecentSongRepositoryImpl {
        return RecentSongRepositoryImpl(dataSource)
    }

    fun provideSongDataSource(
        context: Context
    ): SongDataSource.Local {
        val database = AppDatabase.getInstance(context)
        return LocalSongDataSource(database.songDao())
    }

    fun provideSongRepository(
        dataSource: SongDataSource.Local
    ): SongRepositoryImpl {
        return SongRepositoryImpl(dataSource)
    }
}