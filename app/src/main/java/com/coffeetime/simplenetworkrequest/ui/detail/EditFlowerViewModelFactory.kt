package com.coffeetime.simplenetworkrequest.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coffeetime.simplenetworkrequest.network.Flower

class EditFlowerViewModelFactory(
    private val flower: Flower,
    private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EditFlowerViewModel::class.java)) {
                return EditFlowerViewModel(
                    flower,
                    application
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}