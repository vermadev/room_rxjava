package com.triarc.tutorial.android.room_rxjava.storage.dao

import androidx.room.*
import com.triarc.tutorial.android.room_rxjava.entity.Entries
import com.triarc.tutorial.android.room_rxjava.entity.Register
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Devanshu Verma on 14/1/19
 */
@Dao
interface RegisterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(register: Register): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entries: Register): Completable

    @Delete()
    fun delete(register: Register): Single<Int>

    @Query("DELETE FROM Register")
    fun deleteAll(): Single<Int>

    @Update
    fun update(register: Register): Completable

    @Query("SELECT r.id, r.dueDate, r.issueDate, u.name, u.email, b.title, b.author FROM User u INNER JOIN Register r ON u.id = r.userId INNER JOIN Book b ON r.bookId = b.id ORDER BY issueDate ASC")
    fun getRegisterEntries(): Flowable<List<Entries>?>
}
