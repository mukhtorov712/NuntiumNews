package uz.pdp.dagger2nuntium.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.pdp.dagger2nuntium.App
import uz.pdp.dagger2nuntium.R
import uz.pdp.dagger2nuntium.adapters.HomeRvAdapter
import uz.pdp.dagger2nuntium.database.entity.Article
import uz.pdp.dagger2nuntium.databinding.FragmentSavedBinding
import uz.pdp.dagger2nuntium.utils.hide
import uz.pdp.dagger2nuntium.utils.navOptions
import uz.pdp.dagger2nuntium.utils.show
import uz.pdp.dagger2nuntium.viewmodels.SaveViewModel
import javax.inject.Inject


class SavedFragment : Fragment(R.layout.fragment_saved) {

    private val binding by viewBinding(FragmentSavedBinding::bind)

    @Inject
    lateinit var saveViewModel: SaveViewModel
    lateinit var list: List<Article>
    private lateinit var homeRvAdapter: HomeRvAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.injectFragmentSaved(this)
        list = saveViewModel.getAllArticle()
        homeRvAdapter = HomeRvAdapter(object : HomeRvAdapter.OnNewsItemClickListener {
            override fun onItemClick(article: Article) {
                findNavController().navigate(
                    R.id.action_navigation_saved_to_newsFragment,
                    Bundle().apply {
                        putSerializable("art", article)
                    }, navOptions()
                )
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            if (list.isEmpty()) {
                imageEmpty.show()
                recRv.hide()
            } else {
                imageEmpty.hide()
                recRv.adapter = homeRvAdapter
                homeRvAdapter.submitList(list)
            }
        }
    }
}