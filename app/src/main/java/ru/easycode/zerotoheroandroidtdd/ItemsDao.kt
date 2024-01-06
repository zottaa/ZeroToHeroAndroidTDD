package ru.easycode.zerotoheroandroidtdd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemsDao {

    @Query("SELECT * FROM ItemCache")
    fun list(): List<ItemCache>

    @Query("SELECT * FROM ItemCache where id = :id")
    fun item(id: Long): ItemCache

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(item: ItemCache)

    @Query("DELETE FROM ItemCache where id = :id")
    fun delete(id: Long)

}