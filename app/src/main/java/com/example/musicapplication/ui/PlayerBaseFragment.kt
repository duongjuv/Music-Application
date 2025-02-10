package com.example.musicapplication.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.musicapplication.data.model.song.Song
import com.example.musicapplication.ui.dialog.SongOptionMenuDialogFragment
import com.example.musicapplication.ui.dialog.SongOptionMenuViewModel

open class PlayerBaseFragment : Fragment() {

    protected fun showOptionMenu(song: Song) {
        val menuDialogFragment = SongOptionMenuDialogFragment.newInstance
        val menuDialogViewModel: SongOptionMenuViewModel by activityViewModels()
        menuDialogViewModel.setSong(song)
        menuDialogFragment.show(
            requireActivity().supportFragmentManager,
            SongOptionMenuDialogFragment.TAG
        )
    }
}