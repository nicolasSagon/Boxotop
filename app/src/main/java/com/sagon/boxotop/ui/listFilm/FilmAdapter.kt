package com.sagon.boxotop.ui.listFilm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sagon.boxotop.R
import com.sagon.boxotop.domain.model.Film
import com.squareup.picasso.Picasso

class FilmAdapter : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    private val listData : MutableList<Film> = mutableListOf()
    private val picasso: Picasso = Picasso.get()
    private var endOfRecyclerViewReachedListener : OnEndOfRecyclerViewReachedListener? =null
    private var onItemSelectedListener: OnItemSelectedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val filmCardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card_film, parent, false)

        return FilmViewHolder(filmCardView)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        if(position == listData.size - 1){
            endOfRecyclerViewReachedListener?.onLastElementReached()
        }
        holder.onBind(listData[position])
    }

    fun updateData(newDatas : List<Film>, isReset: Boolean){
        if(isReset){
            listData.clear()
        }
        listData.addAll(newDatas)
        notifyDataSetChanged()
    }

    fun addOnEndOfRecyclerViewReachedListener(listener: OnEndOfRecyclerViewReachedListener){
        this.endOfRecyclerViewReachedListener = listener
    }

    fun addOnItemSelectedListener(listener: OnItemSelectedListener) {
        this.onItemSelectedListener = listener
    }

    inner class FilmViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val filmTitle : TextView = view.findViewById(R.id.filmTitle)
        private val filmYear : TextView = view.findViewById(R.id.filmYear)
        private val filmImage : ImageView = view.findViewById(R.id.filmImageView)
        private var currentFilm: Film? = null

        init {
            view.setOnClickListener {
                onItemSelectedListener?.onItemSelected(currentFilm)
            }
        }

        fun onBind(item : Film) {
            currentFilm = item
            filmTitle.text = item.title
            filmYear.text = item.year
            picasso.load(item.poster).error(R.drawable.no_image_found).into(filmImage)
        }
    }

    interface OnEndOfRecyclerViewReachedListener {
        fun onLastElementReached()
    }

    interface OnItemSelectedListener {
        fun onItemSelected(film: Film?)
    }
}
