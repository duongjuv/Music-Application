package com.example.musicapplication.ui.home.album.more

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.musicapplication.data.model.album.Album
import com.example.musicapplication.ui.home.HomeViewModel
import com.example.musicapplication.ui.home.album.AlbumAdapter
import com.example.musicapplication.ui.home.album.AlbumHotViewModel
import com.example.musicapplication.ui.home.album.detail.DetailAlbumFragment
import com.example.musicapplication.ui.home.album.detail.DetailAlbumViewModel
import net.braniumacademy.musicapplication.R
import net.braniumacademy.musicapplication.databinding.FragmentMoreAlbumBinding

class MoreAlbumFragment : Fragment() {
    private lateinit var binding: FragmentMoreAlbumBinding
    private lateinit var adapter: MoreAlbumAdapter
    private val albumViewModel: AlbumHotViewModel by activityViewModels()
    private val detailAlbumViewModel: DetailAlbumViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val moreAlbumViewModel: MoreAlbumViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoreAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        binding.toolbarMoreAlbum.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        albumViewModel.albums.observe(viewLifecycleOwner) { albums ->
            moreAlbumViewModel.setAlbums(albums)
        }

        adapter = MoreAlbumAdapter(object : AlbumAdapter.OnAlbumClickListener {
            override fun onAlbumClick(album: Album) {
                detailAlbumViewModel.setAlbum(album)
                val songs = homeViewModel.songs.value
                detailAlbumViewModel.extractSongs(album, songs)
                navigateToDetailAlbum()
            }
        })
        binding.rvMoreAlbum.adapter = adapter
    }

    private fun setupViewModel() {
        moreAlbumViewModel.albums.observe(viewLifecycleOwner) { albums ->
            adapter.updateData(albums)
        }
    }

    private fun navigateToDetailAlbum() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment_activity_main, DetailAlbumFragment::class.java, null)
            .addToBackStack(null)
            .commit()
    }
}