package com.example.lokalinterview.model

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lokalinterview.Aplication
import com.example.lokalinterview.Response.article
import com.example.lokalinterview.repo.listreoisitory
import com.example.lokalinterview.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
//2nd step
class viewModel (val repo: listreoisitory, app: Application): AndroidViewModel(app)  {

    val albumList: MutableLiveData<Resource<MutableList<article>>> = MutableLiveData()

    var ListResponse:MutableList<article>?=null
    init {
        getList( )
    }
    fun getList()=viewModelScope.launch {
        albumList.postValue(Resource.Loading())
        val response=repo.getAllList()
        albumList.postValue(HandleNewsResponse(response))
//        safeListCall()
    }
    private fun HandleNewsResponse(response: Response<MutableList<article>>): Resource<MutableList<article>> {
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                return Resource.Sucess(ListResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

//to work on internet connectivity
//    private fun HandleNewsResponse(response: Response<MutableList<article>>): Resource<MutableList<article>> {
//        if(response.isSuccessful){
//            response.body()?.let { resultResponse->
////                if(ListResponse==null){
////                    ListResponse=resultResponse
////                }
////                else{
////                    val oldArticle=ListResponse
////                    val newArticle=resultResponse
////
////                    oldArticle?.addAll(newArticle)
////                }
//                return Resource.Sucess(ListResponse ?: resultResponse)
//            }
//        }
//        return Resource.Error(response.message())
//    }
//    private suspend fun safeListCall(){
//        val albumList: MutableLiveData<Resource<MutableList<article>>> = MutableLiveData()
//        albumList .postValue(Resource.Loading())
//        try{
//            if(hasInternetConnection()) {
//                val response = repo.getAllList()
//                albumList.postValue(HandleNewsResponse(response))
//            }
//            else{
//                albumList.postValue(Resource.Error("NO Internet Connection"))
//            }
//        }catch (t:Throwable){
//            when(t){
//                is IOException -> albumList.postValue(Resource.Error("Network Failure"))
//                else -> albumList.postValue(Resource.Error("Conversion Error"))
//            }
//        }
//    }



//    private fun hasInternetConnection():Boolean{
//        val connectivityManager=getApplication<Aplication>().getSystemService(
//            Context.CONNECTIVITY_SERVICE
//        ) as ConnectivityManager
//        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
//            val activenetwork=connectivityManager.activeNetwork?:return false
//            val capabilities=connectivityManager.getNetworkCapabilities(activenetwork)?: return false
//            return when{
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)-> true
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)-> true
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)-> true
//                else ->false
//            }
//        }else{
//            connectivityManager.activeNetworkInfo?.run {
//                return when(type){
//                    ConnectivityManager.TYPE_WIFI ->true
//                    ConnectivityManager.TYPE_MOBILE ->true
//                    ConnectivityManager.TYPE_ETHERNET ->true
//                    else ->false
//                }
//            }
//        }
//        return false;
//    }
}
