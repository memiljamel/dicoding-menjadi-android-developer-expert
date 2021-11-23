package com.example.tmdb.core.data

import com.example.tmdb.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {
    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDb(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> {
        return flow {
            emit(Resource.Loading())
            val dbSource = loadFromDb().first()
            if (shouldFetch(dbSource)) {
                emit(Resource.Loading())
                when (val apiResponse = createCall().first()) {
                    is ApiResponse.Success -> {
                        saveCallResult(apiResponse.data)
                        emitAll(loadFromDb().map {
                            Resource.Success(it)
                        })
                    }
                    is ApiResponse.Empty -> {
                        emitAll(loadFromDb().map {
                            Resource.Success(it)
                        })
                    }
                    is ApiResponse.Error -> {
                        onFetchFailed()
                        emit(Resource.Error<ResultType>(apiResponse.message))
                    }
                }
            } else {
                emitAll(loadFromDb().map {
                    Resource.Success(it)
                })
            }
        }
    }
}