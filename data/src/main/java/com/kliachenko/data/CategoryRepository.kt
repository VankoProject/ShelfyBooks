package com.kliachenko.data

import com.kliachenko.data.cloud.CategoryCloudDataSource
import com.kliachenko.domain.core.HandleError
import com.kliachenko.data.localCache.CategoryCacheDataSource
import com.kliachenko.data.localCache.MetaInfoCacheDataSource
import com.kliachenko.data.mapper.CategoryMapper
import com.kliachenko.domain.model.CategoryScreenData
import com.kliachenko.domain.repository.CategoryRepository
import com.kliachenko.domain.repository.LoadResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryCloudDataSource: CategoryCloudDataSource,
    private val categoryCacheDataSource: CategoryCacheDataSource.Mutable,
    private val metaInfoCacheDataSource: MetaInfoCacheDataSource.Mutable,
    private val mapCategoryToCache: CategoryMapper.ToCache,
    private val mapCategoryToDomain: CategoryMapper.ToDomain,
    private val handleError: HandleError<String>
) : CategoryRepository {

    override fun categories(): Flow<LoadResult<CategoryScreenData>> = flow {
        val cached = categoryCacheDataSource.read().first()
        if (cached.isEmpty()) {
            try {
                val cloudResponse = categoryCloudDataSource.overview()
                val categories = cloudResponse.categoriesList
                val publishedDate = cloudResponse.publishedDate

                if (categories.isEmpty()) emit(LoadResult.Empty())
                else {
                    val cacheCategories = categories.map { it.map(mapCategoryToCache) }
                    categoryCacheDataSource.save(cacheCategories)
                    metaInfoCacheDataSource.save(publishedDate)
                }
            } catch (exception: Exception) {
                emit(LoadResult.Error(handleError.handle(exception)))
                return@flow
            }
        }
        val publishedDate = metaInfoCacheDataSource.read()
        val mappedCategoriesFlow: Flow<LoadResult<CategoryScreenData>> =
            categoryCacheDataSource.read().map { list ->
                val categoriesDomain = list.map { it.map(mapCategoryToDomain) }
                LoadResult.Success(CategoryScreenData(publishedDate, categoriesDomain))
            }
        emitAll(mappedCategoriesFlow)
    }

}

