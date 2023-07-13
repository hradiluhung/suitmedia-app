package com.adiluhung.suitmediaapp.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adiluhung.suitmediaapp.R
import com.adiluhung.suitmediaapp.adapter.LoadingStateAdapter
import com.adiluhung.suitmediaapp.adapter.UserListAdapter
import com.adiluhung.suitmediaapp.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private val viewModel: ListUserViewModel by viewModels {
        ViewModelFactory(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupView() {

        val rvUser = binding.rvUser
        val toolbar = binding.toolbar
        val tvNoUserFound = binding.tvNoUserFound

        toolbar.title = getString(R.string.third_screen)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        val adapter = UserListAdapter{ user ->
            val intent = intent
            intent.putExtra(EXTRA_NAME, "${user.firstName} ${user.lastName}")
            setResult(RESULT_OK, intent)
            finish()
        }

        adapter.addLoadStateListener { loadState ->
            if(loadState.refresh is androidx.paging.LoadState.Loading) {
                tvNoUserFound.visibility = android.view.View.GONE
            } else {
                if (loadState.refresh is androidx.paging.LoadState.Error) {
                    tvNoUserFound.visibility = android.view.View.VISIBLE
                }
            }
        }

        rvUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter { adapter.retry() }
        )

        rvUser.layoutManager = LinearLayoutManager(this)
        viewModel.users.observe(this) {
            adapter.submitData(lifecycle, it)
            if(adapter.itemCount<1) tvNoUserFound.visibility = android.view.View.VISIBLE
            else tvNoUserFound.visibility = android.view.View.GONE
        }

        val swipeRefresh = binding.swipeRefresh
        swipeRefresh.setOnRefreshListener {
            adapter.refresh()
            swipeRefresh.isRefreshing = false
        }

    }

    companion object {
        const val EXTRA_NAME = "extra_name"
    }
}