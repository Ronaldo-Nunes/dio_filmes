package br.com.runes.diofilmes.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.runes.diofilmes.databinding.MovieItemLayoutBinding
import br.com.runes.diofilmes.data.model.Movie
import br.com.runes.diofilmes.data.services.ApiPath.BASE_PORTRAIT_IMAGE_URL
import coil.load

class MovieListAdapter(private val clickListener: MovieClickListener) :
    ListAdapter<Movie, MovieListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    inner class ViewHolder(
        private val binding: MovieItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie, clickListener: MovieClickListener) {
            val imageUrl = "$BASE_PORTRAIT_IMAGE_URL${item.posterPath}"
            Log.d("ADAPTER", "ImageUrl: $imageUrl")
            binding.movieImage.load(imageUrl)
            binding.root.setOnClickListener {
                clickListener.itemSelected(item)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
}