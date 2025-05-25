package com.kliachenko.domain.model

data class CategoryDomain(
    private val categoryId: String,
    private val categoryName: String,
    private val booksCount: Int,
    private val updatePeriod: String,
) : MapCategory {
    override fun <T : Any> map(mapper: CategoryMapper<T>) =
        mapper.map(categoryId, categoryName, booksCount, updatePeriod)
}

interface MapCategory {

    fun <T : Any> map(mapper: CategoryMapper<T>): T
}

interface CategoryMapper<T : Any> {

    fun map(
        categoryId: String,
        categoryName: String,
        booksCount: Int,
        updatePeriod: String
    ): T
}


