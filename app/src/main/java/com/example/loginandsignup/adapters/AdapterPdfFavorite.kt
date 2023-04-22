package com.example.loginandsignup.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginandsignup.activities.FestaritActivity

import com.example.loginandsignup.databinding.RowPdfFavoriteBinding
import com.example.loginandsignup.models.ModelPdf
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AdapterPdfFavorite : RecyclerView.Adapter<AdapterPdfFavorite.HolderPdfFavorite> {

    //Context
    private  val context: Context
    //Arraylist to hold festivals
    private var festivalArrayList: ArrayList<ModelPdf>

    //view binding
    private lateinit var binding: RowPdfFavoriteBinding

    //constructor
    constructor(context: Context, festivalArrayList: ArrayList<ModelPdf>) {
        this.context = context
        this.festivalArrayList = festivalArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderPdfFavorite {
        //bind/inflate row_pdf_favorite.xml
        binding = RowPdfFavoriteBinding.inflate(LayoutInflater.from(context), parent, false)

        return HolderPdfFavorite(binding.root)
    }

    override fun getItemCount(): Int {
        return festivalArrayList.size //return size of list | number of items in list
    }

    override fun onBindViewHolder(holder: HolderPdfFavorite, position: Int) {
        /*--- GET DATA, set data, handle click etc---*/

        //Get data; from database. Have to load details from festivals node
        val model = festivalArrayList[position]

        loadFestivalDetails(model, holder)

        //handle click, open pdf detail page, pass festival name to load details
        holder.itemView.setOnClickListener {
            val intent = Intent(context, FestaritActivity::class.java)
            intent.putExtra("name", model.name)
            context.startActivity(intent)
        }

        //handle click, remove from favorites
        holder.removeFavBtn.setOnClickListener {
            FestaritActivity.removeFromFavorite(context)
        }

    }

    private fun loadFestivalDetails(model: ModelPdf, holder: AdapterPdfFavorite.HolderPdfFavorite) {
        val names = model.name

        val ref = FirebaseDatabase.getInstance().getReference("Festivals")
        ref.child(names)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    //get festival info
                    val dateend = "${snapshot.child("dateend").value}"
                    val datestart = "${snapshot.child("datestart").value}"
                    val imageurl = "${snapshot.child("imageurl").value}"
                    val infotext = "${snapshot.child("infotext").value}"
                    val locationaddress = "${snapshot.child("locationaddress").value}"
                    val locationtown = "${snapshot.child("locationtown").value}"
                    val name = "${snapshot.child("name").value}"
                    val ticket = "${snapshot.child("ticket").value}"
                    val webpage = "${snapshot.child("webpage").value}"

                    //set data to model
                    model.isFavorite = true
                    model.dateend = dateend
                    model.datestart = datestart
                    model.imageurl = imageurl
                    model.infotext = infotext
                    model.locationaddress = locationaddress
                    model.locationtown = locationtown
                    model.name = name
                    model.ticket = ticket
                    model.webpage = webpage

                    holder.dateendTv.text = dateend
                    holder.datestartTv.text = datestart
                    holder.descriptionTv.text = infotext
                    holder.titleTv.text = name
                    holder.townTv.text = locationtown

                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

    }

    /* VIEW HOLDER CLASS TO MANAGE UI VIEWS OF row_pdf_favorite.xml*/
    inner class HolderPdfFavorite(itemView: View) : RecyclerView.ViewHolder(itemView){
        //init UI Views
        var pdfView = binding.pdfView
        var progressbar = binding.progressbar
        var titleTv = binding.titleTv
        var removeFavBtn = binding.removeFavBtn
        var descriptionTv = binding.descriptionTv
        var townTv = binding.townTv
        var dateendTv = binding.dateendTv
        var datestartTv = binding.datestartTv
    }

}