package fastcampus.aop.part2.chapter03

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit
import fastcampus.aop.part2.chapter03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val numberPicker1 : NumberPicker by lazy {
        binding.numberPicker1.apply {
            minValue = 0
            maxValue = 9
        }
    }

    private val numberPicker2 : NumberPicker by lazy {
        binding.numberPicker2.apply {
            minValue = 0
            maxValue = 9
        }
    }

    private val numberPicker3 : NumberPicker by lazy {
        binding.numberPicker3.apply {
            minValue = 0
            maxValue = 9
        }
    }

    private val openButton : AppCompatButton by lazy {
        binding.openButton
    }

    private val changePassWordButton : AppCompatButton by lazy {
        binding.changePasswordButton
    }

    private val SHARED_PREFERENCE_NAME = "password"
    private val PASSWORD_PREFERENCE_KEY = "password"

    private var changePasswordMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        numberPicker1
        numberPicker2
        numberPicker3

        openButton.setOnClickListener {

            if(changePasswordMode){
                Toast.makeText(this,"비밀번호 변경 중입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val passwordPreferences =  getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
            val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if(passwordPreferences.getString(PASSWORD_PREFERENCE_KEY,"000").equals(passwordFromUser)){
                startActivity(Intent(this, DiaryActivity::class.java))
            }else{
                showErrorAlertDialog()
            }
        }

        changePassWordButton.setOnClickListener {
            val passwordPreferences =  getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
            val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if(changePasswordMode){
                passwordPreferences.edit(true) {
                    putString(PASSWORD_PREFERENCE_KEY, passwordFromUser)
                }
                changePasswordMode = false
                changePassWordButton.setBackgroundColor(Color.BLACK)
            } else {
                // changePasswordMode 가 활성화 :: 비밀번호가 맞는지 체크

                if(passwordPreferences.getString(PASSWORD_PREFERENCE_KEY,"000").equals(passwordFromUser)){
                    changePasswordMode = true
                    Toast.makeText(this, "변경할 패스워드를 입력해주세요", Toast.LENGTH_SHORT).show()
                    changePassWordButton.setBackgroundColor(Color.RED)
                }else{
                    showErrorAlertDialog()
                }
            }
        }

    }

    private fun showErrorAlertDialog(){
        AlertDialog.Builder(this)
            .setTitle("실패!!")
            .setMessage("비밀번호가 잘못되었습니다.")
            .setPositiveButton("확인") { _, _ -> }
            .create()
            .show()
    }

}