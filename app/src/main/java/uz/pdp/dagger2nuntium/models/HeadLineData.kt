package uz.pdp.newsapp.models

import uz.pdp.dagger2nuntium.database.entity.Article


data class HeadLineData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)