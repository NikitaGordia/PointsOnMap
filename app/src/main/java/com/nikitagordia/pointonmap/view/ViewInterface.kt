package com.nikitagordia.pointonmap.view

import com.nikitagordia.pointonmap.model.data.Point

/**
 * Created by nikitagordia on 6/16/18.
 */

interface ViewInterface {

    fun onData(points: List<Point>)

    fun onError()
}