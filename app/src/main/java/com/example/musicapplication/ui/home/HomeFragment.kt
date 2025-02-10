package com.example.musicapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.musicapplication.ui.home.album.AlbumHotViewModel
import com.example.musicapplication.ui.home.recommended.RecommendedViewModel
import net.braniumacademy.musicapplication.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding
    private val homeView: HomeViewModel by activityViewModels()
    private val albumViewModel: AlbumHotViewModel by activityViewModels()
    private val songViewModel: RecommendedViewModel by activityViewModels()
    private var isObserver = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(!isObserver){
            setupObserver()
            isObserver = true
        }

    }

    private fun setupObserver() {
        homeView.albums.observe(viewLifecycleOwner) {
            albumViewModel.setAlbums(it)

        }
        homeView.songs.observe(viewLifecycleOwner) {
            songViewModel.setSongs(it)
        }
    }

}