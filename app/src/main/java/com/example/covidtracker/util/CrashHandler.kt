package com.example.covidtracker.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.covidtracker.ui.MainActivity
import kotlin.system.exitProcess

class CrashHandler(private val context: Context) : Thread.UncaughtExceptionHandler {
    private val TAG = "CrashHandler"

    override fun uncaughtException(t: Thread?, e: Throwable?) {
        Log.e(TAG, "Intercepted uncaught exception", e)
        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra(EXTRA_CRASH, true)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NEW_TASK
            )
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent)
        exitProcess(2)
    }

    companion object {
        const val EXTRA_CRASH = "com.example.covidtracker.CrashHandler.EXTRA_CRASH"
    }
}