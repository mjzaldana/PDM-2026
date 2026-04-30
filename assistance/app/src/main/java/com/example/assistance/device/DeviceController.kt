package com.example.assistance.device

import android.content.Context
import android.content.Intent
import android.hardware.camera2.CameraManager
import android.media.AudioManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.Settings
import androidx.annotation.RequiresApi

class DeviceController(private val context: Context){

    // Que vamos a hacer

    fun linterna(encender: Boolean){
        val cameraManager = context
            .getSystemService(Context.CAMERA_SERVICE) as CameraManager

        val cameraId = cameraManager.cameraIdList[0]

        cameraManager.setTorchMode(cameraId, encender)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun vibrar(){
        val vibrator = context
            .getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        vibrator.vibrate(
            VibrationEffect
                .createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE)
        )
    }

    fun brillo(valor: Int){
        if(Settings.System.canWrite(context)){
            Settings.System.putInt(
                context.contentResolver,
                Settings.System.SCREEN_BRIGHTNESS,
                valor
            )
        } else {
            val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}