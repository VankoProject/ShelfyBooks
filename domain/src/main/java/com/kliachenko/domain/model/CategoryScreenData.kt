package com.kliachenko.domain.model

data class CategoryScreenData(
    private val publishedDate: String,
    private val categories: List<CategoryDomain>
) : MapScreenData {
    override fun <T : Any> map(mapper: ScreenDataMapper<T>) = mapper.map(publishedDate, categories)
}

interface MapScreenData {

    fun <T : Any> map(mapper: ScreenDataMapper<T>): T
}

interface ScreenDataMapper<T : Any> {

    fun map(
        publishedDate: String,
        categories: List<CategoryDomain>
    ): T

}