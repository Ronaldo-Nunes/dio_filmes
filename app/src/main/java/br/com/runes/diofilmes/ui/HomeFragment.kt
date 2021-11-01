package br.com.runes.diofilmes.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.runes.diofilmes.R
import br.com.runes.diofilmes.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
    }
}