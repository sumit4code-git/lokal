package com.example.lokalinterview.Response

import java.io.Serializable

data class article (
    var key:Int?,
    var id:Int?,
    var title:String?,
    var url:String?,
    var thumbnailUrl:String?
    ): Serializable