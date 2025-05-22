package com.kliachenko.data.repository

import com.kliachenko.data.CategoryRepositoryImpl
import com.kliachenko.data.cloud.CategoryCloudDataSource
import com.kliachenko.data.cloud.cloudResponse.BestsellersResult
import com.kliachenko.data.cloud.cloudResponse.CategoryCloud
import com.kliachenko.data.core.HandleError
import com.kliachenko.data.localCache.CategoryCacheDataSource
import com.kliachenko.data.localCache.MetaInfoCacheDataSource
import com.kliachenko.data.localCache.entity.CategoryCache
import com.kliachenko.data.mapper.CategoryMapper
import com.kliachenko.domain.model.CategoryDomain
import com.kliachenko.domain.repository.LoadResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

class CategoryRepositoryTest {

    private lateinit var categoryCloudDataSource: FakeCategoryCloudDataSource
    private lateinit var categoryCacheDataSource: FakeCategoryCacheDataSource
    private lateinit var metaInfoCacheDataSource: FakeMetaInfoCacheDataSource
    private lateinit var mapCategoryToCache: FakeCategoryToCacheMapper
    private lateinit var mapCategoryToDomain: FakeCategoryToDomainMapper
    private lateinit var handleError: FakeHandleError
    private lateinit var repository: CategoryRepositoryImpl

    @Before
    fun setup() {
        categoryCloudDataSource = FakeCategoryCloudDataSource()
        categoryCacheDataSource = FakeCategoryCacheDataSource()
        metaInfoCacheDataSource = FakeMetaInfoCacheDataSource()
        mapCategoryToCache = FakeCategoryToCacheMapper()
        mapCategoryToDomain = FakeCategoryToDomainMapper()
        handleError = FakeHandleError()
        repository = CategoryRepositoryImpl(
            categoryCloudDataSource = categoryCloudDataSource,
            categoryCacheDataSource = categoryCacheDataSource,
            metaInfoCacheDataSource = metaInfoCacheDataSource,
            mapCategoryToCache = mapCategoryToCache,
            mapCategoryToDomain = mapCategoryToDomain,
            handleError = handleError
        )
    }

    @Test
    fun testFirstRunEmptyError() = runBlocking {
        categoryCacheDataSource.cacheEmpty()
        categoryCloudDataSource.errorLoadResult(UnknownHostException())
        val actual = repository.categories().first()
        categoryCloudDataSource.checkCalled(1)
        val expected = LoadResult.Error<String>("No internet connection")
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testFirstRunEmptySuccess() = runBlocking {
        categoryCacheDataSource.cacheEmpty()
        categoryCloudDataSource.successLoadResult()
        val actual = repository.categories().toList()
        categoryCloudDataSource.checkCalled(1)
        val expected = listOf(
            LoadResult.Success(
                listOf(
                    CategoryDomain(
                        categoryId = "cloud_id_1",
                        categoryName = "Cloud Category 1",
                        booksCount = 42,
                        updatePeriod = "WEEKLY"
                    ),
                    CategoryDomain(
                        categoryId = "cloud_id_2",
                        categoryName = "Cloud Category 2",
                        booksCount = 24,
                        updatePeriod = "MONTHLY"
                    )
                )
            )
        )
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testNotFirstRunSuccess() = runBlocking {
        categoryCacheDataSource.cacheNotEmpty()
        val actual = repository.categories().toList()
        categoryCloudDataSource.checkCalled(0)
        val expected = listOf(
            LoadResult.Success(
                listOf(
                    CategoryDomain(
                        categoryId = "cache_id_1",
                        categoryName = "Cache Category 1",
                        booksCount = 101,
                        updatePeriod = "ONCE"
                    ),
                    CategoryDomain(
                        categoryId = "cache_id_2",
                        categoryName = "Cache Category 2",
                        booksCount = 202,
                        updatePeriod = "NEVER"
                    )
                )
            )
        )
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testPublishedDateSavedFromCloud() = runBlocking {
        metaInfoCacheDataSource.cacheEmpty()
        categoryCacheDataSource.cacheEmpty()
        categoryCloudDataSource.successLoadResult()
        repository.categories().first()
        categoryCloudDataSource.checkCalled(1)
        val actual = metaInfoCacheDataSource.read()
        val expected = "2025-01-01"
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun getPublishedDateCorrectly() = runBlocking {
        metaInfoCacheDataSource.cacheNotEmpty()
        val expected = "2025-01-01"
        val actual = repository.publishedDate()
        categoryCloudDataSource.checkCalled(0)
        Assert.assertEquals(expected, actual)
    }
}

private class FakeCategoryCloudDataSource : CategoryCloudDataSource {

    private var isSuccessResult: Boolean = false
    private lateinit var exception: Exception
    private var actualCalledCount: Int = 0

    override suspend fun overview(): BestsellersResult {
        actualCalledCount++
        if (isSuccessResult) {
            return BestsellersResult(
                bestsellersDate = "2025-01-01",
                publishedDate = "2025-01-01",
                categoriesList = listOf(
                    CategoryCloud(
                        categoryId = "cloud_id_1",
                        categoryName = "Cloud Category 1",
                        booksCount = 42,
                        updatePeriod = "WEEKLY"
                    ),
                    CategoryCloud(
                        categoryId = "cloud_id_2",
                        categoryName = "Cloud Category 2",
                        booksCount = 24,
                        updatePeriod = "MONTHLY"
                    )
                )
            )
        } else throw exception
    }

    fun checkCalled(expectedTimes: Int) {
        Assert.assertEquals(expectedTimes, actualCalledCount)
    }

    fun successLoadResult() {
        isSuccessResult = true
    }

    fun errorLoadResult(exception: UnknownHostException) {
        this.exception = exception
    }

}

private class FakeCategoryCacheDataSource : CategoryCacheDataSource.Mutable {

    private var actualCategories: List<CategoryCache> = emptyList()

    override suspend fun save(categories: List<CategoryCache>) {
        actualCategories = categories
    }

    override fun read(): Flow<List<CategoryCache>> = flowOf(actualCategories)

    fun cacheEmpty() {
        actualCategories = emptyList()
    }

    fun cacheNotEmpty() {
        actualCategories = listOf(
            CategoryCache(
                categoryId = "cache_id_1",
                categoryName = "Cache Category 1",
                booksCount = 101,
                updatePeriod = "ONCE"
            ),
            CategoryCache(
                categoryId = "cache_id_2",
                categoryName = "Cache Category 2",
                booksCount = 202,
                updatePeriod = "NEVER"
            )
        )
    }

}

private class FakeMetaInfoCacheDataSource : MetaInfoCacheDataSource.Mutable {

    private var actual: String = ""

    override suspend fun save(date: String) {
        actual = date
    }

    override suspend fun read() = actual

    fun cacheEmpty() {
        actual = ""
    }

    fun cacheNotEmpty() {
        actual = "2025-01-01"
    }

}

private class FakeCategoryToCacheMapper : CategoryMapper.ToCache {
    override fun map(
        categoryId: String,
        categoryName: String,
        booksCount: Int,
        updatedPeriod: String
    ) = CategoryCache(
        categoryId = categoryId,
        categoryName = categoryName,
        booksCount = booksCount,
        updatePeriod = updatedPeriod,
    )

}

private class FakeCategoryToDomainMapper : CategoryMapper.ToDomain {
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

class FakeHandleError : HandleError<String> {
    override fun handle(exception: Exception): String {
        return if (exception is UnknownHostException) "No internet connection"
        else "Service unavailable"
    }

}