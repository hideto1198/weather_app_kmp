import android.os.Build
import org.weather_app_kmp.app.shared.BuildKonfig

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT} ${BuildKonfig.YAHOO_API_KEY}"
}

actual fun getPlatform(): Platform = AndroidPlatform()