package uz.pdp.dagger2nuntium.ui.fragment.home

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.card.MaterialCardView
import com.yariksoffice.lingver.Lingver
import uz.pdp.dagger2nuntium.R
import uz.pdp.dagger2nuntium.databinding.FragmentLanguageBinding
import uz.pdp.dagger2nuntium.utils.hide
import uz.pdp.dagger2nuntium.utils.show


class LanguageFragment : Fragment(R.layout.fragment_language) {

    private val binding by viewBinding(FragmentLanguageBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var language = Lingver.getInstance().getLanguage()

        binding.apply {

            fun select(card: MaterialCardView, text: TextView, image: ImageView) {
                langTv.text = getString(R.string.language)
                text1.text = getString(R.string.english)
                text2.text = getString(R.string.russian)
                text3.text = getString(R.string.german)
                text4.text = getString(R.string.turkish)
                card.setCardBackgroundColor(Color.parseColor("#FF475AD7"))
                text.setTextColor(Color.parseColor("#FFFFFFFF"))
                image.show()
            }

            when (language) {
                "ru" -> {
                    select(russian, text2, image2)
                }
                "de" -> {
                    select(german, text3, image3)
                }
                "tr" -> {
                    select(spanish, text4, image4)
                }
                else -> {
                    select(english, text1, image1)
                }
            }

            fun clear() {
                val color1 = ContextCompat.getColor(requireContext(), R.color.cardLightColor)
                english.setCardBackgroundColor(color1)
                russian.setCardBackgroundColor(color1)
                german.setCardBackgroundColor(color1)
                spanish.setCardBackgroundColor(color1)
                val color2 = ContextCompat.getColor(requireContext(), R.color.text_color)
                text1.setTextColor(color2)
                text2.setTextColor(color2)
                text3.setTextColor(color2)
                text4.setTextColor(color2)

                image1.hide()
                image2.hide()
                image3.hide()
                image4.hide()
            }


            english.setOnClickListener {
                if (language != "en") {
                    Lingver.getInstance().setLocale(requireContext(), "en")
                    language = "en"
                    clear()
                    select(english, text1, image1)
                }
            }
            russian.setOnClickListener {
                if (language != "ru") {
                    language = "ru"
                    Lingver.getInstance().setLocale(requireContext(), "ru")
                    clear()
                    select(russian, text2, image2)
                }
            }
            german.setOnClickListener {
                if (language != "de") {
                    language = "de"
                    Lingver.getInstance().setLocale(requireContext(), "de")
                    clear()
                    select(german, text3, image3)
                }
            }
            spanish.setOnClickListener {
                if (language != "tr") {
                    language = "tr"
                    Lingver.getInstance().setLocale(requireContext(), "tr")
                    clear()
                    select(spanish, text4, image4)
                }
            }
            back.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}