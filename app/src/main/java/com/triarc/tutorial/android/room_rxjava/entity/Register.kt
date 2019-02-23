package com.triarc.tutorial.android.room_rxjava.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Created by Devanshu Verma on 19/1/19
 */
@Entity(foreignKeys = [(ForeignKey(entity = User::class,
                        parentColumns = ["id"],
                        childColumns  = ["userId"],
                        onDelete = ForeignKey.CASCADE)),
                       (ForeignKey(entity = Book::class,
                        parentColumns = ["id"],
                        childColumns  = ["bookId"],
                        onDelete = ForeignKey.CASCADE))])
data class Register(var bookId: Int, var userId: Int): Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var dueDate: Long? = null

    var issueDate: Long? = null
}
