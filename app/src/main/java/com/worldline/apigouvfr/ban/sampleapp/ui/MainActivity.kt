package com.worldline.apigouvfr.ban.sampleapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.worldline.apigouvfr.ban.sampleapp.R
import com.worldline.apigouvfr.ban.sampleapp.ui.component.SearchForm
import com.worldline.apigouvfr.ban.sampleapp.ui.theme3.BanLibTheme
import com.worldline.apigouvfr.ban.sampleapp.ui.viewmodel.SearchVM

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BanLibTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val searchVM by viewModels<SearchVM>()
                    SearchForm(
                        searchVM.uiState.collectAsState().value,
                        searchVM::updateQuery,
                        searchVM::search,
                        { openSrc() }
                    ) { banPropertiesResponse ->
                        val address =
                            "${banPropertiesResponse.housenumber.orEmpty()} ${banPropertiesResponse.street.orEmpty()}, " +
                                "${banPropertiesResponse.postcode.orEmpty()} ${banPropertiesResponse.city.orEmpty()}"
                        openGoogleMaps(address)
                    }
                }
            }
        }
    }

    private fun openSrc() =
        openUrl(getString(R.string.url_src))

    private fun openGoogleMaps(address: String) =
        openUrl("https://www.google.com/maps/search/$address")

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
