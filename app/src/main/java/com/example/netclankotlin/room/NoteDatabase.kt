package com.example.netclankotlin.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun exploreDao(): ExploreDao

    companion object {
        private var instance: NoteDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): NoteDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, NoteDatabase::class.java,
                    "explore_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

            return instance!!

        }

//        private val roomCallback = object : Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                populateDatabase(instance!!)
//            }
//        }
//
//        private fun populateDatabase(db: NoteDatabase) {
//            val noteDao = db.exploreDao()
//
//                noteDao.insert(Note("title 1", "desc 1"))
//                noteDao.insert(Note("title 2", "desc 2"))
//                noteDao.insert(Note("title 3", "desc 3"))
//
//
//        }
    }



}