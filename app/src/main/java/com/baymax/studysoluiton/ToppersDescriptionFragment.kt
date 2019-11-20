package com.baymax.studysoluiton


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_toppers.*

/**
 * A simple [Fragment] subclass.
 */
class ToppersDescriptionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_toppers_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let{
            val safeargs = ToppersDescriptionFragmentArgs.fromBundle(it)
            textView.text = "Topper Description Fragment :${safeargs.numOfToppers}"
        }
    }


}
