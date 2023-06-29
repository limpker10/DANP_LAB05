package com.example.app04

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kotlinx.coroutines.flow.Flow


@Composable
fun DataItemScreen(viewModel: DataItemViewModel) {
    val dataItems: Flow<PagingData<DataItem>> = viewModel.getDataItems()

    val lazyDataItems: LazyPagingItems<DataItem> = dataItems.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyDataItems) { dataItem ->
            if (dataItem != null) {
                DataItemCard(dataItem)
            }
        }
        lazyDataItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { /* Mostrar un indicador de carga durante la actualización */ }
                }
                loadState.append is LoadState.Loading -> {
                    item { /* Mostrar un indicador de carga durante la carga de más elementos */ }
                }
                loadState.refresh is LoadState.Error -> {
                    val errorMessage = (loadState.refresh as LoadState.Error).error.message
                    item { /* Mostrar un mensaje de error durante la actualización */ }
                }
                loadState.append is LoadState.Error -> {
                    val errorMessage = (loadState.append as LoadState.Error).error.message
                    item { /* Mostrar un mensaje de error durante la carga de más elementos */ }
                }
            }
        }
    }
}
@Composable
fun DataItemCard(dataItem: DataItem) {
    // Placeholder implementation for rendering each dataItem
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = dataItem.id.toString())
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = dataItem.temperature)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = dataItem.temperature)
        }
    }
}