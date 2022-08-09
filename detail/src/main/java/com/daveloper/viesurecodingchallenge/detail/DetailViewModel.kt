package com.daveloper.viesurecodingchallenge.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daveloper.viesurecodingchallenge.data.local.database.Article
import com.daveloper.viesurecodingchallenge.domain.GetArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getArticle: GetArticle,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    data class DetailState(
        val article: Article? = null
    )

    var state by mutableStateOf(DetailState())

    init {
        savedStateHandle.get<Int>("id")?.run {
            if (this != -1) {
                loadArticle(this)
            }
        }
    }

    private fun loadArticle(id: Int) {
        viewModelScope.launch {
            val article = getArticle(id)
            state = state.copy(
                article = article
            )
        }
    }
}