package com.userinfo.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.userinfo.R
import com.userinfo.adapters.RecyclerTouchListener
import com.userinfo.adapters.UserListAdapter
import com.userinfo.database.User
import com.userinfo.database.UserDB
import com.userinfo.utilities.ClickListener
import com.userinfo.utilities.NavigationHost
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 */
class HomeFrag : BaseFragment() {

    var users: List<User>? = null

    var singleUser: User? = null;


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.floating_action_button.setOnClickListener {

            (activity as NavigationHost).navigateTo(AddUserFrag(), false)

        }

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        launch {
            context?.let {
                users = UserDB(it).getDAO().getAllUsers()

                recyclerview.adapter = UserListAdapter(users!!)
            }
        }

        recyclerview.addOnItemTouchListener(
            RecyclerTouchListener(context,
                recyclerview, object : ClickListener {
                    override fun onClick(view: View?, position: Int) {
                        singleUser = users!![position]
                        var f = AddUserFrag()
                        var bundle = Bundle()
                        bundle.putSerializable("user", singleUser)
                        f.arguments = bundle
                        (activity as NavigationHost).navigateTo(f, false)
                    }

                    override fun onLongClick(view: View?, position: Int) {
                        singleUser = users!![position]
                        deleteNote()
                    }
                })
        )

    }

    private fun deleteNote() {
        AlertDialog.Builder(context).apply {
            setTitle("Are you sure?")
            setMessage("You cannot undo this operation")
            setPositiveButton("Yes") { _, _ ->
                launch {
                    UserDB(context).getDAO().deleteUser(singleUser!!)
                    (activity as NavigationHost).navigateTo(HomeFrag(), false)
                }
            }
            setNegativeButton("No") { _, _ -> }
        }.create().show()
    }
}
