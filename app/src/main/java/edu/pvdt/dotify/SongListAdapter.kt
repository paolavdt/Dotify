package edu.pvdt.dotify

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import edu.pvdt.dotify.databinding.ItemSongBinding
import edu.pvdt.dotify.model.Song

class SongListAdapter(private var listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {
    var onSongClickListener: (song: Song) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context))
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song: Song = listOfSongs[position]
        with(holder.binding) {
            ivItemSongCover.load(song.smallImageURL)
            tvItemSongTitle.text = song.title
            tvItemSongArtist.text = song.artist

            itemRoot.setOnClickListener{
                onSongClickListener(song)
            }
        }
    }

    override fun getItemCount(): Int = listOfSongs.size

    fun shuffleSongs(newListOfSongs: List<Song>) {
        val callback = SongDiffCallback(newListOfSongs, listOfSongs)
        val result = DiffUtil.calculateDiff(callback)
        result.dispatchUpdatesTo(this)

        this.listOfSongs = newListOfSongs
    }

    class SongViewHolder(val binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root)
}