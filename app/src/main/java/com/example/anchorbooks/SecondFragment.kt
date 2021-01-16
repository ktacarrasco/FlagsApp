package com.example.anchorbooks

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.anchorbooks.pojo.Books
import com.example.anchorbooks.ui.main.Adapter
import com.example.anchorbooks.ui.main.MainViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

    private const val ARG_PARAM1 = "id"
    private const val ARG_PARAM2 = "name"
    private const val ARG_PARAM3 = "image"


    private var listItem: List<Books> = ArrayList<Books>()

    private lateinit var viewAdapter: Adapter
    private lateinit var mViewModel: MainViewModel
    private lateinit var mFragment: SecondFragment
    private lateinit var RecyclerView: RecyclerView
    private var textdetail: TextView? = null
    private var imagedetail: ImageView? = null


    class SecondFragment : Fragment(), Adapter.IAdapter {

        private var param1: Int? = null
        private var param2: String? = null
        private var param3: String? = null

        // private const val ARG_PARAM2 = "images"

        private lateinit var mViewModel: MainViewModel
        private lateinit var mFragment: SecondFragment

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            arguments?.let {
                param1 = it.getInt(ARG_PARAM1)
                param2 = it.getString(ARG_PARAM2)
                param3 = it.getString(ARG_PARAM3)

                //Iniciando el ViewModel
                mViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)


            }


        }


        @SuppressLint("SetTextI18n")
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view: View = inflater.inflate(R.layout.fragment_second, container, false)



            param1?.let {
                mViewModel.getIdDataFromDB(it).observe(viewLifecycleOwner, Observer { it ->
                    Log.d("cant", it.toString())

                    //viewAdapter.updateData(it)

                    view.titleTV.text = " ${it.title}"
                    //view.titleTV.text = it.name
                    if (it.author != null) {
                        Log.d("author", it.author)
                        view.apparanceTV.text = "Autor: ${it.author}"
                    }

                    view.apparance2TV.text = "Region = ${it.country}"
                    view.apparance3TV.text = "Idioma =  $ ${it.language}"
                    Picasso.get()
                        .load(it.imageLink)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(view.photoTV)

                    fab.setOnClickListener { view ->
                        Snackbar.make(view, "Send mail", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                        val Email = Intent(Intent.ACTION_SEND)
                        Email.setType("text/email")
                        Email.putExtra(
                            Intent.EXTRA_EMAIL,
                            arrayOf<String>("info@plaplix.cl")
                        ) //destinatario email
                        Email.putExtra(
                            Intent.EXTRA_SUBJECT,
                            "consulta por ${it.title} , id ${it.id}"
                        ) // Email 's Subject
                        Email.putExtra(
                            Intent.EXTRA_TEXT, "“Hola\n" +
                                    "Vi el producto ${it.title} y me gustaría que me contactaran a este correo o al\n" +
                                    "siguiente número _________\n" +
                                    "Quedo atento.”"
                        ) //Email 's Greeting text
                        startActivity(Intent.createChooser(Email, "Send Feedback:"))


                    }


                })


            }


            return view
        }


        companion object {

            @JvmStatic
            fun newInstance(param1: String, param2: String) =
                SecondFragment().apply {
                    arguments = Bundle().apply {

                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
        }

        override fun getFromAdapter(id: Int) {
            mViewModel.getDataFromDB(id)
        }


    }
