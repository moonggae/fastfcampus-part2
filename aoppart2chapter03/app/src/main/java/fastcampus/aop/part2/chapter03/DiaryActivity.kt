package fastcampus.aop.part2.chapter03

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fastcampus.aop.part2.chapter03.databinding.ActivityDiaryBinding
import android.widget.EditText
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity : AppCompatActivity() {

    private val TAG = "로그"

    private val binding : ActivityDiaryBinding by lazy {
        ActivityDiaryBinding.inflate(layoutInflater)
    }

    private val diaryEditText : EditText by lazy {
        binding.diaryEditText
    }

    private val handler = Handler(Looper.getMainLooper()) // 메인 쓰레드에서 실행됨

    private val SHARED_PREFERENCE_NAME = "diary"
    private val DIARY_PREFERENCE_KEY = "detail"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val detailPreferences = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

        diaryEditText.setText(detailPreferences.getString(DIARY_PREFERENCE_KEY, ""))

        val runnable = Runnable {
            getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).edit {
                putString(DIARY_PREFERENCE_KEY, diaryEditText.text.toString())
            }
            Log.d(TAG, "DiaryActivity - saved! : ${diaryEditText.text}")
        }

        diaryEditText.addTextChangedListener {
            Log.d(TAG, "DiaryActivity - change : ${diaryEditText.text}")
            
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 500)
        }
    }
}