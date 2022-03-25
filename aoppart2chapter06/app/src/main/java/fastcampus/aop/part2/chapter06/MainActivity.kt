package fastcampus.aop.part2.chapter06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.SeekBar
import fastcampus.aop.part2.chapter06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        bindViews()
    }

    private fun bindViews() {
        binding.seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                    binding.remainMinutesTextView.text = "%02d".format(progress)
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {

                }
            }
        )
    }

    private fun createCountDownTimer(initialMillis: Long) =
        object : CountDownTimer(initialMillis, 1000L) {
            override fun onTick(millis: Long) {
                updateRemainingTimes(millis)
            }

            override fun onFinish() {

            }

        }

    private fun updateRemainingTimes(remainMillis : Long) {
        val remainSeconds = remainMillis / 1000

        binding.remainMinutesTextView.text = "%02d".format(remainSeconds / 60)
        binding.raminSecondsTextView.text = "%02d".format(remainSeconds % 60)
    }

    private fun updateSeekBar(remainMillis : Long){

    }
}