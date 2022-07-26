package uz.pdp.dagger2nuntium.viewmodels

import androidx.lifecycle.ViewModel
import androidx.room.Query
import uz.pdp.dagger2nuntium.database.dao.ArticleDao
import uz.pdp.dagger2nuntium.database.entity.Article
import javax.inject.Inject

class SaveViewModel @Inject constructor(private val articleDao: ArticleDao) : ViewModel() {

    fun addArticle(article: Article) {
        articleDao.addArticle(article)
    }

    fun deleteArticle(publishedAt: String) {
        articleDao.deleteArticle(publishedAt)
    }

    fun getIsLiked(publishedAt: String): Int {
        return articleDao.getIsLiked(publishedAt)
    }

    fun getAllArticle(): List<Article> {
        return articleDao.getAllArticle()
    }
}
