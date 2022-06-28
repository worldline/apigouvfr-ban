package com.worldline.apigouvfr.ban.sampleapp.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.worldline.apigouvfr.ban.datasource.response.BanPropertiesResponse
import com.worldline.apigouvfr.ban.sampleapp.R
import com.worldline.apigouvfr.ban.sampleapp.ui.state.SearchUiState
import com.worldline.apigouvfr.ban.sampleapp.ui.theme3.BanLibTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchForm(
    searchUiState: SearchUiState,
    updateQuery: (newQuery: String) -> Unit,
    send: () -> Unit,
    onSrcClick: () -> Unit,
    onItemClick: (banPropertiesResponse: BanPropertiesResponse) -> Unit
) {
    val verticalArrangement = Arrangement.spacedBy(8.dp)

    Column(modifier = Modifier.fillMaxWidth()) {

        val padding = 24.dp
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(padding),
            verticalArrangement = verticalArrangement
        ) {

            var query by remember { mutableStateOf(searchUiState.query) }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = query,
                onValueChange = {
                    query = it
                    updateQuery(it)
                }
            )
            AnimatedVisibility(visible = searchUiState.loading) {
                Text(
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.loading)
                )
            }
            AnimatedVisibility(visible = searchUiState.error) {
                Text(
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.error)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = onSrcClick
                ) {
                    Text(text = stringResource(id = R.string.source))
                }
                Button(
                    enabled = query.isNotEmpty(),
                    onClick = send
                ) {
                    Text(text = stringResource(id = R.string.send))
                }
            }
        }

        AnimatedVisibility(visible = searchUiState.rawResponse.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth(),
                verticalArrangement = verticalArrangement
            ) {
                items(searchUiState.rawResponse) { banPropertiesResponse ->
                    AddressItemView(banPropertiesResponse = banPropertiesResponse) {
                        onItemClick(banPropertiesResponse)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BanLibTheme {
        SearchForm(
            SearchUiState(
                query = "Default",
                loading = true,
                error = true,
                rawResponse = listOf(
                    BanPropertiesResponse(type = ""),
                    BanPropertiesResponse(type = "")
                )
            ),
            {}, {}, {}, {}
        )
    }
}
