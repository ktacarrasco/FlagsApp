package com.example.banderasapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banderasapp.pojo.Flags
import com.example.banderasapp.remote.Repository
import kotlinx.coroutines.launch

class MainViewModel  (application: Application) : AndroidViewModel(application) {
    private val repository =  Repository(application)
    private val flagsList = repository.passLiveDataToViewModel()
   // private val flagsFavList = repository.passLiveFavDataToViewModel()

    fun fetchFromServer() {
        repository.fetchDataFromServer()
    }

    //agregar favorito
    fun updateFav(flags: Flags) = viewModelScope.launch {
        repository.updateFav(flags)
    }

    //favorito
  /*  fun getFavFromDB(id: Int): LiveData<List<Flags>> {
        return cocktailsFavList
    }*/


    fun getDataFromDB(id: Int): LiveData<List<Flags>> {
        return flagsList
    }

    fun getIdDataFromDB(id: Int): LiveData<Flags> {
        return repository.passIdtoFragment(id)
    }


}