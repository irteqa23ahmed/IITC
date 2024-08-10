package com.example.iitc

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telecom.TelecomManager
import android.telephony.TelephonyManager
import android.widget.Toast

class CallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val extras = intent.extras
            if (extras != null) {
                val state = extras.getString(TelephonyManager.EXTRA_STATE)
                if (state != null && state == TelephonyManager.EXTRA_STATE_RINGING) {
                    Toast.makeText(context, "Incoming call detected", Toast.LENGTH_SHORT).show()
                    val telecomManager =
                        context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager
                    try {
                        telecomManager.acceptRingingCall()
//                        telecomManager.endCall()
                        Toast.makeText(context, "Call ended", Toast.LENGTH_SHORT).show()
                    } catch (e: SecurityException) {
                        e.printStackTrace()
                        Toast.makeText(context, "Failed to end call", Toast.LENGTH_SHORT).show()
                    }
                    // context.getSystemService(TelephonyManager::class.java).
                    // This is where you'd typically answer the call and play the response
                    // For demonstration purposes, we're only going to play the media file
//                    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.response)
//                    mediaPlayer.start()
                }
            }
        }
    }
}