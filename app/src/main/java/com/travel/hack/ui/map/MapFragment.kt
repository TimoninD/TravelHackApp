package com.travel.hack.ui.map

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.travel.hack.R
import com.travel.hack.entity.core.PlaceIdList
import com.travel.hack.ui.common.BaseFragment
import com.travel.hack.viewmodel.MapViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MapFragment : BaseFragment(), OnMapReadyCallback {
    override val layoutResId: Int = R.layout.fragment_map

    private var googleMap: GoogleMap? = null

    private val args: MapFragmentArgs by navArgs()

    private val viewModel: MapViewModel by viewModel {
        parametersOf(args.listIds)
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(12.toDouble(), 32.toDouble()), 2f))

        viewModel.places.observe(viewLifecycleOwner, {
            val latLng = LatLng(12.toDouble(), 32.toDouble())
            googleMap?.addPolyline(
                PolylineOptions()
                    .clickable(true)
                    .add(latLng)
                    .addAll(it.map { LatLng(it.lat.toDouble(), it.lon.toDouble()) })
            )
            googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 1f))
        })
    }

}