package com.daveloper.viesurecodingchallenge.presentation.overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daveloper.viesurecodingchallenge.data.util.Resource
import com.daveloper.viesurecodingchallenge.data.util.UiText
import com.daveloper.viesurecodingchallenge.domain.entities.Article
import com.daveloper.viesurecodingchallenge.domain.usecases.GetArticles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val getArticles: GetArticles
) : ViewModel() {
    data class OverviewState(
        val items: List<Article> = emptyList(),
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val errorMsg: UiText = UiText.DynamicString("")
    )

    var state by mutableStateOf(OverviewState())
        private set

    init {
        loadArticles()
    }

    private fun loadArticles() {
        viewModelScope.launch {
            state = when (val articles = getArticles()) {
                is Resource.Success -> state.copy(
                    items = articles.data.orEmpty(),
                    isLoading = false,
                    isError = false
                )
                is Resource.Error -> state.copy(
                    isError = true,
                    isLoading = false,
                    errorMsg = articles.errorMsg
                )
            }
        }
    }
}