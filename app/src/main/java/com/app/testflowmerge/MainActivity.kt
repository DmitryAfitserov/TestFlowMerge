package com.app.testflowmerge

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.testflowmerge.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener {
            start()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun start() {

        if (binding.editText.text.isEmpty()) return

        val count = binding.editText.text.toString().toInt()

        binding.textView.text = "${binding.textView.text} \n Start task"

        lifecycleScope.launch {
            viewModel.resultFlow(count).collectLatest {
                binding.textView.text = "${binding.textView.text} \n $it"
            }
        }
    }
}