package com.kliachenko.domain.model

data class SellerDomain(
    private val name: String,
    private val url: String
) : MapSeller {
    override fun <T : Any> map(mapper: SellerMapper<T>) = mapper.map(name, url)
}

interface MapSeller {

    fun <T : Any> map(mapper: SellerMapper<T>): T

}

interface SellerMapper<T : Any> {

    fun map(
        name: String,
        url: String
    ): T
}