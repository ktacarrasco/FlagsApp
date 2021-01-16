package com.example.anchorbooks

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Dao
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import com.example.anchorbooks.db.DaoBooks
import com.example.anchorbooks.pojo.Books
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BooksDBTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var barDao: DaoBooks
    private lateinit var database : RoomDatabase



    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertBooks_empty() = runBlocking {
        // Given
        val countryList = listOf<Books>()



        // Then
        barDao.getAllBooksList().observeForever {

            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertBooks_1books() = runBlocking {
        // Given
        val booksList = listOf(Books(1, "author", "country"))


        }
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Truth.assertThat("com.example.anchorbooks").isEqualTo(appContext.packageName)
    }
}