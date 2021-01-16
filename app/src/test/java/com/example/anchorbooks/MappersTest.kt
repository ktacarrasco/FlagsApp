package com.example.anchorbooks

import com.example.anchorbooks.pojo.Books
import org.junit.Test

class MappersTest {

    @Test
    fun mapperCountryApi2DB_happyCase() {
        // Given
        val books = Books(1, "author", "country")

        // When
        val response = mapperCountryApi2DB_happyCase(Books)

        // Then
        assert(response.author == books.author)
        assert(response.country == books.country)

    }

}