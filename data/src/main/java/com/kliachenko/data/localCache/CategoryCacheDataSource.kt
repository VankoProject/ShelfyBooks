package com.kliachenko.data.localCache

import com.kliachenko.data.localCache.dao.CategoryDao
import com.kliachenko.data.localCache.entity.CategoryCache
import javax.inject.Inject

class CategoryCacheDataSource {

    interface Save {
        suspend fun save(categories: List<CategoryCache>)
    }

    interface Read {
        suspend fun read(): List<CategoryCache>
    }

    interface Mutable : Save, Read

    class Base @Inject constructor(
        private val categoryDao: CategoryDao
    ) : Mutable {
        override suspend fun save(categories: List<CategoryCache>) {
            categoryDao.insert(categories)
        }

        override suspend fun read() = categoryDao.categories()

    }

}