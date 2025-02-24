package com.example.musicapplication.data.source.local.recent

import com.example.musicapplication.data.model.RecentSong
import kotlinx.coroutines.flow.Flow

class LocalRecentSongDataSource(
    private val recentSongDao: RecentSongDao
) : RecentSongDataSource.Local {

    override val recentSongs: Flow<List<RecentSong>>
        get() = recentSongDao.recentSongs

    override suspend fun insert(vararg recenSongs: RecentSong) {
        recentSongDao.insert(*recenSongs)
    }
}