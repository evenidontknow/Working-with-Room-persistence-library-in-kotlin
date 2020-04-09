package com.userinfo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.userinfo.R
import com.userinfo.utilities.NavigationHost

class MainActivity : AppCompatActivity(), NavigationHost {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState==null)
        {
            supportFragmentManager.
                    beginTransaction().
                    add(R.id.container,HomeFrag()).
                    commit()
        }
    }

    override fun navigateTo(fragment: Fragment, addToBackStack: Boolean) {

        val transaction = supportFragmentManager.beginTransaction().replace(R.id.container,fragment)
        if (addToBackStack)
        {
            transaction.addToBackStack(null)
        }

        transaction.commit()

    }
}
