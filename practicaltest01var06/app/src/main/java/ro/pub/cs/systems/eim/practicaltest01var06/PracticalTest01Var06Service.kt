package ro.pub.cs.systems.eim.practicaltest01var06

import android.app.Service
import android.content.Intent
import android.os.IBinder

class PracticalTest01Var06Service : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var gain = intent?.getIntExtra("gain", -1)

        Thread.sleep(2000)

        val intentBroadcast = Intent("Victory")
        intentBroadcast.putExtra("date", java.util.Date().toString())
        intentBroadcast.putExtra("time", System.currentTimeMillis())
        intentBroadcast.putExtra("score", gain)
        sendBroadcast(intentBroadcast)

        return START_STICKY
    }


}