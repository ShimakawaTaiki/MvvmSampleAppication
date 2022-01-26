package com.example.mvvmsampleappication.view.activity.logitem

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mvvmsampleappication.R
import com.example.mvvmsampleappication.databinding.ActivityLogItemBinding
import com.example.mvvmsampleappication.viewmodel.LogItemViewModel
import kotlinx.android.synthetic.main.activity_log_item.*

class LogItemActivity : AppCompatActivity() {
    lateinit var viewModel: LogItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LogItemViewModel::class.java)

        setContentView(R.layout.activity_log_item)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.log_item_container, LogInputFragment.newInstance())
                .commitNow()
        }

        viewModel.stepCountLog.observe(this, { stepCountLog ->
            val dataIntent = Intent()
            dataIntent.putExtra(EXTRA_KEY_DATA, stepCountLog)
            setResult(RESULT_OK, dataIntent)
            finish()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_KEY_DATA = "data"
    }
}