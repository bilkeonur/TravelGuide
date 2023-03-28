package com.ob.travelguide.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.ob.travelguide.R
import com.ob.travelguide.model.Place.Icon
import javax.inject.Inject
import com.ob.travelguide.model.Place.Result

class PlaceRecyclerAdapter @Inject constructor(val glide: RequestManager): RecyclerView.Adapter<PlaceRecyclerAdapter.PlaceViewHolder>(){
    class PlaceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val diffUtil = object: DiffUtil.ItemCallback<Result>() {

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerViewDiffer = AsyncListDiffer(this, diffUtil)

    var places: List<Result>
        get() = recyclerViewDiffer.currentList
        set(value) = recyclerViewDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_place, parent,false)
        return PlaceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return places.size
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val imgPlace = holder.itemView.findViewById<ImageView>(R.id.imgPlace)
        val txtPlaceName = holder.itemView.findViewById<TextView>(R.id.txtPlaceName)
        val txtPlaceCategory = holder.itemView.findViewById<TextView>(R.id.txtPlaceCategory)
        val txtPlaceRegion = holder.itemView.findViewById<TextView>(R.id.txtPlaceRegion)

        val place = places[position]

        val icon:Icon? = place.categories?.get(0)?.icon
        val iconUrl = icon?.prefix + "64" + icon?.suffix

        holder.itemView.apply {
            glide.load(iconUrl).into(imgPlace)
            txtPlaceName.text = place?.name
            txtPlaceCategory.text = place.categories?.get(0)?.name
            txtPlaceRegion.text = place.location?.region
        }
    }
}