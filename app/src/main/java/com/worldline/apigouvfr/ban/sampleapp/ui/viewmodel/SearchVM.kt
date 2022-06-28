package com.worldline.apigouvfr.ban.sampleapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.worldline.apigouvfr.ban.BanResult
import com.worldline.apigouvfr.ban.BanService
import com.worldline.apigouvfr.ban.parameter.BanSearchParameters
import com.worldline.apigouvfr.ban.sampleapp.ui.state.SearchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchVM : ViewModel() {

    private val banService: BanService by lazy { BanService() }

    private var query = "3 rue de la Gare"

    private val _uiState = MutableStateFlow(SearchUiState(query = query))
    val uiState: StateFlow<SearchUiState> = _uiState

    fun updateQuery(newQuery: String) {
        query = newQuery
    }

    fun search() {
        _uiState.value = SearchUiState(loading = true)
        viewModelScope.launch {
            val banSearchParameters = BanSearchParameters(
                query = query,
                limit = 20
            )
            val banResult = banService.search(banSearchParameters)
            _uiState.value = when (banResult) {
                is BanResult.Success -> {
                    SearchUiState(
                        query = query,
                        rawResponse = banResult.data.features.map { it.properties }
                    )
                }
                else -> {
                    SearchUiState(
                        query = query,
                        error = true
                    )
                }
            }
        }
    }
}
