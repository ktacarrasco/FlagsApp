package com.example.banderasapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.banderasapp.pojo.Flags


@Dao
interface DaoFlags {

    //Insertar un listado
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(listCocktails: Flags)

    //agregar fav
    @Update
    suspend fun updateFav(flags: Flags)

    // traer todos los elementos favoritos
//    @Query("SELECT * FROM Cocktails_table WHERE favStatus=1 ORDER BY name_cocktails ASC")
//    fun getAllFavcocktailsList() : LiveData<List<Flags>>


    // traer todos los elementos de la tabla
    @Query("SELECT * FROM Flags_table  ORDER BY name ASC ")
    fun getAllFlagsList() : LiveData<List<Flags>>

    //traer elemento desde id
    @Query("SELECT * FROM Flags_table WHERE id=:mId")
    fun getIdList(mId:Int) : LiveData<Flags>
}