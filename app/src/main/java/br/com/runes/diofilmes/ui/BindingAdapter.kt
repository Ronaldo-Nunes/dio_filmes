package br.com.runes.diofilmes.ui

import android.icu.text.SimpleDateFormat
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.google.android.material.chip.Chip
import java.util.*

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setWideImage")
    fun ImageView.setWideImage(imageUrl: String?) {
        if (!imageUrl.isNullOrBlank()) {
            load(imageUrl) {
                crossfade(true)
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
        }
    }

    @JvmStatic
    @BindingAdapter("setDate")
    fun Chip.setFormattedDate(date: String?) {
        if (!date.isNullOrBlank()) {
            val actualDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dateActual = actualDateFormat.parse(date)
            text = dateFormat.format(dateActual)
        }
    }
}