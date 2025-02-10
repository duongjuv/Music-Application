package com.example.musicapplication.data.repository

import com.example.musicapplication.ResultCallback
import com.example.musicapplication.data.model.song.SongList
import com.example.musicapplication.data.source.Result

interface SongRepository {
    interface Local {

    }
    interface Remote {
        suspend fun loadSongs(callback: ResultCallback<Result<SongList>>)
    }
}