package com.kliachenko.data.repository

import com.kliachenko.data.BookRepositoryImpl
import com.kliachenko.data.cloud.BookCloudDataSource
import com.kliachenko.data.cloud.cloudResponse.BookCloud
import com.kliachenko.data.cloud.cloudResponse.BookListResponse
import com.kliachenko.data.cloud.cloudResponse.BookListResult
import com.kliachenko.data.cloud.cloudResponse.SellerLinkCloud
import com.kliachenko.data.localCache.BookCacheDataSource
import com.kliachenko.data.localCache.SellersCacheDataSource
import com.kliachenko.data.localCache.entity.BookCache
import com.kliachenko.data.localCache.entity.BookWithSellers
import com.kliachenko.data.localCache.entity.SellerLinkCache
import com.kliachenko.data.mapper.BookMapper
import com.kliachenko.data.mapper.BookWithSellersMapper
import com.kliachenko.data.mapper.SellerMapper
import com.kliachenko.domain.model.BookDomain
import com.kliachenko.domain.model.SellerDomain
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

class BookRepositoryTest {

    private lateinit var bookCloudDataSource: FakeBookCloudDataSource
    private lateinit var bookCacheDataSource: FakeBookCacheDataSource
    private lateinit var sellersCacheDataSource: FakeSellersCacheDataSource
    private lateinit var mapBookToCache: FakeBookToCacheMapper
    private lateinit var sellerMapper: FakeSellerMapper
    private lateinit var mapSellerToCache: FakeSellerToCacheMapper
    private lateinit var mapBookToDomain: FakeBookToDomainMapper
    private lateinit var handleError: FakeHandleError
    private lateinit var repository: BookRepositoryImpl

    @Before
    fun setup() {
        bookCloudDataSource = FakeBookCloudDataSource()
        bookCacheDataSource = FakeBookCacheDataSource()
        sellersCacheDataSource = FakeSellersCacheDataSource()
        sellerMapper = FakeSellerMapper()
        mapBookToCache = FakeBookToCacheMapper()
        mapSellerToCache = FakeSellerToCacheMapper()
        mapBookToDomain = FakeBookToDomainMapper(
            sellerMapper = sellerMapper
        )
        handleError = FakeHandleError()

        repository = BookRepositoryImpl(
            bookCloudDataSource = bookCloudDataSource,
            bookCacheDataSource = bookCacheDataSource,
            sellersCacheDataSource = sellersCacheDataSource,
            mapBookToCache = mapBookToCache,
            mapSellerToCache = mapSellerToCache,
            mapBookToDomain = mapBookToDomain,
            handleError = handleError
        )
    }

    @Test
    fun testFirstRunError() = runBlocking {
        bookCacheDataSource.cacheEmpty()
        bookCloudDataSource.errorLoadResult(UnknownHostException())
        val actual = repository.books("category_1").first()
        bookCloudDataSource.checkCalled(1)
        val expected = LoadResult.Error<String>("No internet connection")
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testFirstRunEmptySuccess() = runBlocking {
        bookCacheDataSource.cacheEmpty()
        bookCloudDataSource.successLoadResult()
        val actual = repository.books("category_1").toList()
        bookCloudDataSource.checkCalled(1)
        val expected = listOf(
            LoadResult.Success(
                listOf(
                    BookDomain(
                        title = "title 1",
                        description = "description 1",
                        author = "author 1",
                        publisher = "publisher 1",
                        imageUrl = "imageUrl 1",
                        rank = 1,
                        sellers = listOf(
                            SellerDomain(
                                name = "seller name 1",
                                url = "url 1"
                            )
                        )
                    ),
                    BookDomain(
                        title = "title 2",
                        description = "description 2",
                        author = "author 2",
                        publisher = "publisher 2",
                        imageUrl = "imageUrl 2",
                        rank = 2,
                        sellers = listOf(
                            SellerDomain(
                                name = "seller name 2",
                                url = "url 2"
                            )
                        )
                    )
                )
            )
        )
        Assert.assertEquals(expected, actual)

    }

    @Test
    fun testNotFirstRunSuccess() = runBlocking {
        bookCacheDataSource.cacheNotEmpty()
        sellersCacheDataSource.cacheNotEmpty()
        val actual = repository.books("category_1").toList()
        bookCloudDataSource.checkCalled(0)
        val expected = listOf(
            LoadResult.Success(
                listOf(
                    BookDomain(
                        title = "title 1",
                        description = "description 1",
                        author = "author 1",
                        publisher = "publisher 1",
                        imageUrl = "imageUrl 1",
                        rank = 1,
                        sellers = listOf(
                            SellerDomain(
                                name = "seller name 1",
                                url = "url 1"
                            )
                        )
                    ),
                    BookDomain(
                        title = "title 2",
                        description = "description 2",
                        author = "author 2",
                        publisher = "publisher 2",
                        imageUrl = "imageUrl 2",
                        rank = 2,
                        sellers = listOf(
                            SellerDomain(
                                name = "seller name 2",
                                url = "url 2"
                            )
                        )
                    )
                )
            )
        )
        Assert.assertEquals(expected, actual)
    }

}

private class FakeBookCloudDataSource : BookCloudDataSource {

    private var isSuccessResult: Boolean = false
    private lateinit var exception: Exception
    private var actualCalledCount: Int = 0

