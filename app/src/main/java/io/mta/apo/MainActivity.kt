package io.mta.apo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.otaliastudios.cameraview.CameraView

class MainActivity : AppCompatActivity() {

    private var btnRecentSearches: ImageButton? = null
    private var btnSearchForm: ImageButton? = null
    private var btnCapture: ImageButton? = null
    private var camera: CameraHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // NOTE: why do i have to explicitly specify the type here?
        btnRecentSearches = findViewById<ImageButton>(R.id.btnRecentSearches)
        // NOTE: because btnRecent is mutable we have to cast as it as ImageButton here
        (btnRecentSearches as ImageButton).setOnClickListener{
            v -> onClick(v)
        }

        btnSearchForm = findViewById<ImageButton>(R.id.btnSearchForm)
        (btnSearchForm as ImageButton).setOnClickListener{
            v -> onClick(v)
        }

        btnCapture = findViewById<ImageButton>(R.id.btnCapture)
        (btnCapture as ImageButton).setOnClickListener{
            v -> onClick(v)
        }

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

    fun onClick(view: View) {
        when(view.id) {
            R.id.btnRecentSearches -> btnRecentSearchesClick()
            R.id.btnSearchForm -> btnSearchFormClick()
            R.id.btnCapture -> btnCaptureClick()
        }
    }

    /**
     * Goto to recent searches page
     */
    fun btnRecentSearchesClick() {
        val intent: Intent = Intent(this, RecentSearchesActivity::class.java)
        startActivity(intent)
    }

    /**
     * Goto to recent searches page
     */
    fun btnSearchFormClick() {
        val intent: Intent = Intent(this, SearchFormActivity::class.java)
        startActivity(intent)
    }

    /**
     * Take a picture
     */
    fun btnCaptureClick() {
        camera?.capture()
    }

}
