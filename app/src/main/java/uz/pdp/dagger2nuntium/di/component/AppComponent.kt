package uz.pdp.dagger2nuntium.di.component

import dagger.Component
import uz.pdp.dagger2nuntium.ui.activity.HomeActivity
import uz.pdp.dagger2nuntium.di.module.DatabaseModule
import uz.pdp.dagger2nuntium.di.module.NetworkModule
import uz.pdp.dagger2nuntium.ui.fragment.home.*
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(homeActivity: HomeActivity)
    fun injectFragment(homeFragment: HomeFragment)
    fun injectFragmentNews(newsFragment: NewsFragment)
    fun injectFragmentSaved(savedFragment: SavedFragment)
    fun injectFragmentSearch(searchFragment: SearchFragment)
}