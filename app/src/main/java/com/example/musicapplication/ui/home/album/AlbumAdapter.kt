package com.example.musicapplication.ui.home.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicapplication.data.model.album.Album
import net.braniumacademy.musicapplication.R
import net.braniumacademy.musicapplication.databinding.ItemAlbumBinding

class AlbumAdapter(
    private val listener: OnAlbumClickListener
) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    private val albums = mutableListOf<Album>()

    class ViewHolder(
        private val binding: ItemAlbumBinding,
        private val listener: OnAlbumClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            binding.textAlbumItemName.text = album.name
            Glide.with(binding.root.context)
                .load(album.artwork)
                .error(R.drawable.ic_album_default)
                .into(binding.imageAlbumItem)
            binding.root.setOnClickListener {
                listener.onAlbumClick(album)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAlbumBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    fun updateAlbums(newAlbums: List<Album>?) {
        newAlbums?.let {
            val oldAlbum = albums.size
            albums.clear()
            albums.addAll(newAlbums)
            if(oldAlbum > albums.size){
                notifyItemRangeRemoved(0, oldAlbum)
            }
            notifyItemRangeChanged(0, newAlbums.size)
        }
    }

    interface OnAlbumClickListener {
        fun onAlbumClick(album: Album)
    }
}