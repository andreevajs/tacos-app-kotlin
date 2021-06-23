package com.example.tacos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tacos.models.Taco
import com.example.tacos.repositories.TacosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TacosViewModel @Inject constructor(
    private val repository: TacosRepository
) : ViewModel() {
    val items = MutableLiveData<List<Taco>>()

    fun loadSavedTacos() {
        GlobalScope.launch(Dispatchers.IO) {
            val tacos = repository.getSavedTacos()
            withContext(Dispatchers.Main) {
                items.value = tacos
            }
        }
    }

    fun deleteTaco(taco : Taco) {
        GlobalScope.launch(Dispatchers.IO) {
            repository.deleteTaco(taco)
        }
        loadSavedTacos()
    }
}