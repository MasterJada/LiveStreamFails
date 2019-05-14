package com.example.livestreamfails

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.livestreamfails.mvp.list.ListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeFragment(ListFragment())
    }

   fun changeFragment(fragment: Fragment, history: Boolean = false){
     val transaction =   supportFragmentManager.beginTransaction()
            if(history) transaction.addToBackStack("")
          transaction.replace(R.id.container, fragment)
           .commit()
   }
}
