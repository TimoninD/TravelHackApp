package com.travel.hack.ui.map

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.travel.hack.R
import com.travel.hack.entity.core.Place
import com.travel.hack.ext.bitmapDescriptorFromVector
import com.travel.hack.ext.show
import com.travel.hack.model.Prefs
import com.travel.hack.ui.common.BaseFragment
import com.travel.hack.viewmodel.MapViewModel
import kotlinx.android.synthetic.main.bottom_sheet_detail_info.*
import kotlinx.android.synthetic.main.bottom_sheet_detail_info.view.*
import kotlinx.android.synthetic.main.fragment_map.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MapFragment : BaseFragment(), OnMapReadyCallback {
    override val layoutResId: Int = R.layout.fragment_map

    private var googleMap: GoogleMap? = null

    private val args: MapFragmentArgs by navArgs()

    private val viewModel: MapViewModel by viewModel {
        parametersOf(args.listIds)
    }

    private var mapOfPlaces: HashMap<String, Place> = HashMap()

    private val prefs: Prefs by inject()

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map

        googleMap?.setOnMarkerClickListener {
            val currentPlace = mapOfPlaces[it.id]
            currentPlace?.let {
                Glide.with(requireContext())
                    .load(it.image)
                    .placeholder(R.color.placeholder)
                    .centerCrop()
                    .into(ivImage)

                tvTitle.text = it.title
                tvDescription.text = it.description

                val behavior = BottomSheetBehavior.from(bottomSheetDetailInfo as ConstraintLayout)

                behavior.show()
            }
            true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        mapOfPlaces.clear()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        viewModel.places.observe(viewLifecycleOwner, { list ->
            handlePlaceList(list, mapOfPlaces)
        })

    }

    private fun handlePlaceList(list: List<Place>, mapOfPlaces: HashMap<String, Place>) {
        val options = MarkerOptions()

        val firstLatLng = LatLng(
            list.firstOrNull()?.lat?.toDouble() ?: 0.toDouble(),
            list.firstOrNull()?.lon?.toDouble() ?: 0.toDouble()
        )

        options.icon(requireContext().bitmapDescriptorFromVector(R.drawable.ic_marker_start))
        if (prefs.lat != 0f || prefs.lng != 0f) {
            updateMarkerRoute(
                options,
                LatLng(prefs.lat.toDouble(), prefs.lng.toDouble()),
                firstLatLng
            )
            options.icon(requireContext().bitmapDescriptorFromVector(R.drawable.ic_marker))
        }

        list.forEachIndexed { index, place ->
            if (index > 0) {
                options.icon(requireContext().bitmapDescriptorFromVector(R.drawable.ic_marker))
            }
            val nextPlace =
                if (index == list.size - 1) place else list[index + 1]

            val marker = updateMarkerRoute(
                options,
                startLatLng = LatLng(place.lat.toDouble(), place.lon.toDouble()),
                endLatLng = LatLng(nextPlace.lat.toDouble(), nextPlace.lon.toDouble()),
            )
            marker?.let {
                mapOfPlaces[it.id] = place
            }
        }

        googleMap?.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                firstLatLng, ZOOM_LEVEL
            )
        )
    }

    private fun updateMarkerRoute(
        options: MarkerOptions,
        startLatLng: LatLng,
        endLatLng: LatLng
    ): Marker? {
        googleMap?.addPolyline(
            PolylineOptions()
                .clickable(true)
                .add(startLatLng)
                .add(endLatLng)
        )

        options.position(startLatLng)
        return googleMap?.addMarker(options)
    }

    companion object {
        private const val ZOOM_LEVEL = 17f
    }
}