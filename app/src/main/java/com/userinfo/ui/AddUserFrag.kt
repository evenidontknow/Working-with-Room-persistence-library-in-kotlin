package com.userinfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.userinfo.R
import com.userinfo.database.User
import com.userinfo.database.UserDB
import com.userinfo.utilities.NavigationHost
import com.userinfo.utilities.showMessage
import kotlinx.android.synthetic.main.fragment_add_user.*
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 */
class AddUserFrag : BaseFragment() {

    private var mUser: User? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_user, container, false)
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            mUser = arguments!!.getSerializable("user") as User
            etName.setText(mUser?.userName)
            etEmail.setText(mUser?.userEmail)
        }

        floating_action_button_save.setOnClickListener {

            val userName = etName.text.toString()
            val userEmail = etEmail.text.toString()
            if (userName.isEmpty()) {
                etName.error = "Name can't be empty"
                etName.requestFocus()
                return@setOnClickListener
            }

            if (userEmail.isEmpty()) {
                etEmail.error = "Email can't be empty"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            launch {

                val userInstance = User(userName, userEmail)

                context?.let {

                    if (mUser == null) {
                        UserDB(it).getDAO().addUser(userInstance)
                    } else {
                        userInstance.id = mUser!!.id
                        UserDB(it).getDAO().updateUser(userInstance)
                    }

                    (activity as NavigationHost).navigateTo(HomeFrag(), false)
                }
            }

        }

    }


}
