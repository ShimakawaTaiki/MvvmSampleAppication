package com.example.mvvmsampleappication.view.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mvvmsampleappication.R
import com.example.mvvmsampleappication.data.StepCountLog
import com.example.mvvmsampleappication.databinding.ActivityMainBinding
import com.example.mvvmsampleappication.view.LogRecyclerAdapter
import com.example.mvvmsampleappication.view.activity.logitem.LogItemActivity
import com.example.mvvmsampleappication.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private lateinit var adapter: LogRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // RecyclerViewの初期化
        adapter = LogRecyclerAdapter(viewModel.stepCountList.value!!) // MainViewModelクラスでLiveDataのvalueにinitブロックで空リストを入れているため、nullがあり得ないので!!を使用している。
        log_list.adapter = adapter

        // 区切り線を追加
        val decor = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        log_list.addItemDecoration(decor)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            REQUEST_CODE_LOGITEM -> {
                onNewStepCountLog(resultCode, data)
                return
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun onNewStepCountLog(resultCode: Int, data: Intent?) {
        when(resultCode) {
            RESULT_OK -> {
                val log = data?.getSerializableExtra(LogItemActivity.EXTRA_KEY_DATA) as StepCountLog?
                log?.let {
                    viewModel.addStepCount(log)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.add_record -> {
                val intent = Intent(this, LogItemActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE_LOGITEM)
                true
            }
            else -> false
        }
    }

    companion object {
        const val REQUEST_CODE_LOGITEM = 100
    }
}