package br.com.runes.diofilmes.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.runes.diofilmes.R
import br.com.runes.diofilmes.core.createDialog
import br.com.runes.diofilmes.core.createProgressDialog
import br.com.runes.diofilmes.databinding.FragmentMovieDetailBinding
import br.com.runes.diofilmes.presentation.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail){
    private lateinit var binding: FragmentMovieDetailBinding
    private val dialog by lazy { requireContext().createProgressDialog() }
    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel by viewModel<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getMovie(args.movieId)
        loadMovie()
        Log.d("MovieDetailFragment", "Movie ID: ${args.movieId}")

        binding.fabBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun loadMovie() {
        viewModel.movie.observe(viewLifecycleOwner) {
            when (it) {
                DetailViewModel.State.Loading -> dialog.show()
                is DetailViewModel.State.Error -> {
                    requireContext().createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is DetailViewModel.State.Success -> {
                    dialog.dismiss()
                    binding.movie = it.movie
                }
            }
        }
    }

}