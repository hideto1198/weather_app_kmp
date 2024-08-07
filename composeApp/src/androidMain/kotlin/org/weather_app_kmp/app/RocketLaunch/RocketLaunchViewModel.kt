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
    private val _state = mutableStateOf(RocketLaunchScreenState())
    val state: State<RocketLaunchScreenState> = _state

    init {
        loadLaunches()
    }
    
    fun loadLaunches() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, launches = emptyList())
            try {
                val launches = sdk.getLaunches(forceReload = true)
                _state.value = _state.value.copy(isLoading = false, launches = launches)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, launches = emptyList())
            }
        }
    }
}

data class RocketLaunchScreenState(
    val isLoading: Boolean = false,
    val launches: List<RocketLaunch> = emptyList()
)