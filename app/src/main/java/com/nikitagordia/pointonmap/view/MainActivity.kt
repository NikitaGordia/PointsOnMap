package com.nikitagordia.pointonmap.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.nikitagordia.pointonmap.R
import com.nikitagordia.pointonmap.model.data.Point
import com.nikitagordia.pointonmap.presenter.MainPresenter
import com.nikitagordia.pointonmap.presenter.Presenter

class MainActivity : AppCompatActivity(), ViewInterface {

    private var map: GoogleMap? = null

    private val presenter: Presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync( { map = it } )

        presenter.fetchData()
    }

    override fun onData(points: List<Point>) {

    }

    override fun onError() {
        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
    }
}
