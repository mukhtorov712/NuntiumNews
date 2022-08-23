package uz.pdp.dagger2nuntium.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import uz.pdp.dagger2nuntium.R
import uz.pdp.dagger2nuntium.database.entity.Article
import uz.pdp.dagger2nuntium.databinding.ItemTabRvBinding
import uz.pdp.dagger2nuntium.utils.hide
import uz.pdp.dagger2nuntium.viewmodels.SaveViewModel
import java.lang.Exception

class TabRvAdapter(
    private val viewModel: SaveViewModel,
    private val context: Context,
    private val listener: OnNewsItemClickListener
) :
    ListAdapter<Article, TabRvAdapter.Vh>(MyDiffUtil()) {

    class MyDiffUtil : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.publishedAt == newItem.publishedAt
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    inner class Vh(private val itemBinding: ItemTabRvBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(article: Article) {
            itemBinding.apply {
                if (article.urlToImage?.isNotEmpty() == true) {
                    Picasso.get().load(article.urlToImage).resize(200, 200).centerCrop().into(image, object :
                        Callback {
                        override fun onSuccess() {
                            placeholder.hide()
                        }
                        override fun onError(e: Exception?) {}
                    })
                }
                title.text = article.title
                author.text = article.author
                var isLiked = viewModel.getIsLiked(article.publishedAt)

                if (isLiked == 1) {
                    saveImage.setImageResource(R.drawable.ic_saved_white);
                }

                saveImage.setOnClickListener {
                    isLiked = if (isLiked == 0) {
                        saveImage.setImageResource(R.drawable.ic_saved_white)
                        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                        viewModel.addArticle(article)
                        1
                    } else {
                        saveImage.setImageResource(R.drawable.ic_save_white)
                        Toast.makeText(context, "UnSaved", Toast.LENGTH_SHORT).show()
                        viewModel.deleteArticle(article.publishedAt)
                        0
                    }                }

            }

            itemView.setOnClickListener {
                listener.onItemClick(article)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemTabRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }

    interface OnNewsItemClickListener {
        fun onItemClick(article: Article)
    }
}