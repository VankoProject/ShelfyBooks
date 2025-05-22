package com.kliachenko.data.localCache

import com.kliachenko.data.localCache.dao.MetaInfoDao
import com.kliachenko.data.localCache.entity.MetaInfoCache
import javax.inject.Inject

class MetaInfoCacheDataSource {

    interface Save {
        suspend fun save(date: String)
    }

    interface Read {
        suspend fun read(): String
    }

    interface Mutable : Save, Read

    class Base @Inject constructor(
        private val dao: MetaInfoDao
    ) : Mutable {
        override suspend fun save(date: String) {
            dao.insert(MetaInfoCache(PUBLISHED_DATE, date))
        }

        override suspend fun read() = dao.value(PUBLISHED_DATE)

        private companion object {
            private const val PUBLISHED_DATE = "published_date"
        }
    }

}