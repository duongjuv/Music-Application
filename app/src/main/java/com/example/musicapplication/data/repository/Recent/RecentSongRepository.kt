package com.example.musicapplication.data.repository.Recent

import com.example.musicapplication.data.model.RecentSong
import kotlinx.coroutines.flow.Flow

interface RecentSongRepository {
    interface Local {
        val recentSongs: Flow<List<RecentSong>>

        suspend fun insert(vararg recentSongs: RecentSong)
    }

    interface Remote {
        // todo
    }
}