package com.kliachenko.data.localCache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kliachenko.data.localCache.entity.CategoryCache

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categories: List<CategoryCache>)

    @Query("Select * From categories")
    suspend fun categories(): List<CategoryCache>

}