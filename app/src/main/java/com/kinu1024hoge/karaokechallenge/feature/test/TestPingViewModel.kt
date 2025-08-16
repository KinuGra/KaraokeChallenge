package com.kinu1024hoge.karaokechallenge.feature.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinu1024hoge.karaokechallenge.data.PingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UiState(
    val loading: Boolean = false,
    val result: String? = null,
    val error: String? = null
)

class TestPingViewModel(
    private val repo: PingRepository = PingRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun callPing() {
        _uiState.value = UiState(loading = true)
        viewModelScope.launch {
            val res = repo.ping()
            _uiState.value = res.fold(
                onSuccess = { UiState(result = it.message) },
                onFailure = { UiState(error = it.message ?: "Unknown error") }
            )
        }
    }
}
