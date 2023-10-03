package com.example.project

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.example.project.viewmodels.FireBaseViewModel

class MyFirebaseMessagingService : FirebaseMessagingService() {

        private val TAG = "FirebaseTest"
        private lateinit var fireBaseViewModel: FireBaseViewModel

        // 메세지가 수신되면 호출
        override fun onMessageReceived(remoteMessage: RemoteMessage) {
                if(remoteMessage.data.isNotEmpty()){
                        sendNotification(remoteMessage.notification?.title,
                                remoteMessage.notification?.body!!)
                        Log.d("@@@@@@@@@@@@@@@@@@@@@@@", "222222222222222")
                }
                else{
                        Log.d("@@@@@@@@@@@@@@@@@@@@@@@", "11111111111111111")
                        Log.d("1111111111111111111", "$remoteMessage")
                }
        }

        // 새로운 토큰이 생성 될 때 호출
        override fun onNewToken(token: String) {
                super.onNewToken(token)
                fireBaseViewModel.sendRegistrationToServer(token)
                Log.d("bbbbbbbbbbbbbbbbbbbbbbb", "${token}")
        }

        private fun sendNotification(title: String?, body: String){
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // 액티비티 중복 생성 방지
                val pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                        PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE) // 일회성

                val channelId = "Conseller" // 채널 아이디
                val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION) // 소리
                val notificationBuilder = NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_notification) // 아이콘
                        .setContentTitle(title) // 제목
                        .setContentText(body) // 내용
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)

                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                // 오레오 버전 예외처리
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val channel = NotificationChannel(channelId,
                                "Conseller 알림",
                                NotificationManager.IMPORTANCE_HIGH)
                        notificationManager.createNotificationChannel(channel)
                }

                notificationManager.notify(0 , notificationBuilder.build()) // 알림 생성
        }
}
