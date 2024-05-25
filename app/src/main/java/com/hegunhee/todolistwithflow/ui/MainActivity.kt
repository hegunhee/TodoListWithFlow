package com.hegunhee.todolistwithflow.ui

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.hegunhee.todolistwithflow.R
import com.hegunhee.todolistwithflow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter : MemoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        adapter = MemoAdapter(viewModel)
        binding.apply {
            viewmodel = viewModel
            recyclerView.adapter = adapter
            lifecycleOwner = this@MainActivity
        }
        observeData()
    }

    private fun observeData() = with(viewModel) {
        lifecycleScope.launchWhenStarted {
            launch {
                navigationActions.collect {
                    when(it) {
                        MainNavigationAction.AddMemo -> {
                            addMemo()
                        }
                    }
                }
            }
            launch {
                memoList.collect {
                    adapter.submitList(it)
                }
            }
        }
    }

    private fun addMemo() {
        val editText = EditText(this@MainActivity)
        AlertDialog.Builder(this)
            .setTitle("메모를 입력해주세요")
            .setView(editText)
            .setPositiveButton(
                "확인",
                DialogInterface.OnClickListener { _, _ ->
                    if (editText.text.toString() == "") {
                        Toast.makeText(this@MainActivity, "메모가 비어있습니다.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        viewModel.insertMemo(editText.text.toString())
                    }
                })
            .setNegativeButton("취소", DialogInterface.OnClickListener { _, _ -> })
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.deleteAll -> {
                viewModel.deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}