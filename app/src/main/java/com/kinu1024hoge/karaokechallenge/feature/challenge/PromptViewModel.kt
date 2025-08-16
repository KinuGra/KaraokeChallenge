package com.kinu1024hoge.karaokechallenge.feature.challenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinu1024hoge.karaokechallenge.data.PromptDto
import com.kinu1024hoge.karaokechallenge.data.PromptRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class PromptUiState(
    val loading: Boolean = false,
    val prompt: PromptDto? = null,
    val error: String? = null
)

class PromptViewModel(
    private val repo: PromptRepository = PromptRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(PromptUiState())
    val state: StateFlow<PromptUiState> = _state

    private var currentJob: Job? = null

    fun loadRandom() {
        currentJob?.cancel()
        _state.value = _state.value.copy(loading = true, error = null)
        currentJob = viewModelScope.launch {
            runCatching { repo.fetchRandom() }
                .onSuccess { p ->
                    _state.value = PromptUiState(loading = false, prompt = p, error = null)
                }
                .onFailure { e ->
                    _state.value = PromptUiState(loading = false, prompt = null, error = e.message)
                }
        }
    }
}