package ru.easycode.zerotoheroandroidtdd.data

import java.lang.IllegalStateException
import java.net.UnknownHostException

interface Repository {

    suspend fun load(): LoadResult

    class Base(private val service: SimpleService, private val url: String) : Repository {
        override suspend fun load(): LoadResult {
            return try {

                val result = service.fetch(url)
                LoadResult.Success(SimpleResponse(result.text))

            } catch (e: UnknownHostException) {

                LoadResult.Error(true)

            } catch (e: IllegalStateException) {

                LoadResult.Error(false)

            }
        }

    }

}