package com.worldline.apigouvfr.ban.sampleapp.ui.state

import com.worldline.apigouvfr.ban.datasource.response.BanPropertiesResponse

class SearchUiState(
    val query: String = "",
    val loading: Boolean = false,
    val error: Boolean = false,
    val rawResponse: List<BanPropertiesResponse> = emptyList()
)