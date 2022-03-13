package com.izo.githubuserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvUser: RecyclerView
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUser = findViewById(R.id.rv_user)
        rvUser.setHasFixedSize(true)

        list.addAll(listuser)
        showRecyclerList()
    }

    private val listuser: ArrayList<User>
        get() {
            val dataUsername = resources.getStringArray(R.array.data_username)
            val dataName = resources.getStringArray(R.array.data_name)
            val dataLocation = resources.getStringArray(R.array.data_location)
            val dataRepository = resources.getStringArray(R.array.data_repository)
            val dataCompany = resources.getStringArray(R.array.data_company)
            val dataFollowers = resources.getStringArray(R.array.data_followers)
            val dataFollowing = resources.getStringArray(R.array.data_following)
            val dataAvatar = resources.obtainTypedArray(R.array.data_avatar)
            val listUser = ArrayList<User>()
            for (i in dataUsername.indices){
                val user = User(dataUsername[i],dataName[i], dataAvatar.getResourceId(i, -1), dataCompany[i],dataLocation[i],dataRepository[i],dataFollowers[i], dataFollowing[i])
                listUser.add(user)
            }
            return listUser
        }

    private fun showRecyclerList(){
        rvUser.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(list)
        rvUser.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra(DetailActivity.DATA, data)
                startActivity(intentToDetail)
            }
        })
    }

}