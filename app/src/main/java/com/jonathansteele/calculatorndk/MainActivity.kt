package com.jonathansteele.calculatorndk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jonathansteele.calculatorndk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val number1 = binding.number1.text.toString().toInt()
        val number2 = binding.number2.text.toString().toInt()

        binding.btnAdd.setOnClickListener {
            add(number1, number2)
        }

        binding.btnSub.setOnClickListener {
            subtract(number1, number2)
        }

        binding.btnMul.setOnClickListener {
            multiply(number1, number2)
        }

        binding.btnDiv.setOnClickListener {
            divide(number1, number2)
        }
    }

    /**
     * A native method that is implemented by the 'calculatorndk' native library,
     * which is packaged with this application.
     */
    private external fun add(x: Int, y: Int): Int
    private external fun subtract(x: Int, y: Int): Int
    private external fun multiply(x: Int, y: Int): Int
    private external fun divide(x: Int, y: Int): Int

    companion object {
        // Used to load the 'calculatorndk' library on application startup.
        init {
            System.loadLibrary("calculatorndk")
        }
    }
}