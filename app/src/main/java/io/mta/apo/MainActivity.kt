package io.mta.apo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.otaliastudios.cameraview.CameraView

class MainActivity : AppCompatActivity() {

    private var camera: CameraHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cameraView = findViewById<CameraView>(R.id.camera)
        camera = CameraHelper(this, cameraView)

    }

    override fun onResume() {
        super.onResume()
        camera?.cameraStart()
    }

    override fun onPause() {
        super.onPause()
        camera?.cameraStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        camera?.cameraDestroy()
    }

    /**
     * Callback used when user responds to a permission request.
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        camera?.permissionHandler(requestCode, permissions, grantResults);
    }
}
