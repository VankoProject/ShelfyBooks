package com.kliachenko.data.localCache

import com.kliachenko.data.localCache.dao.SellerDao
import com.kliachenko.data.localCache.entity.SellerLinkCache
import javax.inject.Inject

class SellersCacheDataSource {

    interface Save {
        suspend fun save(sellers: List<SellerLinkCache>)
    }

    interface Mutable : Save

    class Base @Inject constructor(
        private val sellerDao: SellerDao,
    ) : Mutable {

        override suspend fun save(sellers: List<SellerLinkCache>) {
            sellerDao.insert(sellers)
        }
    }
}