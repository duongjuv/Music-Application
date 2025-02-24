package com.example.musicapplication.data.source.local

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.musicapplication.data.model.Playlist
import com.example.musicapplication.data.model.RecentSong
import com.example.musicapplication.data.model.album.Album
import com.example.musicapplication.data.model.song.Song
import com.example.musicapplication.data.source.local.recent.RecentSongDao
import com.example.musicapplication.data.source.local.song.SongDao

@Database(
    entities = [Song::class, Album::class, Playlist::class, RecentSong::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun songDao(): SongDao
    abstract fun albumDao(): AlbumDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun recentSongDao(): RecentSongDao

    companion object {
        @Volatile
        private var _instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (_instance == null) {
                synchronized(AppDatabase::class.java) {
                    if (_instance == null) {
                        _instance = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "music.db"
                        ).build()
                    }
                }
            }
            return _instance!!
        }
    }
}