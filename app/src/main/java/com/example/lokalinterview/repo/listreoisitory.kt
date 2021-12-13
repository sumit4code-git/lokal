package com.example.lokalinterview.repo

import com.example.lokalinterview.api.RetrofitInstance

class listreoisitory {
    suspend fun getAllList()=
        RetrofitInstance.api.Searchnews()
}