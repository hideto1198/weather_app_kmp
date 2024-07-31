import org.weather_app_kmp.app.shared.BuildKonfig
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion + " ${BuildKonfig.YAHOO_API_KEY}"
}

actual fun getPlatform(): Platform = IOSPlatform()