package uz.pdp.dagger2nuntium.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.squareup.picasso.Picasso
import uz.pdp.dagger2nuntium.App
import uz.pdp.dagger2nuntium.R
import uz.pdp.dagger2nuntium.database.dao.ArticleDao
import uz.pdp.dagger2nuntium.database.entity.Article
import uz.pdp.dagger2nuntium.databinding.FragmentNewsBinding
import uz.pdp.dagger2nuntium.utils.hide
import uz.pdp.dagger2nuntium.utils.setImage
import uz.pdp.dagger2nuntium.utils.show
import uz.pdp.dagger2nuntium.viewmodels.NewsViewModel
import uz.pdp.dagger2nuntium.viewmodels.SaveViewModel
import javax.inject.Inject

class NewsFragment : Fragment(R.layout.fragment_news) {

    @Inject
    lateinit var viewModel: SaveViewModel

    private val binding by viewBinding(FragmentNewsBinding::bind)
    private lateinit var article: Article
    private var isLiked = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.injectFragmentNews(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        article = arguments?.getSerializable("art") as Article
        isLiked = viewModel.getIsLiked(article.publishedAt)

        if (isLiked == 1) {
            binding.saveImg.setImageResource(R.drawable.ic_saved_white);
            binding.saveImg.setAltImageResource(R.drawable.ic_saved_gray);
        }

        binding.saveImg.setOnClickListener {
            isLiked = if (isLiked == 0) {
                binding.saveImg.setImageResource(R.drawable.ic_saved_white)
                binding.saveImg.setAltImageResource(R.drawable.ic_saved_gray)
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                viewModel.addArticle(article)
                1
            } else {
                binding.saveImg.setImageResource(R.drawable.ic_save_white)
                binding.saveImg.setAltImageResource(R.drawable.ic_save_gray)
                Toast.makeText(context, "UnSaved", Toast.LENGTH_SHORT).show()
                viewModel.deleteArticle(article.publishedAt)
                0
            }
        }

        binding.apply {
            image.setImage(article.urlToImage)
            imageOnBg.setImage(article.urlToImage)
            if (article.author == "null"){
                sourceNameCard.visibility = View.GONE
            }else{
                sourceNameTv.text = article.author
            }
            titleTv.text = article.title
            newsDataTv.text = article.description
            backImg.setOnClickListener {
                findNavController().popBackStack()
            }

            binding.shareImg.setOnClickListener {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(Intent.EXTRA_TEXT, article.url)
                sendIntent.type = "text/plain"
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }

        }
    }
}