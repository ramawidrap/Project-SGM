package com.example.ramaw.sgm2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.GoogleMap
import java.util.jar.Manifest
import com.google.android.gms.maps.SupportMapFragment
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.location.Location
import android.support.annotation.NonNull
import android.view.View
import android.widget.*
import com.example.ramaw.sgm2.Models.DataBaseHandler
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions


class SgmNearby : AppCompatActivity() , OnMapReadyCallback {

    override fun onMapReady(googleMap: GoogleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show()
        Log.d("MapActivity", "onMapReady: map is ready")
        mMap = googleMap

        if (mLocationPermissionsGranted) {

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Log.d("MapActivity","kelar move camera")
            Log.d("MapActivity",mMap.toString())
            mMap?.isMyLocationEnabled=true


        }
    }



    private fun moveCamera(latLng: LatLng, zoom: Float) {
        Log.d("MapActivity", "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude)
        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
        Log.d("MapActivity","berhasil")
    }


    private fun initMap() {
        Log.d("MapActivity", "initMap: initializing map")
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        mapFragment!!.getMapAsync(this)
    }

    private val FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION
    private val COURSE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION
    private val LOCATION_PERMISSION_REQUEST_CODE = 1234
    private val DEFAULT_ZOOM = 15f

    private var mLocationPermissionsGranted = false
    private var mMap: GoogleMap? = null

    private var mFusedLocationProviderClient : FusedLocationProviderClient? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sgm_nearby)
        val listproduct=ArrayList<String>()
        val find=findViewById<ImageView>(R.id.find)
        val product=findViewById<Spinner>(R.id.product)
        val from=findViewById<TextView>(R.id.from)
        val nptb=findViewById<TextView>(R.id.nptb)
        val sgmpoint=findViewById<ImageView>(R.id.sgmpoint)

        val db=DataBaseHandler(this)
        listproduct.add("-")
        db.readData("Susu","kategori")
        for(item in db.readData("Susu","kategori")){
            Log.d("spinner","cekmasuk")
            listproduct.add(item.nama)
        }
        Log.d("spinner",listproduct.size.toString())
        val adapter=ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listproduct)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        product.setAdapter(adapter)
        Log.d("spinner","setadapter")

        var nearby = LatLng(-6.873125, 107.642044)
        var nptbtext=""
        Log.d("spinner","setnearby")

        product.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("spinner","kosong")
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("spinner","masuk siniii")
                if(listproduct.get(position) == "SGM Aktif Presinutri 4 Plus"){
                    nearby = LatLng(-6.873125, 107.642044)
                    nptbtext="Jl. Awiligar Raya I, Cibeunying, Cimenyan, Bandung, Jawa Barat 40191"
                }
                else if(listproduct.get(position) == "SGM Aktif Presinutri 400gr" || listproduct.get(position) == "SGM Aktif Presinutri 900gr" ){
                    nearby = LatLng(-6.874506, 107.640790)
                    nptbtext="Jl. Awiligar Ria I No.40, Cibeunying, Cimenyan, Bandung, Jawa Barat 40191"
                }
                else if(listproduct.get(position) == "SGM EKsplor 3PLUS Buah dan Sayur 400gr" || listproduct.get(position) == "SGM EKsplor 3PLUS Buah dan Sayur 900gr"){
                    nearby = LatLng(-6.877856, 107.633891)
                    nptbtext="Jl. Ligar Agung 71, Cibeunying, Cimenyan, Bandung, Jawa Barat 40191"
                }
                else if(listproduct.get(position) == "SGM Eksplor PHPro  400gr 1++" || listproduct.get(position) == "SGM Eksplor PHPro  900gr 1++"){
                    nearby = LatLng(-6.885999, 107.634508)
                    nptbtext="Indomaret, Cibeunying, Cimenyan, Bandung, West Java 40191"

                }
                else if(listproduct.get(position) == "SGM Eksplor PHPro  400gr 6-12 Bulan" || listproduct.get(position) == "SGM Eksplor PHPro  900gr 6-12 Bulan"){
                    nearby = LatLng(-6.884914, 107.634919)
                    nptbtext="Cibeunying, Cimenyan, Bandung, West Java"
                }
                find.setOnClickListener {
                    Log.d("spinner","carirute")
                    if(listproduct.get(position) != "-" && !from.text.isEmpty()){
                        if( from.text.toString().equals("Clove Garden Hotel Bandung",ignoreCase = true)) run {
                            var from = LatLng(-6.872717, 107.642689)
                            Log.d("MapActivity", "kelar setlocation")
                            mMap?.addMarker(MarkerOptions().position(from).title("Clove Garden").snippet("and snippet")
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)))
                            moveCamera(nearby, DEFAULT_ZOOM)
                            mMap?.getUiSettings()?.setMyLocationButtonEnabled(false)
                            mMap?.addMarker(MarkerOptions().position(nearby).title("Nearby"))
                            nptb.setText(nptbtext)

                        }
                    }
                }
            }

        }




        val sgmvision=findViewById<ImageView>(R.id.sgmvision)

        sgmpoint.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP )
            startActivity(intent)
            finish()
        }
        sgmvision.setOnClickListener {
            val intent=Intent(this,Nutrivision::class.java)
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP )
            startActivity(intent)
            finish()
        }
        if(isServicesOK()) {
            getLocationPermission()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getDeviceLocation() {
        Log.d("MapActivity", "getDeviceLocation: getting the devices current location")

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        try {
            if (mLocationPermissionsGranted) {
                val location=FusedLocationProviderClient(this).lastLocation
                location.addOnCompleteListener(object : OnCompleteListener<Location>{
                    override fun onComplete(p0: Task<Location>) {
                        if(p0.isSuccessful){
                            Log.d("MapActivity","found location")
                            var currentlocation:Location=p0.result as Location
                            Log.d("MapActivity",currentlocation.latitude.toString()+" "+currentlocation.longitude.toString())
                            moveCamera(LatLng(currentlocation.latitude,currentlocation.longitude),DEFAULT_ZOOM)
                        }
                        else{
                            Log.d("MapActivity","location null")
                        }
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                })
            }


        }catch (e : SecurityException ){
            Log.e("MapActivity","getDeviceLocation: SecurityException: " + e.message)
        }

    }
    fun isServicesOK(): Boolean {
        Log.d("gmapscek", "isServicesOK: checking google services version")

        val available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)

        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map requests
            Log.d("gmapscek", "isServicesOK: Google Play Services is working")
            return true
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d("gmapscek", "isServicesOK: an error occured but we can fix it")
            val dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, available, 9001)
            dialog.show()
        } else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show()
        }
        return false
    }

    private fun getLocationPermission() {
        Log.d("MapActivity", "getLocationPermission: getting location permissions")
        val permissions = arrayOf<String>(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION)

        if (ContextCompat.checkSelfPermission(this.applicationContext,
                        FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.applicationContext,
                            COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true
                initMap()
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE)
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        Log.d("MapActivity", "onRequestPermissionsResult: called.")
        mLocationPermissionsGranted = false

        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.size > 0) {
                    for (i in grantResults.indices) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false
                            Log.d("MapActivity", "onRequestPermissionsResult: permission failed")
                            return
                        }
                    }
                    Log.d("MapActivity", "onRequestPermissionsResult: permission granted")
                    mLocationPermissionsGranted = true
                    //initialize our map
                    initMap()
                }
            }
        }
    }
}
