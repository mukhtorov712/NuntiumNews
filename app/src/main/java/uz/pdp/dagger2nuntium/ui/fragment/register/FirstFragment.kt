package uz.pdp.dagger2nuntium.ui.fragment.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.pdp.dagger2nuntium.R
import uz.pdp.dagger2nuntium.adapters.CarouselAdapter
import uz.pdp.dagger2nuntium.databinding.FragmentFirstBinding


class FirstFragment : Fragment(R.layout.fragment_first) {

    private val binding by viewBinding(FragmentFirstBinding::bind)
    private lateinit var corouselAdapter: CarouselAdapter
    private var listPic = ArrayList<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadImages()
        corouselAdapter = CarouselAdapter(listPic)
        binding.apply {
            rv.adapter = corouselAdapter
            nextBtn.setOnClickListener {
                findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
            }
        }

    }

    private fun loadImages() {
        listPic = ArrayList()
        listPic.add("https://static.onecms.io/wp-content/uploads/sites/28/2021/02/19/new-york-city-evening-NYCTG0221.jpg")
        listPic.add("https://www.flytap.com/-/media/Flytap/new-tap-pages/destinations/north-america/united-states/washington/destinations-washington-banner-mobile-1024x553.jpg")
        listPic.add("https://worldskillseurope.org/application/files/7616/1891/5854/SaintPetersburg.png")
        listPic.add("https://upload.wikimedia.org/wikipedia/commons/3/33/Tashkent_skyline_2019.jpg")
        listPic.add("https://london.ac.uk/sites/default/files/styles/max_1300x1300/public/2018-10/london-aerial-cityscape-river-thames_1.jpg?itok=6LenFxuz")
        listPic.add("https://static.euronews.com/articles/stories/06/17/53/80/1440x810_cmsv2_a0a5e7cc-ffae-5264-a7c6-8bd86d6b94e7-6175380.jpg")
    }


}