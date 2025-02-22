package com.example.musicapplication.ui.playing

import android.animation.Animator
import android.animation.AnimatorInflater
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import com.bumptech.glide.Glide
import com.example.musicapplication.data.model.song.Song
import com.example.musicapplication.ui.viewmodel.MediaPlayerViewModel
import com.example.musicapplication.ui.viewmodel.SharedViewModel
import net.braniumacademy.musicapplication.R
import net.braniumacademy.musicapplication.databinding.FragmentMiniPlayerBinding

class MiniPlayerFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentMiniPlayerBinding
    private val viewModel: MiniPlayerViewModel by activityViewModels()
    private var mediaController: MediaController? = null
    private lateinit var pressedAnimator: Animator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMiniPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupAnimator()
        setupMediaController()
        setupObserve()
    }

    override fun onClick(v: View) {
        pressedAnimator.setTarget(v)
        pressedAnimator.start()

        when (v) {
            binding.btnMiniPlayerPlayPause -> {
                mediaController?.let {
                    if (it.isPlaying) {
                        it.pause()
                    } else {
                        it.play()
                    }
                }
            }

            binding.btnMiniPlayerSkipNext -> {
                // todo
            }

            binding.btnMiniPlayerFavorite -> {
                // todo
            }
        }
    }

    private fun setupView() {
        binding.btnMiniPlayerFavorite.setOnClickListener(this)
        binding.btnMiniPlayerPlayPause.setOnClickListener(this)
        binding.btnMiniPlayerSkipNext.setOnClickListener(this)
    }

    private fun setupAnimator() {
        pressedAnimator = AnimatorInflater.loadAnimator(requireContext(), R.animator.button_pressed)
    }

    private fun setupMediaController() {
        MediaPlayerViewModel.instance.mediaController.observe(viewLifecycleOwner) { controller ->
            controller?.let {
                mediaController = it
                setupListener()
            }
        }
    }

    private fun setupListener() {
        mediaController?.let {
            it.addListener(object : Player.Listener {
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    viewModel.setPlayingState(isPlaying)
                }
            })
        }
    }

    private fun setupObserve() {
        SharedViewModel.instance.playingSong.observe(viewLifecycleOwner) {
            it.song?.let { song ->
                showSongInfo(song)
            }
        }

        SharedViewModel.instance.currentPlaylist.observe(viewLifecycleOwner) {
            viewModel.setMediaItem(it.mediaItems)
        }
        viewModel.mediaItems.observe(viewLifecycleOwner) { mediaItems ->
            mediaController?.   setMediaItems(mediaItems)
        }
        SharedViewModel.instance.indexToPlay.observe(viewLifecycleOwner) { index->
            if(index > -1 && mediaController != null && mediaController!!.mediaItemCount > index) {
                mediaController!!.seekTo(index, 0)
                mediaController!!.prepare()
                mediaController!!.play()
            }
        }
        viewModel.isPlaying.observe(viewLifecycleOwner) {
            if (it) { // playing
                binding.btnMiniPlayerPlayPause.setImageResource(R.drawable.ic_pause_circle)
            } else {
                binding.btnMiniPlayerPlayPause.setImageResource(R.drawable.ic_play_circle)
            }
        }
    }

    private fun showSongInfo(song: Song) {
        binding.textMiniPlayerTitle.text = song.title
        binding.textMiniPlayerArtist.text = song.artist
        Glide.with(requireContext())
            .load(song.image)
            .error(R.drawable.ic_album_default)
            .circleCrop()
            .into(binding.imageMiniPlayerArtwork)
    }
}