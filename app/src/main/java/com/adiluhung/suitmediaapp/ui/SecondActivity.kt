package com.adiluhung.suitmediaapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.adiluhung.suitmediaapp.R
import com.adiluhung.suitmediaapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    val getUser = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result != null) {
            val tvSelectedUserName = binding.tvSelectedUserName
            val data = result.data
            val name = data?.getStringExtra(ThirdActivity.EXTRA_NAME)
            tvSelectedUserName.text = name
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        val name = intent.getStringExtra(EXTRA_NAME)

        val tvName = binding.tvName
        val tvSelectedUserName = binding.tvSelectedUserName
        val btnChoose = binding.btnChoose
        val toolbar = binding.toolbar

        toolbar.title = getString(R.string.second_screen)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        tvName.text = name
        btnChoose.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            getUser.launch(intent)
        }

    }

    companion object {
        const val EXTRA_NAME = "extra_name"
    }
}