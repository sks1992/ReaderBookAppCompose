package sk.sandeep.readerappcompose.ui.screens.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sk.sandeep.readerappcompose.data.Resource
import sk.sandeep.readerappcompose.model.Item
import sk.sandeep.readerappcompose.repository.BookRepository
import javax.inject.Inject

@HiltViewModel
class ReaderBookSearchViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {
    var list: List<Item> by mutableStateOf(listOf())
    var isLoading by mutableStateOf(true)

    init {
        loadBooks()
    }

    private fun loadBooks() {
        searchBooks("flutter")
    }

    fun searchBooks(query: String) {
        viewModelScope.launch {
            isLoading = true
            if (query.isEmpty()) return@launch

            try {
                when (val response = repository.getBooks(searchQuery = query)) {
                    is Resource.Success -> {
                        list = response.data!!
                        if (list.isNotEmpty()) isLoading = false
                    }
                    is Resource.Error -> {
                        isLoading = false
                        Log.e("Network", "searchBooks: Failed getting Book")
                    }
                    else -> {
                        isLoading = false
                    }
                }
            } catch (e: Exception) {
                isLoading = false
                Log.d("Network", "searchBooks: ${e.message.toString()}")
            }
        }
    }


    /* val listOfBooks: MutableState<DataOrException<List<Item>, Boolean, Exception>> =
         mutableStateOf(DataOrException(listOf(), true, Exception("")))

     init {
         searchBooks("android")
     }

     fun searchBooks(query: String) {
         viewModelScope.launch {
             if (query.isEmpty()) {
                 return@launch
             }
             listOfBooks.value.loading = true
             listOfBooks.value = repository.getBooks(query)
             Log.d("Search", "searchBooks: ${listOfBooks.value.data}")
             if (listOfBooks.value.data.toString().isNotEmpty()) listOfBooks.value.loading = false
         }
     }*/
}