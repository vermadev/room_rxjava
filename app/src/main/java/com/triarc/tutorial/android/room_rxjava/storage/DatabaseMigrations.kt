package com.triarc.tutorial.android.room_rxjava.storage

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration

/**
 * Created by Devanshu Verma on 14/1/19
 */
class DatabaseMigrations {

    companion object {
        val MIGRATION_LIST = listOf<Migration>(
            object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("ALTER TABLE `User` ADD COLUMN `email` TEXT, `school` TEXT, `grade` TEXT")
                }
            }
        )
    }
}
