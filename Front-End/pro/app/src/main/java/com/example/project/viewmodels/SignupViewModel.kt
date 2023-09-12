package com.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.AuctionDetailResponseDTO
import com.example.project.api.AuctionFilterDTO
import com.example.project.api.AuctionService
import com.example.project.api.RegisterAuctionDTO
import com.example.project.api.SignupService
import com.example.project.sharedpreferences.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val service: SignupService,
) : ViewModel() {

    private val _auctionItems = MutableStateFlow<List<AuctionItemData>>(emptyList())
    val auctionItems: StateFlow<List<AuctionItemData>> = _auctionItems

}

