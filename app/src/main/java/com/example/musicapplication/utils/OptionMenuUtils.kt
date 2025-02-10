package com.example.musicapplication.utils

import com.example.musicapplication.ui.dialog.MenuItem
import net.braniumacademy.musicapplication.R

object OptionMenuUtils {

    private val menuItem = mutableListOf<MenuItem>()

    init {
        createOptionMenuItem()
    }

    private fun createOptionMenuItem() {
        menuItem.add(MenuItem(OptionMenu.DOWNLOAD, R.drawable.ic_download, R.string.item_download))
        menuItem.add(MenuItem(OptionMenu.ADD_TO_FAVOURITE, R.drawable.ic_favorite, R.string.item_favorite))
        menuItem.add(MenuItem(OptionMenu.ADD_TO_PLAYLIST, R.drawable.ic_playlist, R.string.item_playlist))
        menuItem.add(MenuItem(OptionMenu.ADD_TO_QUEUE, R.drawable.ic_queue, R.string.item_add_to_queue))
        menuItem.add(MenuItem(OptionMenu.VIEW_ALBUM, R.drawable.ic_album_black, R.string.item_view_album))
        menuItem.add(MenuItem(OptionMenu.VIEW_ARTIST, R.drawable.ic_artist, R.string.item_view_artist))
        menuItem.add(MenuItem(OptionMenu.BLOCK, R.drawable.ic_block, R.string.item_block))
        menuItem.add(MenuItem(OptionMenu.REPORT_ERROR, R.drawable.ic_report, R.string.item_report_error))


    }

    @JvmStatic
    val songOptionMenuItem: List<MenuItem>
        get() = menuItem


    enum class OptionMenu(val value: String) {
        DOWNLOAD("download"),
        ADD_TO_FAVOURITE("add_to_favourite"),
        ADD_TO_PLAYLIST("add_to_playlist"),
        ADD_TO_QUEUE("add_to_queue"),
        VIEW_ALBUM("view_album"),
        VIEW_ARTIST("view_artist"),
        BLOCK("block"),
        REPORT_ERROR("report_error"),
        VIEW_SONG_INFORMATION("view_song_information")
    }
}