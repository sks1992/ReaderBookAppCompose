package sk.sandeep.readerappcompose.repository

import sk.sandeep.readerappcompose.data.Resource
import sk.sandeep.readerappcompose.model.Item
import sk.sandeep.readerappcompose.network.BooksApi
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val api: BooksApi
) {
    suspend fun getBooks(searchQuery: String): Resource<List<Item>> {
        return try {
            Resource.Loading(data = true)
            val itemList = api.getAllBooks(searchQuery).items
            if (itemList.isNotEmpty()) Resource.Loading(data = false)
            Resource.Success(data = itemList)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }
    }

    suspend fun getBookInfo(bookId: String): Resource<Item> {
        val response = try {
            Resource.Loading(data = true)
            api.getBooksInfo(bookId = bookId)
        } catch (e: Exception) {
            return Resource.Error(message = "An error occurred ${e.message.toString()}")
        }
        Resource.Loading(data = false)
        return Resource.Success(data = response)
    }
}

/* private val dataOrException = DataOrException<List<Item>, Boolean, Exception>()
 private val bookInfoDataException = DataOrException<Item, Boolean, Exception>()

 suspend fun getBooks(searchQuery: String): DataOrException<List<Item>, Boolean, Exception> {
     try {
         dataOrException.loading = true
         dataOrException.data = api.getAllBooks(searchQuery).items

         if (dataOrException.data!!.isNotEmpty()) dataOrException.loading = false

     } catch (e: Exception) {
         dataOrException.e = e
     }
     return dataOrException
 }

 suspend fun getBookInfo(bookId: String): DataOrException<Item, Boolean, Exception> {
    val response =  try {
         bookInfoDataException.loading = true
         bookInfoDataException.data = api.getBooksInfo(bookId = bookId)

         if (bookInfoDataException.data.toString().isNotEmpty()) bookInfoDataException.loading =
             false
        else {}

     } catch (e: Exception) {
         bookInfoDataException.e = e
     }
     return bookInfoDataException
 }*/
