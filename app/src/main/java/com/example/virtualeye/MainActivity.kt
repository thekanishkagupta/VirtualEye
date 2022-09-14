package com.example.virtualeye

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.virtualeye.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions


class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       //setSupportActionBar(binding.appBarMain.toolbar)

//        binding.appBarMain.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }


        val navController = Navigation.findNavController(this, R.id.navHost)

        binding.navView.setupWithNavController(navController)

//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        appBarConfiguration = AppBarConfiguration(
//           setOf(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
//            ), binding.drawerLayout
//        )
//       setupActionBarWithNavController(navController, appBarConfiguration)


        // Permission
        openCamera()

//        startCamera()


        // Live detection and tracking
//        val options = ObjectDetectorOptions.Builder()
//            .setDetectorMode(ObjectDetectorOptions.STREAM_MODE)
//            .enableClassification()  // Optional
//            .build()
//
//        val objectDetector = ObjectDetection.getClient(options)


    }

    @AfterPermissionGranted(123)
    private fun openCamera() {
        val perms = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (EasyPermissions.hasPermissions(this, *perms)) {
            Toast.makeText(this, "Opening camera", Toast.LENGTH_SHORT).show()
        } else {
            EasyPermissions.requestPermissions(
                this, "We need permissions because this and that",
                123, *perms
            )
        }
    }



//        private fun startCamera() {
//            val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
//
//            cameraProviderFuture.addListener({
//                // Used to bind the lifecycle of cameras to the lifecycle owner
//                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
//
//                // Preview
//                val preview = Preview.Builder()
//                    .build()
//                    .also {
//                        it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
//                    }
//
//                // Select back camera as a default
//                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//
//                try {
//                    // Unbind use cases before rebinding
//                    cameraProvider.unbindAll()
//
//                    // Bind use cases to camera
//                    cameraProvider.bindToLifecycle(
//                        this, cameraSelector, preview)
//
//                } catch(exc: Exception) {
//                    Log.e("KanishkaApp", "Use case binding failed", exc)
//                }
//
//            }, ContextCompat.getMainExecutor(this))
//        }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Toast.makeText(this, "Granted", Toast.LENGTH_LONG).show()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Toast.makeText(this, "Denied", Toast.LENGTH_LONG).show()
    }
}

//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//    // Inflate the menu; this adds items to the action bar if it is present.
//    //menuInflater.inflate(R.menu.main, menu)
//    return true
//}
//
//override fun onSupportNavigateUp(): Boolean {
//    //val navController = findNavController(R.id.nav_host_fragment_content_main)
//    return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//} }
