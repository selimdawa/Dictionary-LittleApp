package com.littleapp.dictionary.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.littleapp.dictionary.Unit.DATA
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONArray
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.suspendCancellableCoroutine

@HiltViewModel
class MainViewModel @Inject constructor(
    private val requestQueue: RequestQueue
) : ViewModel() {

    private val _definition = MutableLiveData<String>()
    val definition: LiveData<String> get() = _definition

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun searchWord(word: String) {
        viewModelScope.launch {
            try {
                val url = "${DATA.DICTIONARY_BASIC_URL}$word?key=${DATA.DICTIONARY_API_KEY}"
                val response = fetchDefinition(url)
                val definitionText = extractDefinitionFromJson(response)
                _definition.postValue(definitionText)
            } catch (e: Exception) {
                _error.postValue(e.message ?: "An error occurred")
            }
        }
    }

    private suspend fun fetchDefinition(url: String): String = suspendCancellableCoroutine { continuation ->
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response -> continuation.resume(response) },
            { error -> continuation.resumeWithException(Exception(error.message)) }
        )
        requestQueue.add(stringRequest)
        continuation.invokeOnCancellation { stringRequest.cancel() }
    }

    private fun extractDefinitionFromJson(response: String): String {
        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        val getShotDefinition = firstIndex.getJSONArray(DATA.Short_Def)
        return getShotDefinition.getString(0)
    }
}
