package com.jesen.cod.testlottie

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

interface ItemCallBack {
    fun onClick(name: String)
}

class SourceAdapter(private val sourceList: List<String>, private val layId: Int) :
    RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

    lateinit var mCallBack: ItemCallBack

    constructor(
        callBack: ItemCallBack,
        sourceList: List<String>,
        layId: Int
    ) : this(sourceList, layId) {
        mCallBack = callBack
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sourceName = view.findViewById<TextView>(R.id.t_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            layId, parent,
            false
        )
        val holder = ViewHolder(view)
        // val holder = ViewHolder(LayoutInflater.from(this).inflate(R.layout.item_source, parent, false))
        holder.itemView.setOnClickListener {
            mCallBack.onClick(holder.sourceName.text.toString())
        }
        return holder;
    }

    override fun getItemCount(): Int {
        return sourceList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = sourceList[position]
        holder.sourceName.text = name
    }
}