package uz.pdp.dagger2nuntium.adapters

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import uz.pdp.dagger2nuntium.R
import uz.pdp.dagger2nuntium.database.entity.Article
import uz.pdp.dagger2nuntium.databinding.ItemNews1Binding
import uz.pdp.dagger2nuntium.utils.hide
import java.lang.Exception

class HomeRvAdapter(private val listener: OnNewsItemClickListener) :
    ListAdapter<Article, HomeRvAdapter.Vh>(MyDiffUtil()) {

    class MyDiffUtil : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.publishedAt == newItem.publishedAt
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    inner class Vh(private val itemBinding: ItemNews1Binding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(article: Article) {
            itemBinding.apply {
                    Picasso.get().load(article.urlToImage).resize(70, 70).centerCrop().into(image, object :Callback{
                        override fun onSuccess() {
                            placeholder.hide()
                        }
                        override fun onError(e: Exception?) {}
                    })
                title.text = article.title
                author.text = article.author
            }

            itemView.setOnClickListener {
                listener.onItemClick(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemNews1Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }

    interface OnNewsItemClickListener {
        fun onItemClick(article: Article)
    }


}