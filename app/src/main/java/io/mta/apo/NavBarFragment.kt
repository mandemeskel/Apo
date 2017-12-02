package io.mta.apo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private val RECENT_SEARCHES_ACT: String = RecentSearchesActivity::class.java.toString()
    private val MAIN_ACT: String = MainActivity::class.java.toString()
    private val SEARCH_FORM_ACT: String = SearchFormActivity::class.java.toString()
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
        currentActivity = activity::class.java.toString()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
    savedInstanceState: Bundle?):View? {
        val view = inflater!!.inflate(R.layout.fragment_nav_bar, container, false)
        setUpEventListeners(view)
        setUpUI(view)
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun gotoRecentSearches() {
        if(currentActivity != RECENT_SEARCHES_ACT) {
            val intent = Intent(activity, RecentSearchesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun btnCaptureClick() {
        if(currentActivity == MAIN_ACT)
            // capture image
        else {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun gotoSearchForm() {
        if(currentActivity != SEARCH_FORM_ACT) {
            val intent = Intent(activity, SearchFormActivity::class.java)
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

    private fun setUpEventListeners(fragment: View) {
        val btnRecentSearches = fragment.findViewById<ImageButton>(R.id.btnRecentSearches)
        val btnCapture = fragment.findViewById<ImageButton>(R.id.btnCapture)
        val btnSearchForm = fragment.findViewById<ImageButton>(R.id.btnSearchForm)

        btnRecentSearches.setOnClickListener(onBtnClick)
        btnCapture.setOnClickListener(onBtnClick)
        btnSearchForm.setOnClickListener(onBtnClick)
    }

    private fun setUpUI(fragment: View) {
        var btn: ImageButton? = null

        when(currentActivity) {
            RECENT_SEARCHES_ACT -> btn = fragment.findViewById<ImageButton>(R.id.btnRecentSearches)
            MAIN_ACT -> btn = fragment.findViewById<ImageButton>(R.id.btnCapture)
            SEARCH_FORM_ACT -> btn = fragment.findViewById<ImageButton>(R.id.btnSearchForm)
        }

        // TODO: getColor is deprecated, replace
        // set btn color to blue
        if(btn != null)
            btn.setColorFilter(resources.getColor(R.color.colorPrimary))
    }
}
