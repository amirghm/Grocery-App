package com.amirghm.gerocery.data.repository

import com.amirghm.gerocery.data.source.CatalogDataSource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

open class CatalogRepositoryImpl constructor(private val catalogDataSource: CatalogDataSource):CatalogRepository {
    override suspend fun getProducts() = flow {
        catalogDataSource.getProducts().collect {
            // The Business Logic can be added here
            emit(it)
        }
    }
}