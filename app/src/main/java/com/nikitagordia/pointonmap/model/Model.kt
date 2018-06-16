package com.nikitagordia.pointonmap.model

import com.nikitagordia.pointonmap.model.data.Point

/**
 * Created by nikitagordia on 6/16/18.
 */

interface Model {

    fun getPoints(): List<Point>?

}