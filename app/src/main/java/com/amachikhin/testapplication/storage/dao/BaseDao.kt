package com.amachikhin.testapplication.storage.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<E> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: E): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: Collection<E>?): LongArray?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: Array<E>?): LongArray?

    @Update
    fun update(entity: E): Int

    @Update
    fun update(entity: Iterable<E>?): Int

    @Delete
    fun delete(entity: E): Int

    @Delete
    fun delete(entity: Iterable<E>?): Int
}