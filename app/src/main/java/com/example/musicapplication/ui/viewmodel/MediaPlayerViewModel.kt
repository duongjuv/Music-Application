package com.example.musicapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.media3.session.MediaController

class MediaPlayerViewModel private constructor() : ViewModel() {
    private val _mediaController = MutableLiveData<MediaController?>()
    val mediaController: LiveData<MediaController?>
        get() = _mediaController

    fun setMediaPlayer(controller: MediaController) {
        _mediaController.value = controller
    }

    companion object {
        val instance: MediaPlayerViewModel = MediaPlayerViewModel()
    }
}