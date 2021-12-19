package com.example.lokalinterview.repo

import com.example.lokalinterview.api.RetrofitInstance
//2nd step
class listreoisitory {
    suspend fun getAllList()=
        RetrofitInstance.api.Searchnews()
}