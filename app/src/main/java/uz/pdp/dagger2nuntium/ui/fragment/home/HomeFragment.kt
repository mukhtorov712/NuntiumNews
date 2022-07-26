package uz.pdp.dagger2nuntium.ui.fragment.home

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayout
import com.yariksoffice.lingver.Lingver
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.pdp.dagger2nuntium.App
import uz.pdp.dagger2nuntium.R
import uz.pdp.dagger2nuntium.adapters.HomeRvAdapter
import uz.pdp.dagger2nuntium.adapters.TabRvAdapter
import uz.pdp.dagger2nuntium.adapters.TabRvAdapter.*
import uz.pdp.dagger2nuntium.database.entity.Article
import uz.pdp.dagger2nuntium.databinding.FragmentHomeBinding
import uz.pdp.dagger2nuntium.databinding.ItemTabBinding
import uz.pdp.dagger2nuntium.resource.NewsResource
import uz.pdp.dagger2nuntium.utils.*
import uz.pdp.dagger2nuntium.viewmodels.NewsViewModel
import uz.pdp.dagger2nuntium.viewmodels.SaveViewModel
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextInt

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var homeRvAdapter: HomeRvAdapter
    private lateinit var tabRvAdapter: TabRvAdapter

    @Inject
    lateinit var newsViewModel: NewsViewModel

    @Inject
    lateinit var saveViewModel: SaveViewModel

    private var isCreate = true
    private var setLang = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.injectFragment(this)


        homeRvAdapter = HomeRvAdapter(object : HomeRvAdapter.OnNewsItemClickListener {
            override fun onItemClick(article: Article) {
                findNavController().navigate(
                    R.id.action_navigation_home_to_newsFragment,
                    Bundle().apply {
                        putSerializable("art", article)
                    }, navOptions()
                )
            }
        })

        tabRvAdapter =
            TabRvAdapter(saveViewModel, requireContext(), object : OnNewsItemClickListener {
                override fun onItemClick(article: Article) {
                    findNavController().navigate(
                        R.id.action_navigation_home_to_newsFragment,
                        Bundle().apply {
                            putSerializable("art", article)
                        }, navOptions()
                    )
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lang = Lingver.getInstance().getLanguage()
        val country = if (lang == "en"){
            "us"
        }else{
            lang
        }
        if(setLang != lang){
            isCreate = true
        }

        if (isCreate) {
            val random = Random.nextInt(1..6)
            loadRec(getCatList("en")[random], country)
            loadTabData("General", country)
            setLang = lang
            isCreate = false
        }

        binding.apply {

            recRv.adapter = homeRvAdapter

            tabRv.adapter = tabRvAdapter

            for (category in getCatList(lang)) {
                tabLayout.addTab(tabLayout.newTab().setText(category))
            }

            getCatList(lang).forEachIndexed { index, s ->
                val tabBinding = ItemTabBinding.inflate(layoutInflater)
                tabBinding.text.text = s
                if (index == 0) {
                    tabBinding.card.setCardBackgroundColor(Color.parseColor("#FF475AD7"))
                    tabBinding.text.setTextColor(Color.parseColor("#FFFFFFFF"))
                } else {
                    tabBinding.card.setCardBackgroundColor(Color.parseColor("#FFF3F4F6"))
                    tabBinding.text.setTextColor(Color.parseColor("#FF666C8E"))
                }
                tabLayout.getTabAt(index)?.customView = tabBinding.root
            }

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val itemTabBinding = ItemTabBinding.bind(tab!!.customView!!)
                    itemTabBinding.card.setCardBackgroundColor(Color.parseColor("#FF475AD7"))
                    itemTabBinding.text.setTextColor(Color.parseColor("#FFFFFFFF"))
                    if (tab.position == 0) {
                        loadTabData("General", country)
                    } else {
                        loadTabData(getCatList("en")[tab.position], country)
                    }

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    val itemTabBinding = ItemTabBinding.bind(tab!!.customView!!)
                    itemTabBinding.card.setCardBackgroundColor(Color.parseColor("#FFF3F4F6"))
                    itemTabBinding.text.setTextColor(Color.parseColor("#FF666C8E"))
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })

            searchEditText.setOnEditorActionListener { textView, i, _ ->
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    findNavController().navigate(R.id.action_navigation_home_to_searchFragment,
                        Bundle().apply {
                            putString("str", textView.text.toString())
                        },
                        navOptions())
                    return@setOnEditorActionListener true
                } else return@setOnEditorActionListener false
            }
        }
    }

    private fun loadRec(category: String, country: String) {
        binding.apply {
            lifecycleScope.launch {
                newsViewModel.getByCategory(category, country).collect {
                    when (it) {
                        is NewsResource.Loading -> {
                            animationView2.show()
                        }
                        is NewsResource.Error -> {
                            animationView2.hide()
                            errorText2.text = it.message
                            errorText2.show()
                        }
                        is NewsResource.Success -> {
                            animationView2.hide()
                            errorText2.hide()
                            homeRvAdapter.submitList(it.list)
                        }
                    }
                }
            }
        }
    }

    private fun loadTabData(category: String, country: String) {
        binding.apply {
            lifecycleScope.launch {
                newsViewModel.getByCategory(category, country).collect {
                    when (it) {
                        is NewsResource.Loading -> {
                            animationView1.show()
                            tabRv.hide()
                        }
                        is NewsResource.Error -> {
                            animationView1.hide()
                            errorText1.text = it.message
                            errorText1.show()
                        }
                        is NewsResource.Success -> {
                            animationView1.hide()
                            errorText1.hide()
                            tabRv.show()
                            tabRvAdapter.submitList(it.list)
                            tabRv.smoothScrollToPosition(0)
                        }
                    }
                }
            }
        }
    }
}