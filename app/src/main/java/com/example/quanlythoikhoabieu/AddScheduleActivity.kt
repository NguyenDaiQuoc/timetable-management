package com.example.quanlythoikhoabieu

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class AddScheduleActivity : AppCompatActivity() {

    companion object {
        // Keys để Intent
        const val EXTRA_SUBJECT = "EXTRA_SUBJECT"
        const val EXTRA_DAY = "EXTRA_DAY"
        const val EXTRA_START_TIME = "EXTRA_START_TIME"
        const val EXTRA_END_TIME = "EXTRA_END_TIME"
        const val EXTRA_ROOM = "EXTRA_ROOM"
    }

    private lateinit var mEditSubjectView: EditText
    private lateinit var mEditRoomView: EditText
    private lateinit var mSpinnerDay: Spinner
    private lateinit var mTextStartTime: TextView
    private lateinit var mTextEndTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_schedule)

        mEditSubjectView = findViewById(R.id.edit_subject)
        mEditRoomView = findViewById(R.id.edit_room)
        mSpinnerDay = findViewById(R.id.spinner_day)
        mTextStartTime = findViewById(R.id.text_start_time)
        mTextEndTime = findViewById(R.id.text_end_time)
        val mButtonSave: Button = findViewById(R.id.button_save)

        // Xử lý chọn giờ
        mTextStartTime.setOnClickListener { showTimePicker(mTextStartTime) }
        mTextEndTime.setOnClickListener { showTimePicker(mTextEndTime) }

        mButtonSave.setOnClickListener {
            saveSchedule()
        }
    }

    private fun showTimePicker(textView: TextView) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(this, { _, hourOfDay, minuteOfHour ->
            val time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minuteOfHour)
            textView.text = time
        }, hour, minute, true).show()
    }

    private fun saveSchedule() {
        val replyIntent = Intent()

        val subject = mEditSubjectView.text.toString().trim()
        val startTime = mTextStartTime.text.toString().trim()
        val endTime = mTextEndTime.text.toString().trim()

        if (subject.isEmpty() || startTime.isEmpty() || endTime.isEmpty() || mSpinnerDay.selectedItem == null) {
            Toast.makeText(applicationContext, "Vui lòng điền đủ Tên môn, Ngày và Thời gian.", Toast.LENGTH_LONG).show()
            setResult(Activity.RESULT_CANCELED, replyIntent)
        } else {
            val day = mSpinnerDay.selectedItem.toString()
            val room = mEditRoomView.text.toString().trim()

            replyIntent.putExtra(EXTRA_SUBJECT, subject)
            replyIntent.putExtra(EXTRA_DAY, day)
            replyIntent.putExtra(EXTRA_START_TIME, startTime)
            replyIntent.putExtra(EXTRA_END_TIME, endTime)
            replyIntent.putExtra(EXTRA_ROOM, room)

            setResult(Activity.RESULT_OK, replyIntent)
        }
        finish()
    }
}