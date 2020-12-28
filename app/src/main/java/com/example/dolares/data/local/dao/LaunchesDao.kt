package com.example.dolares.data.local.dao

import androidx.room.*
import com.example.dolares.data.local.model.launch.Launch
import kotlinx.coroutines.flow.Flow

@Dao
interface LaunchesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLaunches(launches:List<Launch>)

    @Query("Select * From launch_table")
    fun getAllLaunchesFlow(): Flow<List<Launch>>

    @Transaction
    suspend fun replaceAllLaunches(launches: List<Launch>){
        deleteAllLaunches()
        insertAllLaunches(launches)
    }

    @Query("Delete From launch_table")
    suspend fun deleteAllLaunches()

}