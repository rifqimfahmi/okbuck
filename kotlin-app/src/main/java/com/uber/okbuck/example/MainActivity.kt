package com.uber.okbuck.example

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.uber.okbuck.example.sqldelightmodel.GithubRepo
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var analytics: Analytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            analytics.sendAnalyticsEvent("FAB Clicked")
        }

        val repo = GithubRepo.create(100, "OkBuck", "auto buck")
        Toast.makeText(this, repo.name + ": " + repo.description, Toast.LENGTH_SHORT).show()

        testCoroutine()
    }

    private fun testCoroutine() {
        val btn = findViewById<Button>(R.id.coroutineBtn)
        btn.setOnClickListener {
//            GlobalScope.launch(Dispatchers.Default) {
//                delay(1000)
//                Log.d("TEST_COROUTINE", "YEAY")
//            }
//            GlobalScope.launch(Dispatchers.IO) {
//                delay(1000)
//                Log.d("TEST_COROUTINE", "YEAY")
//            }
            GlobalScope.launch(Dispatchers.Main) {
                Log.d("TEST_COROUTINE", "YEAY")
            }
        }
    }

    companion object {
      internal const val TEST_INTERNAL = ""
    }
}
