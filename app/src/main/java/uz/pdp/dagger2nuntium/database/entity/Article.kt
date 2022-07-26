package uz.pdp.dagger2nuntium.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    @PrimaryKey
    val publishedAt: String,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Serializable