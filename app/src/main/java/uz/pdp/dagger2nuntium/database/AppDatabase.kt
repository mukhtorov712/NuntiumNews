package uz.pdp.dagger2nuntium.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.pdp.dagger2nuntium.database.dao.ArticleDao
import uz.pdp.dagger2nuntium.database.entity.Article

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}