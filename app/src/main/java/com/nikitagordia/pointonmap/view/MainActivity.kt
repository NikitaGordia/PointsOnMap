package com.nikitagordia.pointonmap.view

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.nikitagordia.pointonmap.R
import com.nikitagordia.pointonmap.model.data.Point
import com.nikitagordia.pointonmap.presenter.MainPresenter
import com.nikitagordia.pointonmap.presenter.Presenter
import com.nikitagordia.pointonmap.view.detailsfragment.DetailsFragment

class MainActivity : AppCompatActivity(), ViewInterface {

    private var map: GoogleMap? = null

    private val presenter: Presenter = MainPresenter(this)

    private var points: List<Point>? = null

    private lateinit var coordinator: CoordinatorLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coordinator = findViewById<CoordinatorLayout>(R.id.coordinator)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync( {
            map = it
            map?.setOnMarkerClickListener { marker : Marker ->
                val point = points?.find { it.title == marker.title } ?: return@setOnMarkerClickListener false
                DetailsFragment.getInstance(point).show(supportFragmentManager, "mytg")
                return@setOnMarkerClickListener true
            }
            if (points != null) update(points!!)
        } )

        presenter.fetchData()
    }

    override fun onData(points: List<Point>) {
        if (map != null) update(points)
    }

    override fun onError() {
        Snackbar.make(coordinator, R.string.error, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.try_again, { presenter.fetchData() })
                .show()
    }

    private fun update(points: List<Point>) {
        this.points = points
        map?.clear()
        val bounds = LatLngBounds.builder()
        points.forEach {
            bounds.include(it.position)
            map?.addMarker(MarkerOptions()
                    .position(it.position)
                    .title(it.title))
        }
        val margin = resources.getDimensionPixelSize(R.dimen.map_inset_margin)
        val update = CameraUpdateFactory.newLatLngBounds(bounds.build(), margin)
        map?.animateCamera(update)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
    }
}
