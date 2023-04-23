package com.example.loginandsignup

import android.annotation.SuppressLint
import android.app.Activity
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import com.example.loginandsignup.R

@SuppressLint("ObsoleteSdkInt")
class Compass : Activity(), SensorEventListener {

    private lateinit var image: ImageView
    private var currentDegree = 0f
    private lateinit var mSensorManager: SensorManager
    private lateinit var tvHeading: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compass)

        image = findViewById<View>(R.id.imageViewCompass) as ImageView
        tvHeading = findViewById<View>(R.id.tvHeading) as TextView
        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()

        mSensorManager.registerListener(
            this, mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    override fun onPause() {
        super.onPause()

        mSensorManager.unregisterListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent) {
        val rotationMatrix = FloatArray(9)
        SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)

        val orientation = FloatArray(3)
        SensorManager.getOrientation(rotationMatrix, orientation)

        val degree = Math.toDegrees(orientation[0].toDouble()).toFloat()
        tvHeading.text = "Heading: " + degree + " degrees"

        val targetLocation = Location("")
        targetLocation.latitude = 40.730
        targetLocation.longitude = -73.935

        //val mapsActivity = MapsActivity()

        //mapsActivity.getCurrentLocationUser()

       // var current = mapsActivity.currentLocation
        val current = Location("")
        current.latitude = 0.0
        current.longitude = 0.0

        val destination = current.bearingTo(targetLocation)
        println(destination)


        val ra = RotateAnimation(
            /* fromDegrees = */ currentDegree,
            /* toDegrees = */   destination,
            /* pivotXType = */ Animation.RELATIVE_TO_SELF, /* pivotXValue = */ 0.5f,
            /* pivotYType = */ Animation.RELATIVE_TO_SELF,
            /* pivotYValue = */ 0.5f
        )

        ra.duration = 210
        ra.fillAfter = true

        image.startAnimation(ra)
        currentDegree = -degree
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
    }

}