package com.ccastro.androidjetpackcompose.data.dataSources.localDataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ccastro.androidjetpackcompose.domain.Models.Image

@Database(entities = arrayOf(Image::class), version = 1, exportSchema = false)
abstract class LocalDataBase : RoomDatabase() {

    abstract fun imageDao(): ImageDAO
}