package com.discoverita.pixum.ui.comics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.discoverita.pixum.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllComicsViewModel @Inject constructor(private val marvelRepository: MarvelRepository) :
    ViewModel() {

    val comicsPager = marvelRepository.fetchAllComics().cachedIn(viewModelScope)

}