package io.mta.apo

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



/**
 * Created by sudo on 12/13/17.
 */
class PillArrayAdapter(val ctx: Context, val pills: Array<Pill>): ArrayAdapter<Pill>(ctx, R.layout.listview_item, pills) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val pill = getItem(position)

        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false)
        }

        // Lookup view for data population
        val tvName = view!!.findViewById<TextView>(R.id.txtItem)

        // Populate the data into the template view using the data object
        tvName.setText(pill!!.brand_name)

        return view
    }
}