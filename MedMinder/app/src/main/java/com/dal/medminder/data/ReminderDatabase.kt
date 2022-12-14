package com.dal.medminder.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dal.medminder.model.Reminder

/**
 * This is an abstract class for a local sqlite database to hold all the medication reminders
 */
@Database(entities = [Reminder::class], version = 4)
abstract class ReminderDatabase : RoomDatabase() {

    abstract fun reminderDao(): ReminderDao

    companion object {
        @Volatile
        private var INSTANCE: ReminderDatabase? = null

        fun getDatabase(context: Context): ReminderDatabase {
            return (INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReminderDatabase::class.java,
                    "reminder_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            })
        }
    }
}