package com.example.test

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener

class ShakeDetector(var listener: OnShakeListener) : SensorEventListener {

    private var lastX: Float = 0.0f
    private var lastY: Float = 0.0f
    private var lastZ: Float = 0.0f
    private var lastUpdate: Long = 0

    private val SHAKE_THRESHOLD = 800

    interface OnShakeListener {
        fun onShake()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return

        val curTime = System.currentTimeMillis()

        val diffTime = curTime - lastUpdate
        if (diffTime < 100) return

        lastUpdate = curTime

        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]

        val diffX = x - lastX
        val diffY = y - lastY
        val diffZ = z - lastZ

        lastX = x
        lastY = y
        lastZ = z

        val speed = Math.abs(diffX + diffY + diffZ) / diffTime * 10000

        if (speed > SHAKE_THRESHOLD) {
            listener.onShake()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}
