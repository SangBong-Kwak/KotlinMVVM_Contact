package com.dreamer_sb.kotlinmvvm_contact.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true) var id : Long?,
    @ColumnInfo(name = "name") var name : String,
    @ColumnInfo(name = "number") var number : String,
    @ColumnInfo(name = "initial") var initial : Char
)