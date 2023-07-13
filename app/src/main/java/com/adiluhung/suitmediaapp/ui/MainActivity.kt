package com.adiluhung.suitmediaapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.adiluhung.suitmediaapp.R
import com.adiluhung.suitmediaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        val etName = binding.etName
        val etPalindrome = binding.etPalindrome
        val btnCheck = binding.btnCheck
        val btnNext = binding.btnNext

        btnCheck.setOnClickListener {
            val text = etPalindrome.text.toString()
            val isPalindrome = isPalindrome(text)
            val message = if (isPalindrome) "isPalindrome" else "not palindrome"
            showDialog(message)
        }

        btnNext.setOnClickListener{
            if(etName.text.toString().isEmpty()){
                Toast.makeText(this, "Please input your name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra(SecondActivity.EXTRA_NAME, etName.text.toString())
            startActivity(intent)
        }
    }

    private fun showDialog(message: String) {
        val dialog = AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

        dialog.show()
    }

    private fun isPalindrome(text: String): Boolean {
        val textLength = text.length
        val halfLength = textLength / 2
        for (i in 0 until halfLength) {
            if (text[i] != text[textLength - i - 1]) {
                return false
            }
        }
        return true
    }
}