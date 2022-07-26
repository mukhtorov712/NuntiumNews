package uz.pdp.dagger2nuntium.resource

import uz.pdp.dagger2nuntium.database.entity.Article


sealed class NewsResource {

    object Loading : NewsResource()

    data class Error(val message: String) : NewsResource()

    data class Success(val list: List<Article>) : NewsResource()
}
