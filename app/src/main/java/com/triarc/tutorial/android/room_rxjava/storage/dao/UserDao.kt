package com.triarc.tutorial.android.room_rxjava.storage.dao

import androidx.room.*
import com.triarc.tutorial.android.room_rxjava.entity.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Devanshu Verma on 14/1/19
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Completable

    @Delete()
    fun delete(user: User): Single<Int>

    @Query("DELETE FROM User")
    fun deleteAll(): Single<Int>

    @Update
    fun update(user: User): Completable

    @Query("SELECT * FROM User ORDER BY timestamp DESC")
    fun getAllUsers(): Flowable<List<User>?>
}
