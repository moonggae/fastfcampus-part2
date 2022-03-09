package fastcampus.aop.part2.chapter01

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fastcampus.aop.part2.chapter01.databinding.ActivityResultBinding
import kotlin.math.pow

class ResultActivity : AppCompatActivity() {

    private val TAG = "로그"
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val height = intent.getIntExtra("height", 0)
        val weight = intent.getIntExtra("weight", 0)

        Log.d(TAG, "height : $height , weight : $weight")

        val bmi = weight / (height / 100.0).pow(2.0)
        val resultText = when{
            bmi >= 35.0 -> "고도 비만"
            bmi >= 30.0 -> "중정도 비만"
            bmi >= 25.0 -> "경도 비만"
            bmi >= 23.0 -> "과체중"
            bmi >= 18.5 -> "정상체중"
            else -> "저체중"
        }

        binding.resultValueTextView.text = bmi.toString()
        binding.resultStringTextView.text = resultText

    }
}