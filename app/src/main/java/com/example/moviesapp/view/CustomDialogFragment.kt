package com.example.moviesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.moviesapp.R
import kotlinx.android.synthetic.main.dialog_fragment.view.*

class CustomDialogFragment: DialogFragment() {
    private var name: String = ""
    private var description: String = ""
    private var textViewTitle: TextView? = null
    private var textViewDescription: TextView? = null
    companion object{
        fun newInstance(name: String, description: String): CustomDialogFragment{
            val fragment = CustomDialogFragment()
            val args = Bundle()
            args.putString("name_1",name)
            args.putString("description_1",description)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null){
            name = arguments?.getString("name_1").toString()
            description = arguments?.getString("description_1").toString()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View = inflater.inflate(R.layout.dialog_fragment, container, false)
        textViewTitle = rootView.findViewById(R.id.text_view_title)
        textViewDescription = rootView.findViewById(R.id.text_view_description)
        textViewTitle?.text = name
        textViewDescription?.text = description
        return rootView
    }
}