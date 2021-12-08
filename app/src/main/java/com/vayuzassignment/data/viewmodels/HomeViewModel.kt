package com.vayuzassignment.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vayuzassignment.data.model.Resource
import com.vayuzassignment.data.model.Response
import com.vayuzassignment.data.retrofit.HomeRepository
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val getImagesData = MutableLiveData<Resource<Response>>()
    val getImageResponse: LiveData<Resource<Response>>
        get() = getImagesData

    fun getImages() {
        viewModelScope.launch {
            try {
                getImagesData.value = Resource.loading()
                val response = homeRepository.getImages()

                if (response.status == "success") {
                    getImagesData.value = Resource.success(response)
                } else {
                    getImagesData.value = Resource.error(message = response.message)
                }
            } catch (e: Exception) {
                if (e is UnknownHostException) {
                    getImagesData.value = Resource.offlineError()
                } else {
                    getImagesData.value = Resource.error(e)
                }
            }
        }
    }

}