package com.example.mvvmsampleappication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mvvmsampleappication.R
import com.example.mvvmsampleappication.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private lateinit var adapter: LogRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.stepCountList.observe(this, Observer { list ->
            list?.let {

            }
        })

        InputDialogFragment().show(supportFragmentManager, INPUT_TAG)

        // RecyclerViewの初期化
        adapter = LogRecyclerAdapter(viewModel.stepCountList.value!!) // MainViewModelクラスでLiveDataのvalueにinitブロックで空リストを入れているため、nullがあり得ないので!!を使用している。
        log_list.adapter = adapter

        // 区切り線を追加
        val decor = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        log_list.addItemDecoration(decor)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.add_record -> {
                InputDialogFragment().show(supportFragmentManager, INPUT_TAG)
                true
            }
            else -> false
        }
    }

    companion object {
        const val INPUT_TAG = "input_dialog"
    }
}