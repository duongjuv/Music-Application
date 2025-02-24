package com.example.musicapplication.data.source.local.song

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.musicapplication.data.model.song.Song
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @get: Query("SELECT * FROM songs")
    val songs: Flow<List<Song>>

    @get:Query("SELECT * FROM songs WHERE favorite = 1")
    val favoriteSongs: Flow<List<Song>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSong(vararg songs: Song)

    @Delete
    suspend fun deleteSong(song: Song)

    @Update
    suspend fun updateSong(song: Song)

    @Query("UPDATE songs SET favorite = :favorite WHERE song_id = :id")
    suspend fun updateFavoriteStatus(id: String, favorite: Boolean)

}