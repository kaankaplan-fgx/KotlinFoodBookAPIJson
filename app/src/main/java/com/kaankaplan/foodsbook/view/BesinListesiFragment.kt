package com.kaankaplan.foodsbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaankaplan.foodsbook.R
import com.kaankaplan.foodsbook.adapter.RecyclerViewAdapter
import com.kaankaplan.foodsbook.viewmodel.BesinListesiViewModel
import kotlinx.android.synthetic.main.fragment_besin_listesi.*


class BesinListesiFragment : Fragment() {

    private lateinit var viewModel : BesinListesiViewModel
    private val recyclerAdapter = RecyclerViewAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besin_listesi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(BesinListesiViewModel::class.java)
        viewModel.refreshData()



        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerAdapter


        swiperefreshlayout.setOnRefreshListener {
            progressBar.visibility = View.VISIBLE
            hatamesajiTextView.visibility = View.GONE
            recyclerView.visibility = View.GONE

            viewModel.refreshFromInternet()
            swiperefreshlayout.isRefreshing = false

        }

        observeLiveData()

        }

    fun observeLiveData(){

        viewModel.besinler.observe(viewLifecycleOwner, Observer { besinler ->
            besinler?.let {
                recyclerView.visibility = View.VISIBLE
                hatamesajiTextView.visibility = View.GONE
                progressBar.visibility = View.GONE
                recyclerAdapter.besinListesiniGuncelle(besinler)
            }
        })

        viewModel.besinHataMesaji.observe(viewLifecycleOwner, Observer { hata ->
            hata?.let {
                if (it){
                    hatamesajiTextView.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    progressBar.visibility = View.GONE
                }else{
                    hatamesajiTextView.visibility = View.GONE
                }

            }
        })

        viewModel.besinProgressBar.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it == true){
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    hatamesajiTextView.visibility = View.GONE
                }else{
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    }


