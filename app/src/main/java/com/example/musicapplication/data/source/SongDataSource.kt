package com.example.musicapplication.data.source

import com.example.musicapplication.ResultCallback
import com.example.musicapplication.data.model.song.SongList

interface SongDataSource {
    interface Local {

    }

    interface Remote {
        suspend fun loadSongs(callback: ResultCallback<Result<SongList>>)
    }
}