package com.example.alphainternproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BinEntity::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun binDao(): BinDao

}
