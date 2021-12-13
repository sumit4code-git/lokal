package com.example.lokalinterview.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lokalinterview.Response.article
import com.example.lokalinterview.repo.listreoisitory
import com.example.lokalinterview.utils.Resource
import retrofit2.Response
import java.io.IOException

class viewModel(val repo:listreoisitory,app: Application): AndroidViewModel(app)  {

    val albumList: MutableLiveData<Resource<MutableList<article>>> = MutableLiveData()

    var breakingNewsResponse:List<article>?=null
    private suspend fun safeBreakingNewsCall(){
        val albumList: MutableLiveData<Resource<MutableList<article>>> = MutableLiveData()
        albumList .postValue(Resource.Loading())
        try{
//            if(hasInternetConnection()) {
                val response = repo.getAllList()
                albumList.postValue(HandleNewsResponse(response))
//            }
//            else{
//                albumList.postValue(Resource.Error("NO Internet Connection"))
//            }
        }catch (t:Throwable){
            when(t){
                is IOException -> albumList.postValue(Resource.Error("Network Failure"))
                else -> albumList.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun HandleNewsResponse(response:Response<MutableList<article>>):Resource<MutableList<article>>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse->
                if(breakingNewsResponse==null){
                    breakingNewsResponse=resultResponse
                }
                else{
                    val oldArticle=breakingNewsResponse?.article
                    val newArticle=resultResponse.articles

                    oldArticle?.addAll(newArticle)
                }
                return Resource.Sucess(breakingNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
//private fun hasInternetConnection():Boolean{
//    val connectivityManager= getApplication<Aplication>().getSystemService(
//        Context.CONNECTIVITY_SERVICE
//    ) as ConnectivityManager
//    if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
//        val activenetwork=connectivityManager.activeNetwork?:return false
//        val capabilities=connectivityManager.getNetworkCapabilities(activenetwork)?: return false
//        return when{
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)-> true
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)-> true
//            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)-> true
//            else ->false
//        }
//    }else{
//        connectivityManager.activeNetworkInfo?.run {
//            return when(type){
//                ConnectivityManager.TYPE_WIFI ->true
//                ConnectivityManager.TYPE_MOBILE ->true
//                ConnectivityManager.TYPE_ETHERNET ->true
//                else ->false
//            }
//        }
//    }
//    return false;
//}