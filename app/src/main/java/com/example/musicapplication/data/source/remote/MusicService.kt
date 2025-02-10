package com.example.musicapplication.data.source.remote

import com.example.musicapplication.data.model.album.AlbumList
import com.example.musicapplication.data.model.song.SongList
import retrofit2.Response
import retrofit2.http.GET

interface MusicService {

    @GET("/resources/braniumapis/songs.json")
    suspend fun loadSongs() : Response<SongList>

    @GET("/resources/braniumapis/playlist.json")
    suspend fun loadAlbums() : Response<AlbumList>

}