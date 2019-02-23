package com.triarc.tutorial.android.room_rxjava.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Created by Devanshu Verma on 14/1/19
 */
@Entity
data class User(var name: String,
                var email: String,
                var school: String,
                var grade: String): Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var timestamp: Long = 0
}