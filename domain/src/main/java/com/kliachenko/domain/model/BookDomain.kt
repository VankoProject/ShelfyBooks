package com.kliachenko.domain.model

data class BookDomain(
    private val title: String,
    private val description: String,
    private val author: String,
    private val publisher: String,
    private val imageUrl: String,
    private val rank: Int,
    private val sellers: List<SellerDomain>
) : MapBook {
    override fun <T : Any> map(mapper: BookMapper<T>) =
        mapper.map(title, description, author, publisher, imageUrl, rank, sellers)
}

interface MapBook {

    fun <T : Any> map(mapper: BookMapper<T>): T
}

interface BookMapper<T : Any> {

    fun map(
        title: String,
        description: String,
        author: String,
        publisher: String,
        imageUrl: String,
        rank: Int,
        sellers: List<SellerDomain>
    ): T
}