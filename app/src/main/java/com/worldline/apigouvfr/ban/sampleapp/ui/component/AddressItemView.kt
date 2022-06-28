package com.worldline.apigouvfr.ban.sampleapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.worldline.apigouvfr.ban.datasource.response.BanPropertiesResponse

@Composable
fun AddressItemView(
    banPropertiesResponse: BanPropertiesResponse,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.clickable { onClick() }.padding(horizontal = 24.dp, vertical = 12.dp)) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "${banPropertiesResponse.housenumber.orEmpty()} ${banPropertiesResponse.street.orEmpty()}"
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "${banPropertiesResponse.postcode.orEmpty()} ${banPropertiesResponse.city.orEmpty()}"
        )
    }
}
