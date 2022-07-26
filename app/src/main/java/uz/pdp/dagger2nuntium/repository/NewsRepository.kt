package uz.pdp.newsapp.repository

import kotlinx.coroutines.flow.flow
import org.intellij.lang.annotations.Language
import uz.pdp.dagger2nuntium.database.dao.ArticleDao
import uz.pdp.dagger2nuntium.database.entity.Article
import uz.pdp.dagger2nuntium.network.ApiService
import javax.inject.Inject


class NewsRepository @Inject constructor(private val apiService: ApiService) {

    fun getByCategory(category: String, country: String) =
        flow { emit(apiService.getByCategory(category, country)) }

    fun getSearch(query: String, language: String) =
        flow { emit(apiService.getSearch(query, language)) }

}