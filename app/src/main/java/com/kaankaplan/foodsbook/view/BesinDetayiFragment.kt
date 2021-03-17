package com.kaankaplan.foodsbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.kaankaplan.foodsbook.R
import com.kaankaplan.foodsbook.databinding.FragmentBesinDetayiBinding
import com.kaankaplan.foodsbook.model.Besin
import com.kaankaplan.foodsbook.util.gorselIndir
import com.kaankaplan.foodsbook.util.placeHolderYap
import com.kaankaplan.foodsbook.viewmodel.BesinDetayiViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_besin_detayi.*


class BesinDetayiFragment : Fragment() {

    private lateinit var viewModel : BesinDetayiViewModel
    private var besinId = 0
    private lateinit var dataBinding : FragmentBesinDetayiBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_besin_detayi,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        geributton.setOnClickListener {
            val action = BesinDetayiFragmentDirections.actionBesinDetayiFragmentToBesinListesiFragment()
            Navigation.findNavController(it).navigate(action)
        }

        arguments?.let {
            besinId = BesinDetayiFragmentArgs.fromBundle(it).besinId
            println(besinId)
        }

        viewModel = ViewModelProviders.of(this).get(BesinDetayiViewModel::class.java)
        viewModel.roomVerisiniAl(besinId)


        observeLiveData()


        }

    fun observeLiveData(){
        viewModel.besinLiveData.observe(viewLifecycleOwner, Observer {besin ->
            besin?.let {
                dataBinding.secilenBesin = it

                /*
                besinBaslikBesinDetayi.text = it.BesinIsim
                besinKalorisiTextViewBesinDetayi.text = it.BesinKalori
                besinKarbonhidratTextViewBesinDetayi.text = it.BesinKarbonhidrat
                besinProteiniTextViewBesinDetayi.text = it.BesinProtein
                besinYagTextViewBesinDetayi.text = it.BesinYag
                context?.let {
                    ImageViewBesinDetayi.gorselIndir(besin.besinGorsel, placeHolderYap(it))
                }
                */
            }
        })
    }



}