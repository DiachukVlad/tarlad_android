package com.example.tarlad.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage?) {
        if (message == null) return

        println(message.data)
        super.onMessageReceived(message)
    }

}
