package com.ccastro.androidjetpackcompose.data.dataSources.localDataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ccastro.androidjetpackcompose.domain.Models.Image
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(image: Image)

    @Query("SELECT * FROM images WHERE url = :url")
    suspend fun getImageByUrl(url: String): Image?

    @Query("SELECT * FROM images")
    fun getAllImages(): Flow<List<Image>>

    @Update
    suspend fun updateImage(image: Image)

    @Delete
    suspend fun deleteImage(image: Image)
}