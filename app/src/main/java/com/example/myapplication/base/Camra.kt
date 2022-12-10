package com.example.myapplication.base

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.os.FileUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.myapplication.R
import java.io.File
import java.lang.Exception

@Composable
fun Camera(navController: NavController) {
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current
    val cameraProvider = ProcessCameraProvider.getInstance(context)
    BackHandler {
        navController.popBackStack()

    }
    val previewView = remember {
        PreviewView(context).apply {
            id = R.id.pre_view_view
        }
    }
    val imageCapture =
        remember {
            ImageCapture.Builder().build()
        }

//        ImageCapture.Builder().setTargetRotation(previewView.display.rotation).build()
    AndroidView(factory = {
        previewView
    }, modifier = Modifier.fillMaxSize()) {
        cameraProvider.addListener({
            val provider = cameraProvider.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }


            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                provider.unbindAll()
                provider.bindToLifecycle(lifecycle, cameraSelector, preview,imageCapture)

            } catch (e: Exception) {
                Log.e("Exception", e.toString())
            }
        }, ContextCompat.getMainExecutor(context))
    }
    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Bottom) {
        Button(onClick = {
            createDirectoryIfNotExist(context)

            val file = createFile(context)

            imageCapture.takePicture(
                ImageCapture.OutputFileOptions.Builder(file).build(),
                ContextCompat.getMainExecutor(context),
                object :ImageCapture.OnImageSavedCallback{
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        val saveUri = Uri.fromFile(file)
                        Toast.makeText(context,saveUri.path,Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(exception: ImageCaptureException) {
                        TODO("Not yet implemented")
                    }

                }
            )
        }, content = {
            Icon(imageVector = Icons.Default.AccountBox, contentDescription = "")
        })
    }
}

const val IMAGE_PREFIX = "Image_"
const val JPG_SUFFIX = ".jpg"
const val FOLDER_NAME = "Photo"
fun createDirectoryIfNotExist(context: Context) {
    val folder = File(
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absolutePath + File.separator + FOLDER_NAME
    )
    if (!folder.exists()) {
        folder.mkdirs()
    }
}

fun createFile(context: Context) = File(
    context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absolutePath +
            File.separator + FOLDER_NAME + File.separator + IMAGE_PREFIX + System.currentTimeMillis() + JPG_SUFFIX
)
