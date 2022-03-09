package fastcampus.aop.part2.chapter02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import fastcampus.aop.part2.chapter02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "로그"

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val numberTextViewList : List<TextView> by lazy {
        listOf<TextView>(
            binding.resultNumberTextView1,
            binding.resultNumberTextView2,
            binding.resultNumberTextView3,
            binding.resultNumberTextView4,
            binding.resultNumberTextView5,
            binding.resultNumberTextView6
        )
    }

    private var didRun = false

    private val pickNumberSet = hashSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.numberPicker.minValue = 1
        binding.numberPicker.maxValue = 45

        initRunButton()
        initAddButton()
        initClearButton()
    }



    private fun initRunButton(){
        binding.runButton.setOnClickListener {
            val list = getRandomNumber()

            didRun = true

            list.forEachIndexed { index, number ->
                val textView = numberTextViewList[index]

                textView.text = number.toString()
                textView.isVisible = true
                setNumberBackground(number, textView)
            }

            Log.d(TAG, "MainActivity - initRunButton: ${list.toString()}")
        }
    }

    private fun initAddButton() {
        binding.addButton.setOnClickListener {
            if(didRun){
                Toast.makeText(this, "초기화 후에 시도해주세요. ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(pickNumberSet.size >= 6){
                Toast.makeText(this, "번호는 6개 까지만 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(pickNumberSet.contains(binding.numberPicker.value)){
                Toast.makeText(this, "이미 선택한 번호입니다. ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val textView = numberTextViewList[pickNumberSet.size]
            textView.isVisible = true
            textView.text = binding.numberPicker.value.toString()

            setNumberBackground(binding.numberPicker.value, textView)

            pickNumberSet.add(binding.numberPicker.value)

        }
    }

    private fun setNumberBackground(number:Int, textView: TextView){
        when(number){
            in 1..10 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_yellow)
            in 1..20 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_blue)
            in 21..31 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_red)
            in 31..40 -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_gray)
            else -> textView.background = ContextCompat.getDrawable(this, R.drawable.circle_green)
        }
    }

    private fun initClearButton() {
        binding.clearButton.setOnClickListener {
            pickNumberSet.clear()
            numberTextViewList.forEach {
                it.isVisible = false
            }
            didRun = false
        }
    }

    private fun getRandomNumber(): List<Int> {
        val numberList = mutableListOf<Int>().apply {
            for (i in 1..45){
                if(pickNumberSet.contains(i)){
                    continue
                }

                this.add(i)
            }
        }
        numberList.shuffle()

        val newList = pickNumberSet.toList() + numberList.subList(0, 6 - pickNumberSet.size)
        return newList.sorted()
    }
}