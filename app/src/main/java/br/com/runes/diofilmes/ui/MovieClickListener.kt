package br.com.runes.diofilmes.ui

import br.com.runes.diofilmes.data.model.Movie

interface MovieClickListener {
    fun itemSelected(item: Movie)
}
