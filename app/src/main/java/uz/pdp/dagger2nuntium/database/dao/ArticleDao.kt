package uz.pdp.dagger2nuntium.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.pdp.dagger2nuntium.database.entity.Article

@Dao
interface ArticleDao {

    @Insert
    fun addArticle(article: Article)

    @Query("DELETE FROM article WHERE publishedAt = :publishedAt")
    fun deleteArticle(publishedAt: String)

    @Query("select count(publishedAt) from article where publishedAt like :publishedAt")
    fun getIsLiked(publishedAt: String): Int

    @Query("select * from article")
    fun getAllArticle(): List<Article>
}

