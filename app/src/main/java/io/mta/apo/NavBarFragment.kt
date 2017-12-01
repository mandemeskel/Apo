package io.mta.apo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
   * A simple [Fragment] subclass.
   * Activities that contain this fragment must implement the
   * [NavBarFragment.OnFragmentInteractionListener] interface
   * to handle interaction events.
   * Use the [NavBarFragment.newInstance] factory method to
   * create an instance of this fragment.
  */
class NavBarFragment: Fragment(), View.OnClickListener {

    private var currentActivity:String? = null

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        if (getArguments() != null) {
            currentActivity = getArguments().getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
    savedInstanceState: Bundle?):View? {
        return inflater!!.inflate(R.layout.fragment_nav_bar, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btnRecentSearches -> gotoRecentSearches()
        }
    }

    private fun gotoRecentSearches() {
        val intent = Intent(activity, RecentSearchesActivity::class.java)
        startActivity(intent)
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "current_activity"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @param currentActivity The class name of the activity the fragment is in.
         * *
         * @return A new instance of fragment NavBarFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(currentActivity: String): NavBarFragment {
            val fragment = NavBarFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, currentActivity)
            fragment.arguments = args
            return fragment
        }
    }
}
