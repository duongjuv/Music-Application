package com.example.musicapplication.data.source.local.song

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.musicapplication.data.model.song.Song
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Query("SELECT * FROM songs")
    suspend fun getAllSongs(): List<Song>

    @get:Query("SELECT * FROM songs WHERE favorite = 1")
    val favoriteSongs: Flow<List<Song>>

    @Insert
    suspend fun insertSong(song: Song)

    @Delete
    suspend fun deleteSong(song: Song)

    @Update
    suspend fun updateSong(song: Song)

    @Query("UPDATE songs SET favorite = :favorite WHERE song_id = :id")
    suspend fun updateFavoriteStatus(id: String, favorite: Boolean)

}