package org.sopt.at.my

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute

class MyViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val profile = savedStateHandle.toRoute<My>()
}