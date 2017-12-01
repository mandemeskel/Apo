package io.mta.apo

import android.content.Context
import android.net.Uri
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
class NavBarFragment: Fragment() {

    private var currentActivity:String? = null

    private var mListener:OnFragmentInteractionListener? = null

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

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null)
            mListener!!.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener)
            mListener = context
        else
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri:Uri)
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "current_activity"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @param param1 Parameter 1.
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
