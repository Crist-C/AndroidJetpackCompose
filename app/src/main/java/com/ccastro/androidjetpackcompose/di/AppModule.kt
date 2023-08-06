package com.ccastro.androidjetpackcompose.di

import android.content.Context
import androidx.room.Room
import com.ccastro.androidjetpackcompose.data.dataSources.localDataBase.LocalDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun LocalDataBaseProvide(@ApplicationContext context: Context): LocalDataBase {
        return Room.databaseBuilder(context, LocalDataBase::class.java, "ProjectLocalDB")
            .fallbackToDestructiveMigration()
            .build()
    }
}