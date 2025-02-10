package com.example.musicapplication.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicapplication.data.model.song.Song
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import net.braniumacademy.musicapplication.R
import net.braniumacademy.musicapplication.databinding.DialogFragmentSongOptionMenuBinding
import net.braniumacademy.musicapplication.databinding.ItemOptionMenuBinding

class SongOptionMenuDialogFragment : BottomSheetDialogFragment() {
    private lateinit var binding: DialogFragmentSongOptionMenuBinding
    private lateinit var adapter: MenuItemAdapter
    private val viewmodel: SongOptionMenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogFragmentSongOptionMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObserver()
    }

    private fun setupView() {
        adapter =
            MenuItemAdapter(listener = object : MenuItemAdapter.OnOptionMenuItemClickListener {
                override fun onClick(item: MenuItem) {
                    // todo
                }
            })
        binding.rvOptionMenu.adapter = adapter
    }

    private fun setupObserver() {
        viewmodel.optionMenuItem.observe(viewLifecycleOwner) { items ->
            adapter.updateMenuItem(items)
        }
        viewmodel.song.observe(viewLifecycleOwner) { song ->
            showSongInformation(song)
        }
    }

    private fun showSongInformation(song: Song) {
        binding.includeSongBottomSheet.textOptionItemSongTitle.text = song.title
        binding.includeSongBottomSheet.textOptionItemSongArtist.text = song.artist
        Glide.with(binding.root.context)
            .load(song.image)
            .error(R.drawable.ic_album_black)
            .into(binding.includeSongBottomSheet.imageOptionSongArtwork)
    }

    class MenuItemAdapter(
        private val menuItem: MutableList<MenuItem> = mutableListOf(),
        private val listener: OnOptionMenuItemClickListener
    ) : RecyclerView.Adapter<MenuItemAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemOptionMenuBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding, listener)
        }

        override fun getItemCount(): Int {
            return menuItem.size
        }

        fun updateMenuItem(item: List<MenuItem>) {
            menuItem.addAll(item)
            notifyItemRangeChanged(0, menuItem.size)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(menuItem[position])
        }

        class ViewHolder(
            private val binding: ItemOptionMenuBinding,
            private val listener: OnOptionMenuItemClickListener
        ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(item: MenuItem) {
                val title = binding.root.context.getString(item.menuItemTitle)
                binding.textItemMenuTitle.text = title
                Glide.with(binding.root.context)
                    .load(item.iconId)
                    .into(binding.imageItemMenuIcon)
                binding.root.setOnClickListener {
                    listener.onClick(item)
                }
            }
        }

        interface OnOptionMenuItemClickListener {
            fun onClick(item: MenuItem)
        }
    }

    companion object {
        val newInstance = SongOptionMenuDialogFragment()
        val TAG = SongOptionMenuDialogFragment::class.java.simpleName
    }
}