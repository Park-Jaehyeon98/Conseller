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
import com.example.project.api.LoginService
import com.example.project.api.firebaseToken
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyFirebaseMessagingService : FirebaseMessagingService() {
        private val TAG = "FirebaseTest"

        // Retrofit 설정
        private val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://j9b207.p.ssafy.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        // Service 인스턴스화
        private val apiService: LoginService = retrofit.create(LoginService::class.java)

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
                sendRegistrationToServer(token)
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
                Log.d("aaaaaaaaaaaaaaaaaa", "${title}")
                Log.d("bbbbbbbbbbbbbbbbb", "${body}")

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

        fun getFirebaseToken(){
                FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                        if (!task.isSuccessful) {
                                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                                return@OnCompleteListener
                        }

                        // Get new FCM registration token
                        val firebaseToken = task.result

                        onNewToken(firebaseToken!!)

                        Log.d("@@@@@@@@@@@@@@@@@@@@@@@", firebaseToken)
                })
        }

        // 받은 토큰을 서버로 전송
        private fun sendRegistrationToServer(token: String) {

                val call = apiService.sendToken(firebaseToken(token))
                call.enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                if (response.isSuccessful) {
                                        Log.d(TAG, "Token sent successfully!")
                                } else {
                                        Log.e(TAG, "Failed to send token: ${response.errorBody()?.string()}")
                                }
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                                Log.e(TAG, "Failed to send token", t)
                        }
                })
        }
}