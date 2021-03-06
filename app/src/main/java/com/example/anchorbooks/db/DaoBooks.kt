package com.example.anchorbooks.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.anchorbooks.pojo.Books


@Dao
interface DaoBooks {

    //Insertar un listado
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(listBooks: List<Books>)

    //agregar fav
    @Update
    suspend fun updateFav(books: Books)



    // traer todos los elementos de la tabla
    @Query("SELECT * FROM Books_table  ORDER BY title ASC ")
    fun getAllBooksList() : LiveData<List<Books>>

    //traer elemento desde id
    @Query("SELECT * FROM Books_table WHERE id=:mId")
    fun getIdList(mId:Int) : LiveData<Books>
}