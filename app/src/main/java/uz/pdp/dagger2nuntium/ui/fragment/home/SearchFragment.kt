package uz.pdp.dagger2nuntium.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.http.Query
import uz.pdp.dagger2nuntium.App
import uz.pdp.dagger2nuntium.R
import uz.pdp.dagger2nuntium.adapters.HomeRvAdapter
import uz.pdp.dagger2nuntium.database.entity.Article
import uz.pdp.dagger2nuntium.databinding.FragmentSearchBinding
import uz.pdp.dagger2nuntium.resource.NewsResource
import uz.pdp.dagger2nuntium.utils.MySharedPreference
import uz.pdp.dagger2nuntium.utils.hide
import uz.pdp.dagger2nuntium.utils.navOptions
import uz.pdp.dagger2nuntium.utils.show
import uz.pdp.dagger2nuntium.viewmodels.NewsViewModel
import uz.pdp.dagger2nuntium.viewmodels.SearchViewModel
import javax.inject.Inject


class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding by viewBinding(FragmentSearchBinding::bind)

    @Inject
    lateinit var viewModel: SearchViewModel
    lateinit var str: String

    private lateinit var homeRvAdapter: HomeRvAdapter
    private var isCreate = true
    lateinit var mySharedPreference: MySharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.injectFragmentSearch(this)
        str = arguments?.getString("str") ?: ""
        mySharedPreference = MySharedPreference(requireContext())

        homeRvAdapter = HomeRvAdapter(object : HomeRvAdapter.OnNewsItemClickListener {
            override fun onItemClick(article: Article) {
                findNavController().navigate(
                    R.id.action_searchFragment_to_newsFragment,
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
            searchEditText.setText(str)
            if (isCreate) {
                loadSearch(str)
                isCreate = false
            }

            recRv.adapter = homeRvAdapter

            searchEditText.setOnEditorActionListener { textView, i, keyEvent ->
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    loadSearch(textView.text.toString())
                    return@setOnEditorActionListener true
                } else return@setOnEditorActionListener false
            }
        }
    }

    private fun loadSearch(query: String) {
        binding.apply {
            lifecycleScope.launch {
                viewModel.getSearch(query, mySharedPreference.getString("lan")).collect {
                    when (it) {
                        is NewsResource.Loading -> {
                            animationView1.show()
                            recRv.hide()
                        }
                        is NewsResource.Error -> {
                            animationView1.hide()
                            errorText1.text = it.message
                            errorText1.show()
                        }
                        is NewsResource.Success -> {
                            animationView1.hide()
                            errorText1.hide()
                            recRv.show()
                            homeRvAdapter.submitList(it.list)
                            recRv.smoothScrollToPosition(0)
                        }
                    }
                }
            }
        }

    }
}