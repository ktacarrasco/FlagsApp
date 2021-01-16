package com.example.anchorbooks.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.anchorbooks.pojo.Books
import com.example.anchorbooks.remote.Repository
import kotlinx.coroutines.launch

class MainViewModel  (application: Application) : AndroidViewModel(application) {
    private val repository =  Repository(application)
    private val booksList = repository.passLiveDataToViewModel()
   // private val flagsFavList = repository.passLiveFavDataToViewModel()

    fun fetchFromServer() {
        repository.fetchDataFromServer()
    }

    //agregar favorito
    fun updateFav(books: Books) = viewModelScope.launch {
        repository.updateFav(books)
    }

    //favorito
  /*  fun getFavFromDB(id: Int): LiveData<List<Flags>> {
        return cocktailsFavList
    }*/


    fun getDataFromDB(id: Int): LiveData<List<Books>> {
        return booksList
    }

    fun getIdDataFromDB(id: Int): LiveData<Books> {
        return repository.passIdtoFragment(id)
    }


}