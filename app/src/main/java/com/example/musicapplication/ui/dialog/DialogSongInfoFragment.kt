package com.example.musicapplication.ui.dialog

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import net.braniumacademy.musicapplication.R

class DialogSongInfoFragment : BottomSheetDialogFragment() {

    private val viewModel: DialogSongInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_dialog_song_info, container, false)
    }

    companion object {
        fun newInstance() = DialogSongInfoFragment()
        val TAG: String = DialogSongInfoFragment::class.java.simpleName
    }
}