    override suspend fun books(categoryId: String): BookListResponse {
        actualCalledCount++
        if (!isSuccessResult) throw exception
        return BookListResponse(
            result = BookListResult(
                listName = "Cloud Category 1",
                books = listOf(
                    BookCloud(
                        bookId = "book_1",
                        title = "title 1",
                        description = "description 1",
                        author = "author 1",
                        publisher = "publisher 1",
                        imageUrl = "imageUrl 1",
                        rank = 1,
                        sellers = listOf(
                            SellerLinkCloud(
                                sellerName = "name 1",
                                url = "url 1"
                            )
                        )
                    ),
                    BookCloud(
                        bookId = "book_2",
                        title = "title 2",
                        description = "description 2",
                        author = "author 2",
                        publisher = "publisher 2",
                        imageUrl = "imageUrl 2",
                        rank = 2,
                        sellers = listOf(
                            SellerLinkCloud(
                                sellerName = "name 2",
                                url = "url 2"
                            )
                        )
                    )
                )
            )
        )
    }

    fun successLoadResult() {
        isSuccessResult = true
    }

    fun errorLoadResult(e: UnknownHostException) {
        exception = e
    }

    fun checkCalled(expectedTimes: Int) {
        Assert.assertEquals(expectedTimes, actualCalledCount)
    }
}

private class FakeBookCacheDataSource : BookCacheDataSource.Mutable {

    private var actualBooks: List<BookCache> = emptyList()
    private var actualBooksWithSellers: List<BookWithSellers> = emptyList()

    override suspend fun save(books: List<BookCache>) {
        actualBooks = books
        actualBooksWithSellers = books.map {
            BookWithSellers(
                book = it,
                sellers = listOf(
                    SellerLinkCache(
                        id = it.rank,
                        bookId = it.bookId,
                        sellerName = "seller name ${it.rank}",
                        url = "url ${it.rank}"
                    )
                )
            )
        }
    }

    override fun read(categoryListName: String): Flow<List<BookWithSellers>> =
        flowOf(
            actualBooksWithSellers.filter {
                it.book.categoryListName == categoryListName
            }
        )

    fun cacheEmpty() {
        actualBooks = emptyList()
        actualBooksWithSellers = emptyList()
    }

    fun cacheNotEmpty() {
        val book1 = BookCache(
            bookId = "book_1",
            title = "title 1",
            categoryListName = "category_1",
            description = "description 1",
            author = "author 1",
            publisher = "publisher 1",
            imageUrl = "imageUrl 1",
            rank = 1
        )
        val book2 = BookCache(
            bookId = "book_2",
            title = "title 2",
            categoryListName = "category_1",
            description = "description 2",
            author = "author 2",
            publisher = "publisher 2",
            imageUrl = "imageUrl 2",
            rank = 2
        )

        actualBooks = listOf(book1, book2)
        actualBooksWithSellers = listOf(
            BookWithSellers(
                book = book1,
                sellers = listOf(
                    SellerLinkCache(
                        id = 1,
                        bookId = book1.bookId,
                        sellerName = "seller name 1",
                        url = "url 1"
                    )
                )
            ),
            BookWithSellers(
                book = book2,
                sellers = listOf(
                    SellerLinkCache(
                        id = 2,
                        bookId = book2.bookId,
                        sellerName = "seller name 2",
                        url = "url 2"
                    )
                )
            )
        )
    }
}

private class FakeSellersCacheDataSource : SellersCacheDataSource.Save {

    private var actualSellers: List<SellerLinkCache> = emptyList()

    override suspend fun save(sellers: List<SellerLinkCache>) {
        actualSellers = sellers
    }

    fun cacheNotEmpty() {
        actualSellers = listOf(
            SellerLinkCache(
                id = 1,
                bookId = "book_1",
                sellerName = "seller name 1",
                url = "url 1"
            ),
            SellerLinkCache(
                id = 2,
                bookId = "book_2",
                sellerName = "seller name 2",
                url = "url 2"
            )
        )
    }
}

private class FakeBookToCacheMapper : BookMapper.ToCache {
    override fun map(
        bookId: String,
        categoryListName: String,
        title: String,
        description: String,
        author: String,
        publisher: String,
        imageUrl: String,
        rank: Int
    ) = BookCache(
        bookId = bookId,
        categoryListName = categoryListName,
        title = title,
        description = description,
        author = author,
        publisher = publisher,
        imageUrl = imageUrl,
        rank = rank
    )

}

private class FakeSellerToCacheMapper : SellerMapper.ToCache {
    override fun map(bookId: String, sellerName: String, url: String) =
        SellerLinkCache(
            bookId = bookId,
            sellerName = sellerName,
            url = url
        )

}

private class FakeBookToDomainMapper(
    private val sellerMapper: SellerMapper.ToDomain
) : BookWithSellersMapper.ToDomain {
    override fun map(book: BookCache, sellers: List<SellerLinkCache>) =
        BookDomain(
            title = book.title,
            description = book.description,
            author = book.author,
            publisher = book.publisher,
            imageUrl = book.imageUrl,
            rank = book.rank,
            sellers = sellers.map { it.map(bookId = book.bookId, sellerMapper) }
        )

}

private class FakeSellerMapper : SellerMapper.ToDomain {
    override fun map(bookId: String, sellerName: String, url: String): SellerDomain {
        return SellerDomain(
            name = sellerName,
            url = url
        )
    }

}
