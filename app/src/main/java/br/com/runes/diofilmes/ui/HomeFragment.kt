package br.com.runes.diofilmes.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.runes.diofilmes.R
import br.com.runes.diofilmes.core.createDialog
import br.com.runes.diofilmes.core.createProgressDialog
import br.com.runes.diofilmes.data.model.Movie
import br.com.runes.diofilmes.databinding.FragmentHomeBinding
import br.com.runes.diofilmes.presentation.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home), MovieClickListener {
    private val dialog by lazy { requireContext().createProgressDialog() }
    private val viewModel by viewModel<HomeViewModel>()
    private val adapter by lazy { MovieListAdapter(this@HomeFragment) }
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        setRecyclerView()
        loadMovies()
    }

    private fun setRecyclerView() {
        binding.moviesList.apply {
            adapter = this@HomeFragment.adapter
            setHasFixedSize(true)
        }
    }

    private fun loadMovies() {
        viewModel.movies.observe(viewLifecycleOwner) {
            when (it) {
                HomeViewModel.State.Loading -> dialog.show()
                is HomeViewModel.State.Error -> {
                    requireContext().createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is HomeViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.list)
                }
            }
        }
    }

    override fun itemSelected(item: Movie) {
        findNavController().navigate(HomeFragmentDirections.navToMovieDetailFragment(movieId = item.id))
    }
}