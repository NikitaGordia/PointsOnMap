package com.nikitagordia.pointonmap.model.remote.jsongenerator

import com.google.android.gms.maps.model.LatLng
import com.nikitagordia.pointonmap.model.Model
import com.nikitagordia.pointonmap.model.data.Point
import com.nikitagordia.pointonmap.model.remote.jsongenerator.services.PointsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by nikitagordia on 6/16/18.
 */

class MainModel : Model {

    private val MAIN_URL = "http://www.json-generator.com/"
    private val INDENT = 2

    private val retrofit = Retrofit.Builder()
            .baseUrl(MAIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val pointsFetcher = retrofit.create(PointsService::class.java)

    override fun getPoints(): List<Point>? {
        val res = pointsFetcher.getGroups(INDENT).execute()
        return res.body()
                ?.filter { it.lat != null && it.lng != null && it.title != null && it.description != null }
                ?.map { Point(LatLng(it.lat!!, it.lng!!), it.title!!, it.description!!) }
    }
}