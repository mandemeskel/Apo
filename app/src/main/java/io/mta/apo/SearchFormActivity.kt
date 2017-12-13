package io.mta.apo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.os.StrictMode
import android.util.Log


class SearchFormActivity : AppCompatActivity() {
    private val TAG = "SearachFormActivity"

    //Form Parameters
    private lateinit var pill_name:EditText
    private lateinit var pill_imprint:EditText
    private lateinit var pill_color:EditText

    //Buttons
    private lateinit var submit_form_Button:ImageButton
    private lateinit var pill_request_server:Server


    //Headers
    //private var activity_header = findViewById<TextView>(R.id.search_form_activity_header)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_form)

        init()

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    fun init() {
        pill_request_server = Server()
        pill_name = findViewById(R.id.search_form_et_pill_name)
        pill_imprint = findViewById(R.id.search_form_et_pill_imprint)
        pill_color = findViewById(R.id.search_form_et_pill_color)

        submit_form_Button = findViewById(R.id.search_form_ib_submit_form)

        submit_form_Button.setOnClickListener({submit_click_listener()})
    }

    fun submit_click_listener(){
        val pills = pill_request_server.queryServerPillOption(pill_name.text.toString(),pill_imprint.text.toString(),pill_color.text.toString()).size.toString()

        Log.i(TAG, "number of pills retrieved ${pills.length}")
    }
}