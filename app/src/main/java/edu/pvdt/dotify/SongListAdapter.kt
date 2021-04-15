package edu.pvdt.dotify

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import edu.pvdt.dotify.databinding.ItemSongBinding

class SongListAdapter(private var listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {
    var onSongClickListener: (songTitle: String, songArtist: String) -> Unit = { _, _ ->  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context))
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = listOfSongs[position]
        with(holder.binding) {
            ivItemSongCover.setImageResource(song.smallImageID)
            tvItemSongTitle.text = song.title
            tvItemSongArtist.text = song.artist

            itemRoot.setOnClickListener{
                onSongClickListener(song.title, song.artist)
            }
        }
    }

    override fun getItemCount(): Int = listOfSongs.size

    fun shuffleSongs(newListOfSongs: List<Song>) {
        this.listOfSongs = newListOfSongs
        notifyDataSetChanged()
    }

    class SongViewHolder(val binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root)
}