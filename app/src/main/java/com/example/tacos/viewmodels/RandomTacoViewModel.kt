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
class RandomTacoViewModel @Inject constructor(
    private val repository: TacosRepository
) : ViewModel()  {
    val taco = MutableLiveData<Taco>()

    fun randomizeTaco() {
        GlobalScope.launch(Dispatchers.IO) {
            val randomTaco = repository.getRandomTaco()
            withContext(Dispatchers.Main) {
                taco.value = randomTaco!!
            }
        }
    }

    fun saveTaco() {
        if (taco.value != null) {
            GlobalScope.launch(Dispatchers.IO) {
                repository.saveTaco(taco.value!!)
            }
        }
    }
}