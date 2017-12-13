package io.mta.apo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class PillPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pill_page)
        val brand = intent!!.getStringExtra("brand")
        val medical = intent.getStringExtra("medical")
        val img = intent.getStringExtra("img")
        val info = intent.getStringExtra("d")

        val tv_brand: TextView = findViewById(R.id.brand)
        val tv_medical: TextView = findViewById(R.id.medical)
        val iv_img: ImageView = findViewById(R.id.img)
        val tv_info: TextView = findViewById(R.id.info)

        tv_brand.text = brand
        tv_medical.text = medical
        tv_info.text = info
    }
}
