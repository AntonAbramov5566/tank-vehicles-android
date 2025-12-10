package ru.abramov.tank_reference_system.viewmoodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.abramov.tank_reference_system.data.repository.TankRepository

class TankViewModelFactory(private val repo: TankRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TankViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TankViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}