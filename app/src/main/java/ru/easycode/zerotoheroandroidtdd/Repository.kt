package ru.easycode.zerotoheroandroidtdd


interface Repository {
    interface Read {
        fun list(): List<Item>
    }

    interface Change {
        fun delete(id: Long)

        fun item(id: Long): Item

        fun update(id: Long, newText: String)
    }

    interface Add {
        fun add(value: String): Long
    }

    interface All : Read, Change, Add

    class Base(
        private val dataSource: ItemsDao,
        private val now: Now
    ) : All {
        override fun list(): List<Item> = dataSource.list().map { Item(it.id, it.text) }

        override fun delete(id: Long) {
            dataSource.delete(id)
        }

        override fun item(id: Long) = dataSource.item(id).let { Item(it.id, it.text) }

        override fun update(id: Long, newText: String) {
            val item = dataSource.item(id)
            val newItem = ItemCache(item.id, newText)
            dataSource.add(newItem)
        }

        override fun add(value: String): Long {
            val id = now.nowMillis()
            dataSource.add(ItemCache(id, value))
            return id
        }
    }
}

data class Item(val id: Long, val text: String)