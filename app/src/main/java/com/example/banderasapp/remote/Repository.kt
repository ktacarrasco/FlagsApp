package com.example.banderasapp.remote

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.banderasapp.db.RoomFlags
import com.example.banderasapp.pojo.Flags
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class Repository(context: Context) {

    private val tag = "FlagsRepository"

    //esto viene  de la Base de datos
    private val db: RoomFlags = RoomFlags.getDatabase(context)
    private val flagsList = db.barDao().getAllFlagsList()
   // private val cocktailsFavList = db.barDao().getAllFavcocktailsList()

    fun passLiveDataToViewModel(): LiveData<List<Flags>> {
        return flagsList
    }

  /*  fun passLiveFavDataToViewModel(): LiveData<List<Cocktails>> {
        return cocktailsFavList
    }*/

    fun  passIdtoFragment(id :Int): LiveData<Flags> {

        return  db.barDao().getIdList(id)
    }

    suspend fun updateFav( flags: Flags){
        db.barDao().updateFav(flags)
    }



    // esto hace la llamada a retrofit
    fun fetchDataFromServer() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getFlags()



        call.enqueue(object : Callback<List<Flags>> {
            override fun onResponse(call: Call<List<Flags>>, response: Response<List<Flags>>) {
                Log.d(tag, response.body().toString())
                CoroutineScope(Dispatchers.IO).launch {

                    db.barDao().insertAll(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Flags>>, t: Throwable) {
                Log.d(tag, t.message.toString())

            }
        })

    }




}