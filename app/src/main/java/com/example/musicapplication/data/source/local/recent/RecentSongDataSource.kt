package com.example.musicapplication.data.source.local.recent

import com.example.musicapplication.data.model.RecentSong
import kotlinx.coroutines.flow.Flow

interface RecentSongDataSource {
    interface Local {
        val recentSongs: Flow<List<RecentSong>>

        suspend fun insert(vararg songs: RecentSong)
    }

    interface Remote {
        // todo
    }
}