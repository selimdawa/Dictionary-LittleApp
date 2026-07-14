package com.littleapp.dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
@Suppress("unused")
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}