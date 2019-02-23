package com.triarc.tutorial.android.room_rxjava.entity

import java.io.Serializable

/**
 * Created by Devanshu Verma on 7/2/19
 */
data class Entries(var id: Int,
                   var name: String,
                   var email: String,
                   var title: String,
                   var author: String,
                   var dueDate: Long,
                   var issueDate: Long): Serializable
