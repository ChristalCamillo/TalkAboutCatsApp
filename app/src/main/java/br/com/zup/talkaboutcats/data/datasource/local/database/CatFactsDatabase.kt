package br.com.zup.talkaboutcats.data.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.zup.talkaboutcats.data.datasource.local.dao.CatFactsDAO
import br.com.zup.talkaboutcats.data.model.CatFactsResult

@Database(entities = [CatFactsResult::class], version = 2)
abstract class CatFactsDatabase : RoomDatabase() {
    abstract fun catFactsDao(): CatFactsDAO

    companion object {
        @Volatile
        private var INSTANCE: CatFactsDatabase? = null

        fun getDatabase(context: Context): CatFactsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CatFactsDatabase::class.java,
                    "catfacts_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}