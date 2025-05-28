package com.kliachenko.presentation.categories.models

interface CategoryScreenUiData {

    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {

        fun map(publishedDate: String, categories: List<CategoryUi>): T
    }

    data class Base(
        private val publishedDate: String,
        private val categories: List<CategoryUi>
    ) : CategoryScreenUiData {

        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(publishedDate, categories)
        }
    }

}