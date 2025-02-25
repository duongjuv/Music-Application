package com.example.musicapplication.data.repository.song

import com.example.musicapplication.ResultCallback
import com.example.musicapplication.data.model.song.Song
import com.example.musicapplication.data.model.song.SongList
import com.example.musicapplication.data.source.RemoteSongDataSource
import com.example.musicapplication.data.source.Result
import com.example.musicapplication.data.source.local.SongDataSource
import com.example.musicapplication.data.source.local.song.LocalSongDataSource
import kotlinx.coroutines.flow.Flow

class SongRepositoryImpl(
    private val localSongDataSource: SongDataSource.Local
) : SongRepository.Remote, SongRepository.Local {
    private val remoteSongDataSource = RemoteSongDataSource()

    override suspend fun loadSongs(callback: ResultCallback<Result<SongList>>) {
        remoteSongDataSource.loadSongs(callback)
    }

    override val songs: List<Song>
        get() = localSongDataSource.songs


    override val favoriteSongs: Flow<List<Song>>
        get() = localSongDataSource.favoriteSongs


    override suspend fun insertSong(vararg song: Song) {
        localSongDataSource.insertSong(*song)
    }

    override suspend fun deleteSong(song: Song) {
        localSongDataSource.deleteSong(song)
    }

    override suspend fun updateSong(song: Song) {
        localSongDataSource.updateSong(song)
    }

    override suspend fun updateFavoriteStatus(id: String, favorite: Boolean) {
        localSongDataSource.updateFavoriteStatus(id, favorite)
    }
}