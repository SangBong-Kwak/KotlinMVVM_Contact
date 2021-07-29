package com.dreamer_sb.kotlinmvvm_contact.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {

    @Query("SELECT * FROM Contact ORDER BY name ASC")
    fun getAll(): LiveData<List<Contact>> // LiveData 로 반환

    @Insert(onConflict = OnConflictStrategy.REPLACE) // 새로 입력시 Replace 하도록, ABORT -> ERROR, IGNORE -> 무시해서 업데이트가 안됨
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)

}