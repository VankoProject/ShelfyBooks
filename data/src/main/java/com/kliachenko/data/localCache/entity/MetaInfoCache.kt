package com.kliachenko.data.localCache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meta_info")
data class MetaInfoCache(
    @PrimaryKey
    val key: String,
    val value: String
)
