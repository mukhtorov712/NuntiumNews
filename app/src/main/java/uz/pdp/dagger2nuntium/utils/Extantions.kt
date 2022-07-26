package uz.pdp.dagger2nuntium.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.NavOptions
import com.squareup.picasso.Picasso
import uz.pdp.dagger2nuntium.R
import javax.inject.Inject


fun ImageView.setImage(url: String?) {
    Picasso.get().load(url).into(this)
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun navOptions(): NavOptions {
    return NavOptions.Builder()
        .setEnterAnim(R.anim.enter)
        .setExitAnim(R.anim.exit)
        .setPopEnterAnim(R.anim.pop_enter)
        .setPopExitAnim(R.anim.pop_exit).build()
}

class MakeToast @Inject constructor(private val context: Context) {
    fun makeToast(str: String) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    }
}