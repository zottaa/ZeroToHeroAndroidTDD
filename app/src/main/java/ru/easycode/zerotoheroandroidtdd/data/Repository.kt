package ru.easycode.zerotoheroandroidtdd.data

import kotlinx.coroutines.delay

interface Repository {

    suspend fun load() : SimpleResponse

    class Base(private val service: SimpleService, private val url: String) : Repository {

        override suspend fun load() : SimpleResponse {
            delay(2500)
            return service.fetch(url)
        }

    }
}