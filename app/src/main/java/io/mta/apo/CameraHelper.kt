package io.mta.apo

import android.content.Context
import android.content.pm.PackageManager
import android.view.Surface
import android.Manifest
import android.hardware.camera2.*

/**
 * Created by Michael T. Andemeskel on 11/12/17.
 */
class CameraHelper (val context: Context) {
    var camera_manager: CameraManager? = null
    var main_camera_id: String = ""

    init {

        if(this.checkForPermission() == false)
            throw Exception("Don't have camera permissions")

        if(this.isCameraAvailable() == false)
            throw Exception("No available camera")

        camera_manager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

        main_camera_id = this.getBackCamera()

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
     * Get the back facing camera we will use to take pictures with
     */
    fun getBackCamera(): String {
        // go through list of cameras on the phone
        val cameras: Array<String> = camera_manager!!.cameraIdList
        for(camera in cameras){

            // get the details of the camera
            val stats: CameraCharacteristics = (camera_manager as CameraManager).getCameraCharacteristics(camera)

            // find a the camera on the back of the phone
            if(stats[CameraCharacteristics.LENS_FACING] == CameraMetadata.LENS_FACING_BACK)
                return camera

        }

        return ""
    }

    /**
     * Capture an image.
     */
    fun capture(surface: Surface) {

    }
}