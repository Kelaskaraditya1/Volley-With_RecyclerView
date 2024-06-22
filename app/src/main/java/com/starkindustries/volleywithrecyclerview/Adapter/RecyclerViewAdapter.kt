package com.starkindustries.volleywithrecyclerview.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.starkindustries.volleywithrecyclerview.Data.UserInfo
import com.starkindustries.volleywithrecyclerview.Model.User
import com.starkindustries.volleywithrecyclerview.R

open class RecyclerViewAdapter(var context_:Context,var userList_:UserInfo) :RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>()
{
    lateinit var context:Context
    lateinit var userList: UserInfo
    init
    {
        this.context=context_
        this.userList=userList_
    }
    inner open class ViewHolder(var view: View):RecyclerView.ViewHolder(view)
    {
     lateinit var profileImage:AppCompatImageView
     lateinit var profileName: AppCompatTextView
     init
     {
         profileImage=view.findViewById(R.id.profileImage)
         profileName=view.findViewById(R.id.profileName)
     }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        var view:View = LayoutInflater.from(context).inflate(R.layout.custom_row,parent,false)
        var viewHolder:ViewHolder = ViewHolder(view)
        return viewHolder
    }
    override fun getItemCount(): Int
    {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        Glide.with(context).load(userList.get(position).avatar_url).into(holder.profileImage)
        holder.profileName.setText(userList.get(position).login)
    }
}