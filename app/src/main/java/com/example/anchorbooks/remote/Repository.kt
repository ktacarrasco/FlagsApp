package com.example.anchorbooks.remote

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.anchorbooks.db.RoomBooks
import com.example.anchorbooks.pojo.Books
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(context: Context) {

    private val tag = "BooksRepository"

    //esto viene  de la Base de datos
    private val db: RoomBooks = RoomBooks.getDatabase(context)
    private val booksList = db.barDao().getAllBooksList()
   // private val cocktailsFavList = db.barDao().getAllFavcocktailsList()

    fun passLiveDataToViewModel(): LiveData<List<Books>> {
        return booksList
    }

  /*  fun passLiveFavDataToViewModel(): LiveData<List<Cocktails>> {
        return cocktailsFavList
    }*/

    fun  passIdtoFragment(id :Int): LiveData<Books> {

        return  db.barDao().getIdList(id)
    }

    suspend fun updateFav(books: Books){
        db.barDao().updateFav(books)
    }



    // esto hace la llamada a retrofit
    fun fetchDataFromServer() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getBooks()



        call.enqueue(object : Callback<List<Books>> {


            override fun onResponse(call: Call<List<Books>>, response: Response<List<Books>>) {
                Log.d(tag, response.body().toString())
                CoroutineScope(Dispatchers.IO).launch {

                    db.barDao().insertAll(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Books>>, t: Throwable) {
                Log.d(tag, t.message.toString())
            }
        })

    }




}