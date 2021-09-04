package com.trendster.oyelabs.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.trendster.oyelabs.databinding.ActivityMainBinding
import com.trendster.oyelabs.viewmodel.HomeScreenViewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        viewModel = ViewModelProvider(this).get(HomeScreenViewModel::class.java)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
