package ru.easycode.zerotoheroandroidtdd

interface Repository {
    interface Add {
        fun add(value: String): Long
    }

    interface Read {
        fun list(): List<Item>
    }

    interface Delete {
        fun delete(id: Long)

        fun item(id: Long): Item
    }

    interface All : Add, Read, Delete

    class Base(
        private val dataSource: ItemsDao,
        private val now: Now
    ) : All {
        override fun add(value: String): Long {
            val id = now.nowMillis()
            dataSource.add(ItemCache(id, value))
            return id
        }

        override fun list(): List<Item> {
            return dataSource.list().map { Item(it.id, it.text) }
        }

        override fun item(id: Long): Item {
            return dataSource.item(id).let { Item(it.id, it.text) }
        }

        override fun delete(id: Long) {
            dataSource.delete(id)
        }

    }
}