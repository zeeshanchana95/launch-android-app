package com.example.test

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi

class MyService : Service() {

   @RequiresApi(Build.VERSION_CODES.O)
   override fun onCreate() {
        super.onCreate()

        val notification = Notification.Builder(this, "my_channel_id")
            .setContentTitle("My App is running in the background")
            .setContentText("Shake your device to launch the app")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        startForeground(1, notification)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
