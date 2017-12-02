package io.mta.apo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton


/**
   * A simple [Fragment] subclass.
   * Activities that contain this fragment must implement the
   * [NavBarFragment.OnFragmentInteractionListener] interface
   * to handle interaction events.
   * Use the [NavBarFragment.newInstance] factory method to
   * create an instance of this fragment.
  */
class NavBarFragment: Fragment() {
    private val TAG: String = "NavBarFragment"
    private var currentActivity:String? = null

    private val onBtnClick: View.OnClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.btnRecentSearches -> gotoRecentSearches()
            R.id.btnCapture -> btnCaptureClick()
            R.id.btnSearchForm -> gotoSearchForm()
        }
    }

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        currentActivity = activity.toString()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
    savedInstanceState: Bundle?):View? {
        val view = inflater!!.inflate(R.layout.fragment_nav_bar, container, false)
        setUpEventListeners(view)
        setUpUI()
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun gotoRecentSearches() {
        Log.i(TAG, "btnRecentSearches pressed")
        val recentSearchesAct = RecentSearchesActivity::class.java
        Log.i(TAG, recentSearchesAct.toString())
        if(currentActivity != recentSearchesAct.toString()) {
            val intent = Intent(activity,recentSearchesAct )
            startActivity(intent)
        }
    }

    private fun btnCaptureClick() {
        val mainActivity = MainActivity::class.java
        if(currentActivity == mainActivity.toString())
            // capture image
        else {
            val intent = Intent(activity, mainActivity)
            startActivity(intent)
        }
    }

    private fun gotoSearchForm() {
        val searchActivity = SearchFormActivity::class.java
        if(currentActivity != searchActivity.toString()) {
            val intent = Intent(activity, searchActivity)
            startActivity(intent)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters
         *
         * @return A new instance of fragment NavBarFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(): NavBarFragment {
            val fragment = NavBarFragment()
            return fragment
        }
    }

    private fun setUpEventListeners(view: View) {
        val btnRecentSearches = view.findViewById<ImageButton>(R.id.btnRecentSearches)
        val btnCapture = view.findViewById<ImageButton>(R.id.btnCapture)
        val btnSearchForm = view.findViewById<ImageButton>(R.id.btnSearchForm)

        btnRecentSearches.setOnClickListener(onBtnClick)
        btnCapture.setOnClickListener(onBtnClick)
        btnSearchForm.setOnClickListener(onBtnClick)
    }

    private fun setUpUI() {
        // TODO
    }
}
