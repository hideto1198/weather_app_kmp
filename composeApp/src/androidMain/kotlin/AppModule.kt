import cahce.AndroidDatabaseDriverFactory
import network.SpaceXApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.weather_app_kmp.app.App.RocketLaunchViewModel

val appModule = module {
    single<SpaceXApi> { SpaceXApi() }
    single<SpaceXSDK> {
        SpaceXSDK(
            databaseDriverFactory = AndroidDatabaseDriverFactory(androidContext()),
            api = get()
        )
    }
    single { RocketLaunchViewModel(sdk = get()) }
}