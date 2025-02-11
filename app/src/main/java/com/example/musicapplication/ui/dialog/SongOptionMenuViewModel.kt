package com.example.musicapplication.ui.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicapplication.data.model.song.Song
import com.example.musicapplication.utils.OptionMenuUtils

class SongOptionMenuViewModel : ViewModel() {
    private val _song = MutableLiveData<Song>()
    private val _optionMenuItem = MutableLiveData<List<MenuItem>>()

    val song: LiveData<Song> = _song
    val optionMenuItem: LiveData<List<MenuItem>> = _optionMenuItem

    init {
        _optionMenuItem.value = OptionMenuUtils.songOptionMenuItem
    }

    fun setSong(song: Song) {
        _song.value = song
    }
}