package com.kliachenko.data.mapper

import com.kliachenko.data.localCache.entity.CategoryCache
import javax.inject.Inject

interface CategoryMapper<T : Any> {

    fun map(
        listName: String,
        publishedDate: String,
        categoryName: String,
        booksCount: Int,
        updatedPeriod: String,
    ): T

    interface ToCache : CategoryMapper<CategoryCache> {
        class Base @Inject constructor(): ToCache {
            override fun map(
                listName: String,
                publishedDate: String,
                categoryName: String,
                booksCount: Int,
                updatedPeriod: String,
            ) = CategoryCache(
                listName = listName,
                publishedDate = publishedDate,
                categoryName = categoryName,
                booksCount = booksCount,
                updatePeriod = updatedPeriod
            )
        }
    }

    interface ToDomain: CategoryMapper<CategoryDomain> {
        class Base @Inject constructor(): ToDomain {
            override fun map(
                listName: String,
                publishedDate: String,
                categoryName: String,
                booksCount: Int,
                updatedPeriod: String
            ) = CategoryDomain(
                categoryName = categoryName,
                booksCount = booksCount,
                updatePeriod = updatedPeriod,
                publisherDate = publishedDate
            )

        }
    }

}

data class CategoryDomain(
    private val categoryName: String,
    private val booksCount: Int,
    private val updatePeriod: String,
    private val publisherDate: String
)