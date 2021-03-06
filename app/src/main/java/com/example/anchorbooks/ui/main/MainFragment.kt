package com.example.anchorbooks.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anchorbooks.R
import com.example.anchorbooks.pojo.Books
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() ,Adapter.MyClickListener  {

    private var booksList =  ArrayList<Books>()

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
        viewAdapter = Adapter(booksList, this)
        RecyclerView.layoutManager = LinearLayoutManager(context)
        RecyclerView.adapter = viewAdapter

        mViewModel.fetchFromServer()
        mViewModel.getDataFromDB(id).observe(viewLifecycleOwner, Observer {
            Log.d("cant", it.toString())
            viewAdapter.updateData(it)

        })
    }

    override fun onItemClick(books: Books) {
        val bundle=Bundle()
        bundle.putInt("id",books.id)
        findNavController().navigate(R.id.action_firstFragment_to_secondFragment,bundle)
    }



}