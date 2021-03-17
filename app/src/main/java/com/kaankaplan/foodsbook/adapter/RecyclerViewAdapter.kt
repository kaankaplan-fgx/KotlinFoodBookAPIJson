package com.kaankaplan.foodsbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.kaankaplan.foodsbook.R
import com.kaankaplan.foodsbook.databinding.BesinRecylclerRowBinding
import com.kaankaplan.foodsbook.model.Besin
import com.kaankaplan.foodsbook.util.gorselIndir
import com.kaankaplan.foodsbook.util.placeHolderYap
import com.kaankaplan.foodsbook.view.BesinListesiFragmentDirections
import kotlinx.android.synthetic.main.besin_recylcler_row.view.*

class RecyclerViewAdapter(val besinListesi : ArrayList<Besin>) : RecyclerView.Adapter<RecyclerViewAdapter.BesinViewHolder>(),BesinClickListener {
    class BesinViewHolder(var view : BesinRecylclerRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BesinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.besin_recylcler_row,parent,false)
        val view = DataBindingUtil.inflate<BesinRecylclerRowBinding>(inflater,R.layout.besin_recylcler_row,parent,false)
        return BesinViewHolder(view)
    }

    override fun onBindViewHolder(holder: BesinViewHolder, position: Int) {

        holder.view.besin = besinListesi[position]
        holder.view.listener = this

        /*
        holder.itemView.besinIsmıTextViewRow.text = besinListesi.get(position).BesinIsim
        holder.itemView.recyclerRowKalori.text = besinListesi.get(position).BesinKalori
        //görsel

        holder.itemView.setOnClickListener {
            val action = BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(besinListesi.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.itemView.imageViewRecyclerRow.gorselIndir(besinListesi.get(position).besinGorsel, placeHolderYap(holder.itemView.context))

    */
    }

    override fun getItemCount(): Int {
        return besinListesi.size
    }

    fun besinListesiniGuncelle(yeniBesinListesi : List<Besin>){
        besinListesi.clear()
        besinListesi.addAll(yeniBesinListesi)
        notifyDataSetChanged()
    }

    override fun besinTiklandi(view: View) {
        val action = BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(view.uuidRow.text.toString().toIntOrNull()!!)
        Navigation.findNavController(view).navigate(action)
    }


}