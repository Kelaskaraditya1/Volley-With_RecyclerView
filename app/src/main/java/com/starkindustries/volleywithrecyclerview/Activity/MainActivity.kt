package com.starkindustries.volleywithrecyclerview.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.starkindustries.volleywithrecyclerview.Adapter.RecyclerViewAdapter
import com.starkindustries.volleywithrecyclerview.Data.UserInfo
import com.starkindustries.volleywithrecyclerview.Data.UserInfoItem
import com.starkindustries.volleywithrecyclerview.R
import com.starkindustries.volleywithrecyclerview.Singleton.Singleton
import com.starkindustries.volleywithrecyclerview.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    val URL:String="https://api.github.com/users"
    lateinit var userInfo:UserInfo
    var userInfoItem = arrayOf<UserInfoItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.response.setOnClickListener(){
            var request:StringRequest = StringRequest(Request.Method.GET,URL,
                Response.Listener
                {
                    userInfo=UserInfo()
                    var gsonBuilder:GsonBuilder = GsonBuilder()
                    var gson: Gson = gsonBuilder.create()
                    userInfoItem=gson.fromJson(it,Array<UserInfoItem>::class.java)
                    userInfoItem.forEach{
                        userInfo.add(it)
                    }
                    binding.recyclerView.layoutManager= LinearLayoutManager(applicationContext)
                    binding.recyclerView.adapter=RecyclerViewAdapter(applicationContext,userInfo)
                },
                Response.ErrorListener
                {
                    Log.d("error","The error is:"+it.message.toString().trim())
                })
//            var queue:RequestQueue = Volley.newRequestQueue(applicationContext)
//            queue.add(request)
            Singleton.singleTon(applicationContext).addRequest(request)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}