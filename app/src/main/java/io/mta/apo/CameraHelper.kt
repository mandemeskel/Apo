package io.mta.apo

import android.content.Context
import android.content.pm.PackageManager
import android.Manifest
import android.util.Log
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.CameraView

/**
 * Created by Michael T. Andemeskel on 11/12/17.
 */
class CameraHelper (val context: Context, val camera: CameraView) {
    var camera_callback: CameraListener? = null

    init {

        if(this.checkForPermission() == false)
            throw Exception("Don't have camera permissions")

        if(this.isCameraAvailable() == false)
            throw Exception("No available camera")

        camera_callback = CaptureListener()
        camera.addCameraListener(camera_callback)

        cameraStart()
    }

    /**
     * Checks if the camera is available.
     */
    fun isCameraAvailable(): Boolean {
        return context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)
    }

    /**
     * Check if we have been granted permissions to access the camera.
     */
    fun checkForPermission(): Boolean {
        return context.checkCallingOrSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Camera capture listener.
     */
    class CaptureListener: CameraListener() {
        override fun onPictureTaken(jpeg: ByteArray?) {
            super.onPictureTaken(jpeg)
            // TODO: do something with the picture
            Log.i("CaptureListener:", "picture taken, byte array size: " + jpeg?.size.toString())
        }
    }

    /**
     * Get access to the camera resources.
     */
    fun cameraStart() {
        camera.start()
    }

    /**
     * Stop listening to camera resources.
     */
    fun cameraStop() {
        camera.stop()
    }

    /**
     * Release camera resources.
     */
    fun cameraDestroy() {
        camera.destroy()
    }
}