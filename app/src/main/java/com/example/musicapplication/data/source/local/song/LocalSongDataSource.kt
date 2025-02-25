package com.example.musicapplication.data.source.local.song

import com.example.musicapplication.data.model.song.Song
import com.example.musicapplication.data.source.local.SongDataSource
import kotlinx.coroutines.flow.Flow

class LocalSongDataSource(
    private val songDao: SongDao
) : SongDataSource.Local {

   override val songs: List<Song>
       get() = songDao.songs

    override val favoriteSongs: Flow<List<Song>>
        get() = songDao.favoriteSongs

    override suspend fun insertSong(vararg songs: Song) {
        return songDao.insertSong(*songs)
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