package com.example.musicapplication.ui.home.recommended

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.musicapplication.data.model.song.Song

class RecommendedViewModel : ViewModel() {
    private val _songs = MutableLiveData<List<Song>>()
    val songs: LiveData<List<Song>>
        get() = _songs

    fun setSongs(songs: List<Song>) {
        _songs.postValue(songs)
    }
}