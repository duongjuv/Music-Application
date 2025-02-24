package com.example.musicapplication.data.source.local.song

import com.example.musicapplication.data.model.song.Song
import com.example.musicapplication.data.source.local.SongDataSource
import kotlinx.coroutines.flow.Flow

class LocalSongDataSource(
    private val songDao: SongDao
) : SongDataSource.Local {
    override suspend fun getAllSongs(): List<Song> {
        return songDao.getAllSongs()
    }

    override val favoriteSongs: Flow<List<Song>>
        get() = songDao.favoriteSongs

    override suspend fun insertSong(song: Song) {
        return songDao.insertSong(song)
    }

    override suspend fun deleteSong(song: Song) {
        return songDao.deleteSong(song)
    }

    override suspend fun updateSong(song: Song) {
        return songDao.updateSong(song)
    }

    override suspend fun updateFavoriteStatus(id: String, favorite: Boolean) {
        return songDao.updateFavoriteStatus(id, favorite)
    }
}