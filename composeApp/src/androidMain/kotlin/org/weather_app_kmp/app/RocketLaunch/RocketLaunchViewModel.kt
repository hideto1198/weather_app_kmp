package org.weather_app_kmp.app.RocketLaunch

import SpaceXSDK
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import entity.RocketLaunch
import kotlinx.coroutines.launch
import java.lang.Exception

class RocketLaunchViewModel(
    private val sdk: SpaceXSDK
) : ViewModel() {
    init {
        loadLaunches()
    }
    private val _state = mutableStateOf(RocketLaunchScreenState())
    val state: State<RocketLaunchScreenState> = _state
    
    fun loadLaunches() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, launches = emptyList())
            try {
                val lauches = sdk.getLaunches(forceReload = true)
                _state.value = _state.value.copy(isLoading = false, launches = lauches)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}

data class RocketLaunchScreenState(
    val isLoading: Boolean = false,
    val launches: List<RocketLaunch> = emptyList()
)