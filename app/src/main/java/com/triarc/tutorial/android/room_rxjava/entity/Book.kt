package com.triarc.tutorial.android.room_rxjava.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.Normalizer

/**
 * Created by Devanshu Verma on 19/1/19
 */
@Entity
data class Book(var title: String,
                var author: String,
                var publication: String): Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    init {
        title = Normalizer.normalize(title, Normalizer.Form.NFD).replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
    }
}
