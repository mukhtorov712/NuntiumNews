package uz.pdp.dagger2nuntium.di.module

import android.content.Context
import androidx.room.Room
import com.yariksoffice.lingver.Lingver
import dagger.Module
import dagger.Provides
import uz.pdp.dagger2nuntium.database.AppDatabase
import uz.pdp.dagger2nuntium.database.dao.ArticleDao
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesContext(): Context = context

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "my_db")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(appDatabase: AppDatabase): ArticleDao =
        appDatabase.articleDao()

}