package uz.pdp.dagger2nuntium

import android.app.Application
import com.yariksoffice.lingver.Lingver
import uz.pdp.dagger2nuntium.di.component.AppComponent
import uz.pdp.dagger2nuntium.di.component.DaggerAppComponent
import uz.pdp.dagger2nuntium.di.module.DatabaseModule

class App : Application() {


    companion object {
        lateinit var appComponent: AppComponent
    }


    override fun onCreate() {
        super.onCreate()
        Lingver.init(this, "en")
        appComponent = DaggerAppComponent
            .builder()
            .databaseModule(DatabaseModule(applicationContext))
            .build()
    }
}