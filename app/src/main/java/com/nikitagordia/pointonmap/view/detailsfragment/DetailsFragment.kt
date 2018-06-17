package com.nikitagordia.pointonmap.view.detailsfragment

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.gms.maps.model.LatLng
import com.nikitagordia.pointonmap.R
import com.nikitagordia.pointonmap.model.data.Point

/**
 * Created by nikitagordia on 6/17/18.
 */

class DetailsFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.fragment_details, container, false)

        val title = v?.findViewById<TextView>(R.id.title)
        val description = v?.findViewById<TextView>(R.id.description)
        val lat = v?.findViewById<TextView>(R.id.lat)
        val lon = v?.findViewById<TextView>(R.id.lon)

        if (arguments != null) {
            val pt = getPoint(arguments)
            lat?.text = pt.position.latitude.toString()
            lon?.text = pt.position.longitude.toString()
            title?.text = pt.title
            description?.text = pt.description
        } else {
            description?.text = resources.getString(R.string.empty)
        }

        return v
    }

    companion object {

        const val TAG = "com.nikitagordia.pointonmap.view.detailsfragment.TAG"

        private const val EXTRA_POINT_LAT = "com.nikitagordia.pointonmap.view.detailsfragment.point.lat"
        private const val EXTRA_POINT_LON = "com.nikitagordia.pointonmap.view.detailsfragment.point.lon"
        private const val EXTRA_POINT_TITLE = "com.nikitagordia.pointonmap.view.detailsfragment.point.title"
        private const val EXTRA_POINT_DESCRIPTION = "com.nikitagordia.pointonmap.view.detailsfragment.point.description"

        fun getInstance(point: Point): DetailsFragment {
            val result = DetailsFragment()
            val b = Bundle()
            b.putDouble(EXTRA_POINT_LAT, point.position.latitude)
            b.putDouble(EXTRA_POINT_LON, point.position.longitude)
            b.putString(EXTRA_POINT_TITLE, point.title)
            b.putString(EXTRA_POINT_DESCRIPTION, point.description)
            result.arguments = b
            return result
        }

        fun getPoint(arg: Bundle) = Point(
                LatLng(arg.getDouble(EXTRA_POINT_LAT), arg.getDouble(EXTRA_POINT_LON)),
                arg.getString(EXTRA_POINT_TITLE),
                arg.getString(EXTRA_POINT_DESCRIPTION)
        )
    }
}