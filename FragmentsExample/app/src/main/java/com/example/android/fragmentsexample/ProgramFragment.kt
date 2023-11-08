package com.example.android.fragmentsexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
//

//
class ProgramFragment : Fragment() {

    val onCheckedChangeListener =
        RadioGroup.OnCheckedChangeListener { radioGroup, checkedId ->
            when (checkedId) {
                R.id.radio_AIActivity -> {Toast.makeText(this@ProgramFragment.requireActivity(),
                    "AIActivities", Toast.LENGTH_SHORT).show()}
                R.id.radio_VRActivity -> {}

                else -> {}
            }
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_program, container, false)
        //
        /*
        val programs = resources.getStringArray(R.array.programs)
        val context = context as MainActivity
        var mListView = view.findViewById<ListView>(R.id.fragment_container_view_program.list)
        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, programs)
        mListView.adapter = adapter
         */
        val radioGroup = fragmentView.findViewById(R.id.radio_group) as RadioGroup
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        // get the data from Bundle
        val info = arguments?.getString("onCreate")
       // Toast.makeText(this, "onCreateView", Toast.LENGTH_SHORT).show()

        //
        return fragmentView
    }


}