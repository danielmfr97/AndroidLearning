package com.daniel.ramos.learningnotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

class MainActivity : AppCompatActivity() {
    private val channelId = "com.daniel.ramos.learningnotifications"
    private var notificationManager: NotificationManager? = null
    private val KEY_REPLY = "key_reply"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        criarCanalNotificacao(channelId, "Demo", "Essa é uma demonstração")
        button.setOnClickListener {
            mostrarNotificacao()
        }
    }

    private fun mostrarNotificacao() {
        val notificationId = 45
        val tapResult = Intent(this, SecondActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
                this, 0, tapResult, PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Reply action
        val remoteInput = RemoteInput.Builder(KEY_REPLY).run {
            setLabel("Insira seu nome")
                    .build()
        }
        val replyAction = NotificationCompat.Action.Builder(
                0, "REPLY", pendingIntent
        ).addRemoteInput(remoteInput)
                .build()

        // Ação botao 1
        val tapResult1 = Intent(this, DetailsActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
        }
        val pendingIntent1: PendingIntent = PendingIntent.getActivity(
                this, 0, tapResult1, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val action1: NotificationCompat.Action = NotificationCompat.Action.Builder(0, "Details", pendingIntent1).build()

        // Ação botao 2
        val tapResult2 = Intent(this, SettingsActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
        }
        val pendingIntent2: PendingIntent = PendingIntent.getActivity(
                this, 0, tapResult2, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val action2: NotificationCompat.Action = NotificationCompat.Action.Builder(0, "Settings", pendingIntent2).build()

        val notification = NotificationCompat.Builder(this@MainActivity, channelId)
                .setContentTitle("Demo titulo")
                .setContentText("Essa é uma demonstração")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(action1)
                .addAction(action2)
                .addAction(replyAction)
                .build()
        notificationManager?.notify(notificationId, notification)
    }

    private fun criarCanalNotificacao(id: String, name: String, channelDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importanceLevel = NotificationManager.IMPORTANCE_HIGH
            val channelInstance = NotificationChannel(id, name, importanceLevel).apply {
                description = channelDescription
            }
            notificationManager?.createNotificationChannel(channelInstance)
        }
    }
}