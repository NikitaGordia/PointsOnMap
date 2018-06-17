package com.nikitagordia.pointonmap.model.data

import com.google.android.gms.maps.model.LatLng

/**
 * Created by nikitagordia on 6/16/18.
 */

data class Point(val position: LatLng, val title: String, val description: String)