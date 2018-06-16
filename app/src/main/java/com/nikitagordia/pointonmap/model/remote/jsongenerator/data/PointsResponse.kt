package com.nikitagordia.pointonmap.model.remote.jsongenerator.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by nikitagordia on 6/16/18.
 */

class PointsResponse {

    @SerializedName("lat")
    @Expose
    val lat: Double? = null
    @SerializedName("description")
    @Expose
    val description: String? = null
    @SerializedName("id")
    @Expose
    val id: Int? = null
    @SerializedName("lng")
    @Expose
    val lng: Double? = null
    @SerializedName("title")
    @Expose
    val title: String? = null
}