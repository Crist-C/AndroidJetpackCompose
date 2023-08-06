package com.ccastro.androidjetpackcompose.domain.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class Image(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "route") val route: String
)
