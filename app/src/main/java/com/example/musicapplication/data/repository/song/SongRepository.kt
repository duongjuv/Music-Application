package com.example.musicapplication.data.repository.song

import com.example.musicapplication.ResultCallback
import com.example.musicapplication.data.model.song.Song
import com.example.musicapplication.data.model.song.SongList
import com.example.musicapplication.data.source.Result
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    interface Local {
        suspend fun getAllSongs(): List<Song>

        val favoriteSongs: Flow<List<Song>>

        suspend fun insertSong(song: Song)

        suspend fun deleteSong(song: Song)

        suspend fun updateSong(song: Song)

        suspend fun updateFavoriteStatus(id: String, favorite: Boolean)
    }
    interface Remote {
        suspend fun loadSongs(callback: ResultCallback<Result<SongList>>)
    }
}