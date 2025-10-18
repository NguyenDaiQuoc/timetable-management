package com.example.quanlythoikhoabieu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var scheduleViewModel: ScheduleViewModel

    private val addItemLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                // Lấy dữ liệu từ AddScheduleActivity
                val subject = data.getStringExtra(AddScheduleActivity.EXTRA_SUBJECT) ?: return@let
                val day = data.getStringExtra(AddScheduleActivity.EXTRA_DAY) ?: return@let
                val start = data.getStringExtra(AddScheduleActivity.EXTRA_START_TIME) ?: return@let
                val end = data.getStringExtra(AddScheduleActivity.EXTRA_END_TIME) ?: return@let
                val room = data.getStringExtra(AddScheduleActivity.EXTRA_ROOM)

                // Tạo đối tượng và chèn vào database qua ViewModel
                val item = ScheduleItem(
                    subjectName = subject,
                    dayOfWeek = day,
                    startTime = start,
                    endTime = end,
                    room = room
                )
                scheduleViewModel.insert(item)
            }
        }
        // Có thể thêm Toast nếu resultCode là CANCELED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ScheduleListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Khởi tạo ViewModel sử dụng Factory
        val dao = ScheduleDatabase.getDatabase(applicationContext).scheduleDao()
        val repository = ScheduleRepository(dao)
        val factory = ScheduleViewModelFactory(repository)
        scheduleViewModel = ViewModelProvider(this, factory)[ScheduleViewModel::class.java]

        // Quan sát dữ liệu và cập nhật RecyclerView
        scheduleViewModel.allScheduleItems.observe(this) { items ->
            items?.let { adapter.submitList(it) }
        }

        // Xử lý nút FAB (Thêm mới)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddScheduleActivity::class.java)
            addItemLauncher.launch(intent)
        }
    }
}