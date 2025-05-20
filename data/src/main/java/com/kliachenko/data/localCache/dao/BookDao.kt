package com.kliachenko.data.localCache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.kliachenko.data.localCache.entity.BookCache
import com.kliachenko.data.localCache.entity.BookWithSellers

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(books: List<BookCache>): List<Long>

    @Transaction
    @Query("Select * From books Where category_list_name =:categoryListName Order by rank ASC")
    suspend fun booksWithSellers(categoryListName: String): List<BookWithSellers>

}