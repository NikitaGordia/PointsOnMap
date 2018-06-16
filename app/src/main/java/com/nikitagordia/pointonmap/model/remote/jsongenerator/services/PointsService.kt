package com.nikitagordia.pointonmap.model.remote.jsongenerator.services

import com.nikitagordia.pointonmap.model.remote.jsongenerator.data.PointsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by nikitagordia on 6/16/18.
 */

interface PointsService {

    @GET("/api/json/get/bPvorlFkwO")
    fun getGroups(@Query("indent") indent : Int) : Call<List<PointsResponse>>
}