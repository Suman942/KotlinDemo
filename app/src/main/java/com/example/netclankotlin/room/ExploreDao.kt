package com.example.netclankotlin.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExploreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(explore: Explore)

//    @Update
//    fun update(note: Note)
//
//    @Delete
//    fun delete(note: Note)

//    @Query("delete from note_table")
//    fun deleteAllNotes()

    @Query("select * from explore_table")
    fun getAllExploreData(): LiveData<List<Explore>>
}