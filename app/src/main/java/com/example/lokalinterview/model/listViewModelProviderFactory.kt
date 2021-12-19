package com.example.lokalinterview.model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lokalinterview.repo.listreoisitory

class listViewModelProviderFactory (
    val app:Application,
    val listRepository: listreoisitory
        ): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel(listRepository,app) as T
    }
}
