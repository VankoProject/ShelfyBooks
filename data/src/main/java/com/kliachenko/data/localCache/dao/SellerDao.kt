package com.kliachenko.data.localCache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.kliachenko.data.localCache.entity.SellerLinkCache

@Dao
interface SellerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sellers: List<SellerLinkCache>)

}