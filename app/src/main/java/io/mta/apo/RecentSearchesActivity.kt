package io.mta.apo

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class RecentSearchesActivity : AppCompatActivity() {

    private lateinit var activity: Context
    private lateinit var pills: Array<Pill>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_searchs)

        activity = this
        pills = emptyArray()

        val lv: ListView = findViewById(R.id.recents)
        Pill.loadPills(this)
        pills = Pill.loaded_pills

        if( pills.isEmpty() ) {

            val tv = findViewById<View>(R.id.no_recents)
            tv.visibility = View.VISIBLE
            lv.visibility = View.GONE

        } else {

            val pill_names = pills.map { pill -> pill.brand_name }
            val adapter = ArrayAdapter(
                    this,
                    R.layout.listview_item,
                    pill_names
            )
            lv.adapter = adapter
            lv.setOnItemClickListener(onItemClick)

        }
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
