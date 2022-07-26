package uz.pdp.dagger2nuntium.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.pdp.newsapp.models.HeadLineData

interface ApiService {

    companion object {
        private const val API_KEY = "44edcd777f2643ffb14f86184992d3b7"
    }

//    0fb42a1989734636b4671ea01f34a570
//    44edcd777f2643ffb14f86184992d3b7
//    31a3888bc30d42e2ac637ee0258c14ed
//    41e12e0982d74e94b0f889dcf7b4362c

    //for topViewPager random(category = general)
    //and by category
    //and recommended
    @GET("top-headlines")
    suspend fun getByCategory(
        @Query("category") category: String,
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int = 10,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<HeadLineData>

    //for search
    @GET("everything")
    suspend fun getSearch(
        @Query("q") query: String,
        @Query("language") language: String,
        @Query("pageSize") pageSize: Int = 50,
        @Query("apiKey") apiKey: String = API_KEY,
    ): Response<HeadLineData>

}