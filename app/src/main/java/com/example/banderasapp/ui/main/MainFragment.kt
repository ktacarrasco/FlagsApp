package com.example.banderasapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.banderasapp.R
import com.example.banderasapp.pojo.Flags
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() ,Adapter.MyClickListener  {

    private var flagsList =  ArrayList<Flags>()

    private lateinit var viewAdapter: Adapter
    private lateinit var mViewModel: MainViewModel
    private lateinit var mFragment: MainFragment


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Iniciando el ViewModel

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // Iniciando el adapter
        viewAdapter = Adapter(flagsList, this)
        RecyclerView.layoutManager = LinearLayoutManager(context)
        RecyclerView.adapter = viewAdapter

        mViewModel.fetchFromServer()
        mViewModel.getDataFromDB(id).observe(viewLifecycleOwner, Observer {
            Log.d("cant", it.toString())
            viewAdapter.updateData(it)

        })
    }

    override fun onItemClick(flags: Flags) {
        TODO("Not yet implemented")
    }

    override fun favClick(flags: Flags) {
        TODO("Not yet implemented")
    }

    override fun desfavClick(flags: Flags) {
        TODO("Not yet implemented")
    }

}