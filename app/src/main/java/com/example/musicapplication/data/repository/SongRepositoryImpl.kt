package com.example.musicapplication.data.repository

import com.example.musicapplication.ResultCallback
import com.example.musicapplication.data.model.song.SongList
import com.example.musicapplication.data.source.RemoteSongDataSource
import com.example.musicapplication.data.source.Result

class SongRepositoryImpl:  SongRepository.Remote, SongRepository.Local {
    private val remoteSongDataSource = RemoteSongDataSource()

    override suspend fun loadSongs(callback: ResultCallback<Result<SongList>>) {
        remoteSongDataSource.loadSongs(callback)
    }
}