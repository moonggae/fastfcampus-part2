package fastcampus.aop.part2.chapter05

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fastcampus.aop.part2.chapter05.databinding.ActivityPhotoframeBinding
import java.util.*
import kotlin.concurrent.timer

class PhotoFrameActivity : AppCompatActivity() {

    val binding: ActivityPhotoframeBinding by lazy {
        ActivityPhotoframeBinding.inflate(layoutInflater)
    }

    private val TAG = "로그"

    private var currentPosition = 0

    private val photoList = mutableListOf<Uri>()

    private var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.d(TAG, "PhotoFrameActivity onCreate() - called : ")

        getPhotoUriFromIntent()
    }

    private fun getPhotoUriFromIntent() {
        val size = intent.getIntExtra("photoListSize", 0)
        for (i in 0..size) {
            intent.getStringExtra("photo$i")?.let {
                photoList.add(Uri.parse(it))
            }
        }
    }

    private fun startTimer() {
        timer = timer(period = 5 * 1000) {
            runOnUiThread {
                val current = currentPosition
                val next = if (photoList.size <= currentPosition + 1) 0 else currentPosition + 1

                binding.backgroundPhotoImageView.setImageURI(photoList[current])

                binding.photoImageView.alpha = 0f
                binding.photoImageView.setImageURI(photoList[next])
                binding.photoImageView.animate()
                    .alpha(1.0f)
                    .setDuration(1000)
                    .start()

                currentPosition = next
            }
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "PhotoFrameActivity onStop() - called : ")

        timer?.cancel()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "PhotoFrameActivity onStart() - called : ")

        startTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "PhotoFrameActivity onDestroy() - called : ")

        timer?.cancel()
    }
}