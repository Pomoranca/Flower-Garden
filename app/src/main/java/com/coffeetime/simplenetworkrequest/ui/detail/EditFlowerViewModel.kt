package com.coffeetime.simplenetworkrequest.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coffeetime.simplenetworkrequest.network.Flower

class EditFlowerViewModel(flower: Flower, app: Application) : AndroidViewModel(app) {

    private val _selectedFlower = MutableLiveData<Flower>()

    val selectedFlower: LiveData<Flower>
        get() = _selectedFlower



    init {
        _selectedFlower.value = flower
    }




}