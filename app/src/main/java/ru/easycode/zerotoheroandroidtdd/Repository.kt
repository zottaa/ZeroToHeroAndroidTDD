package ru.easycode.zerotoheroandroidtdd


interface Repository {
    interface Add {
        fun add(value: String)
    }

    interface Read {
        fun list(): List<String>
    }

    interface Mutable : Add, Read

    class Base(
        private val dataSource: ItemsDao,
        private val now: Now
    ) : Mutable {
        override fun add(value: String) {
            dataSource.add(ItemCache(id = now.nowMillis(), text = value))
        }

        override fun list(): List<String> {
            val list = mutableListOf<String>()
            dataSource.list().forEach {
                list.add(it.text)
            }
            return list.toList()
        }

    }
}