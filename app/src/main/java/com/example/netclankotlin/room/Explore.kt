package com.example.netclankotlin.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "explore_table")
data class Explore(
    @ColumnInfo(name = "Fname")
    val Fname: String,
    @ColumnInfo(name = "Lname")
    val Lname: String,
    @ColumnInfo(name = "profileIMG")
    val profileIMG: String,
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    val id: String
)