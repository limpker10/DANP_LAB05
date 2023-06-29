package com.example.app04

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class DataItemViewModel : ViewModel() {
    private val pageSize = 10 // Tamaño de página deseado

    fun getDataItems(): Flow<PagingData<DataItem>> {
        return Pager(PagingConfig(pageSize)) {
            DataItemPagingSource(pageSize)
        }.flow
    }
}
