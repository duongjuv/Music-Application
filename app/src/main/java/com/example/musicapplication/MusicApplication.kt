package com.example.musicapplication

import android.app.Application
import android.content.ComponentName
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.example.musicapplication.ui.playing.PlaybackService
import com.example.musicapplication.ui.viewmodel.MediaPlayerViewModel
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import java.util.concurrent.ExecutionException

class MusicApplication : Application() {
    private lateinit var controllerFuture: ListenableFuture<MediaController>
    private var mediaController: MediaController? = null

    override fun onCreate() {
        super.onCreate()
        createMediaPlayer()
    }

    private fun createMediaPlayer() {
        val sessionToke = SessionToken(
            applicationContext,
            ComponentName(applicationContext, PlaybackService::class.java)
        )

        controllerFuture = MediaController.Builder(applicationContext, sessionToke).buildAsync()
        controllerFuture.addListener({
            if (controllerFuture.isDone && !controllerFuture.isCancelled) {
                try {
                    mediaController = controllerFuture.get()
                    mediaController?.let {
                        MediaPlayerViewModel.instance.setMediaPlayer(it)
                    }
                } catch (ignored: ExecutionException) {

                } catch (ignored: InterruptedException) {

                }
            } else {
                mediaController = null
            }
        }, MoreExecutors.directExecutor())
    }
}