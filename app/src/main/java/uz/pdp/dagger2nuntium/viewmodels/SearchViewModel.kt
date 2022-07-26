package uz.pdp.dagger2nuntium.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Language
import retrofit2.http.Query
import uz.pdp.dagger2nuntium.resource.NewsResource
import uz.pdp.dagger2nuntium.utils.NetworkHelper
import uz.pdp.newsapp.repository.NewsRepository
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    fun getSearch(query: String, language: String): StateFlow<NewsResource> {
        val stateFlow = MutableStateFlow<NewsResource>(NewsResource.Loading)
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                newsRepository.getSearch(query, language)
                    .catch {
                        stateFlow.emit(NewsResource.Error(it.message ?: ""))
                    }
                    .collect {
                        if (it.isSuccessful) {
                            stateFlow.emit(NewsResource.Success(it.body()?.articles ?: emptyList()))
                        } else {
                            stateFlow.emit(NewsResource.Error(it.errorBody()?.string() ?: ""))
                        }
                    }
            } else {
                stateFlow.emit(NewsResource.Error("No Internet Connections"))
            }

        }
        return stateFlow
    }
}