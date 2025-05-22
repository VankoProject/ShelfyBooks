package com.kliachenko.data.localCache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kliachenko.data.localCache.entity.MetaInfoCache

@Dao
interface MetaInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(info: MetaInfoCache)

    @Query("Select value From meta_info Where `key` =:key")
    suspend fun value(key: String): String

}