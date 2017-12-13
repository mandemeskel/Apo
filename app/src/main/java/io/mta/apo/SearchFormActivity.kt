package io.mta.apo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.*
import android.content.Intent
import android.view.View
import android.widget.AdapterView




class SearchFormActivity : AppCompatActivity() {
    private val TAG = "SearachFormActivity"

    //Form Parameters
    private lateinit var pill_name:EditText
    private lateinit var pill_imprint:EditText
    private lateinit var pill_color:EditText

    //Buttons
    private lateinit var submit_form_Button:ImageButton
    private lateinit var pill_request_server:Server

    private lateinit var pills: Array<Pill>
    private lateinit var activity: Context

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

        pills = emptyArray()

        activity = this
    }

    fun submit_click_listener(){
        pills = pill_request_server.queryServerPillOption(pill_name.text.toString(),pill_imprint.text.toString(),pill_color.text.toString())

        val pill_names = pills.map { pill -> pill.brand_name }
        val adapter = ArrayAdapter<String>(
                this,
                R.layout.listview_item,
                pill_names
        )
        val listview = findViewById<ListView>(R.id.search_results)
        listview.setOnItemClickListener(onItemClick)
        listview.adapter = adapter
        Log.i(TAG, "number of pills retrieved ${pills.size}")
    }

    var onItemClick: AdapterView.OnItemClickListener = object : AdapterView.OnItemClickListener {
        override fun onItemClick(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {

            Log.i("search results", position.toString())

            val pill = pills[position]
            pill.save()
            Pill.saveAllPills(activity)
            Pill.loadPills(activity)
            val intent = Intent(activity, PillPage::class.java)
            intent.putExtra("brand", pill.brand_name)
            intent.putExtra("medical", pill.medical_name)
            intent.putExtra("img", pill.img_path)
            intent.putExtra("d", pill.description)
            startActivity(intent)

        }
    }
}