package com.kliachenko.data.mapper

import com.kliachenko.data.localCache.entity.CategoryCache
import com.kliachenko.domain.model.CategoryDomain
import javax.inject.Inject

interface CategoryMapper<T : Any> {

    fun map(
        categoryId: String,
        categoryName: String,
        booksCount: Int,
        updatedPeriod: String,
    ): T

    interface ToCache : CategoryMapper<CategoryCache> {
        class Base @Inject constructor() : ToCache {
            override fun map(
                categoryId: String,
                categoryName: String,
                booksCount: Int,
                updatedPeriod: String,
            ) = CategoryCache(
                categoryId = categoryId,
                categoryName = categoryName,
                booksCount = booksCount,
                updatePeriod = updatedPeriod
            )
        }
    }

    interface ToDomain : CategoryMapper<CategoryDomain> {
        class Base @Inject constructor() : ToDomain {
            override fun map(
                categoryId: String,
                categoryName: String,
                booksCount: Int,
                updatedPeriod: String
            ) = CategoryDomain(
                categoryId = categoryId,
                categoryName = categoryName,
                booksCount = booksCount,
                updatePeriod = updatedPeriod,
            )
        }
    }

}