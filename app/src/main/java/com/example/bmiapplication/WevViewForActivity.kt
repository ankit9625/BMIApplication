package com.example.bmiapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bmiapplication.databinding.ActivityWevViewForBinding

class WevViewForActivity : AppCompatActivity() {
    private lateinit var bindingwebview: ActivityWevViewForBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingwebview= ActivityWevViewForBinding.inflate(layoutInflater)
        setContentView(bindingwebview.root)
        
         bindingwebview.webView.loadUrl("https://www.calculator.net/bmi-calculator.html")

    }


}