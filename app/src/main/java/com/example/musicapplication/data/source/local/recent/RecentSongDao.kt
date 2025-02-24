package com.example.musicapplication.data.source.local.recent

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musicapplication.data.model.RecentSong
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSongDao {
    @get:Query("SELECT * FROM recent_songs ORDER BY play_at DESC limit 30")
    val recentSongs: Flow<List<RecentSong>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg songs: RecentSong)
}