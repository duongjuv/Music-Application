package com.example.musicapplication.data.repository.Recent

import com.example.musicapplication.data.model.RecentSong
import com.example.musicapplication.data.source.local.recent.LocalRecentSongDataSource
import kotlinx.coroutines.flow.Flow

class RecentSongRepositoryImpl(
    private val localDataSource: LocalRecentSongDataSource,
) : RecentSongRepository.Local, RecentSongRepository.Remote {
    override val recentSongs: Flow<List<RecentSong>>
        get() = localDataSource.recentSongs

    override suspend fun insert(vararg recentSongs: RecentSong) {
        localDataSource.insert(*recentSongs)
    }
}