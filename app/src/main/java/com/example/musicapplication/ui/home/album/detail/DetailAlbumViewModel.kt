package com.example.musicapplication.ui.home.album.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicapplication.data.model.album.Album
import com.example.musicapplication.data.model.song.Song

class DetailAlbumViewModel : ViewModel() {
    private val _songs = MutableLiveData<List<Song>>()
    private val _albums = MutableLiveData<Album>()

    val songs: LiveData<List<Song>>
        get() = _songs

    val album: LiveData<Album>
        get() = _albums

    fun setAlbum(albums: Album) {
        _albums.postValue(albums)
    }

    fun extractSongs(album: Album, songs: List<Song>?) {
        songs?.let {
            val songList = mutableListOf<Song>()
            for (songId in album.songs) {
                val songIndex = songs.indexOfFirst {
                    it.id == songId
                }
                if (songIndex != -1) {
                    songList.add(songs[songIndex])
                }
            }
            _songs.value = songList
        }
    }
}