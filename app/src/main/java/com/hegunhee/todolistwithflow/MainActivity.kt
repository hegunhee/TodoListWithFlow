package com.hegunhee.todolistwithflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.hegunhee.todolistwithflow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            viewmodel = viewModel
        }
        initObserve()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.deleteAll ->{
                viewModel.deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun initObserve() = with(viewModel){
        memoListLiveData.observe(this@MainActivity){
            Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}