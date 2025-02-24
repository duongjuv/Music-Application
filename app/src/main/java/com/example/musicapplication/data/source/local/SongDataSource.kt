package com.example.musicapplication.data.source.local

import com.example.musicapplication.data.model.song.Song
import kotlinx.coroutines.flow.Flow

interface SongDataSource {
    interface Local {
        val songs: Flow<List<Song>>

        val favoriteSongs: Flow<List<Song>>

        suspend fun insertSong(vararg songs: Song)

        suspend fun deleteSong(song: Song)

        suspend fun updateSong(song: Song)

        suspend fun updateFavoriteStatus(id: String, favorite: Boolean)
    }

    interface Remote {
        // todo
    }
}