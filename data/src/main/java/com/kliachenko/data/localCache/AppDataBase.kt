package com.kliachenko.data.localCache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kliachenko.data.localCache.dao.BookDao
import com.kliachenko.data.localCache.dao.CategoryDao
import com.kliachenko.data.localCache.dao.MetaInfoDao
import com.kliachenko.data.localCache.dao.SellerDao
import com.kliachenko.data.localCache.entity.BookCache
import com.kliachenko.data.localCache.entity.CategoryCache
import com.kliachenko.data.localCache.entity.MetaInfoCache
import com.kliachenko.data.localCache.entity.SellerLinkCache

@Database(
    entities = [CategoryCache::class, BookCache::class, SellerLinkCache::class, MetaInfoCache::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun bookDao(): BookDao

    abstract fun metaInfoDao(): MetaInfoDao

    abstract fun sellerDao(): SellerDao

}