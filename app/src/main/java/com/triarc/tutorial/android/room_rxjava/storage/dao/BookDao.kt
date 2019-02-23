package com.triarc.tutorial.android.room_rxjava.storage.dao

import androidx.room.*
import com.triarc.tutorial.android.room_rxjava.entity.Book
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Devanshu Verma on 14/1/19
 */
@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Book): Completable

    @Delete()
    fun delete(book: Book): Single<Int>

    @Query("DELETE FROM Book")
    fun deleteAll(): Single<Int>

    @Update
    fun update(book: Book): Completable

    @Query("SELECT * FROM Book ORDER BY title ASC")
    fun getAllBooks(): Flowable<List<Book>?>

    @Query("SELECT * FROM Book WHERE title LIKE :title || '%' COLLATE NOCASE OR title LIKE '% ' || :title || '%' COLLATE NOCASE ORDER BY title ASC")
    fun getBookByMatchingTitle(title: String): Flowable<List<Book>?>
}
