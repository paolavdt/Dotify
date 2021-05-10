package edu.pvdt.dotify

import androidx.recyclerview.widget.DiffUtil
import edu.pvdt.dotify.model.Song

class SongDiffCallback(private val newSongs: List<Song>, private val oldSongs: List<Song>): DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newSong = newSongs[newItemPosition]
        val oldSong = oldSongs[oldItemPosition]

        return newSong.id == oldSong.id
    }

    override fun getOldListSize(): Int = oldSongs.size

    override fun getNewListSize(): Int = newSongs.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newSong = newSongs[newItemPosition]
        val oldSong = oldSongs[oldItemPosition]

        return newSong.title == oldSong.title && newSong.artist == oldSong.artist
    }
}