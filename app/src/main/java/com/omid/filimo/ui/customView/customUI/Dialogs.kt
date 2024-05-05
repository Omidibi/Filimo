package com.omid.filimo.ui.customView.customUI

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.omid.filimo.R
import com.omid.filimo.activity.MainWidget

class Dialogs {

    companion object {

        fun mainActivity(context: Context, navController: NavController) {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle(R.string.login_or_register)
            dialog.setMessage(R.string.message_dialog_main_activity)
            dialog.setPositiveButton(R.string.yes) { _, _ ->
                navController.navigate(R.id.loginFragment)
                MainWidget.bnv.visibility = View.GONE
                MainWidget.toolbar.visibility = View.GONE
            }
            dialog.setNegativeButton(R.string.no) { _, _ ->

            }
            dialog.show()
        }

        fun sendCommentDialog(context: Context, fragment: Fragment) {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle(R.string.login_or_register)
            dialog.setMessage(R.string.message_send_comment)
            dialog.setPositiveButton(R.string.yes) { _, _ ->
                fragment.findNavController().navigate(R.id.action_videoPlayerFragment_to_loginFragment)
                MainWidget.bnv.visibility = View.GONE
                MainWidget.toolbar.visibility = View.GONE
            }
            dialog.setNegativeButton(R.string.no) { _, _ ->

            }
            dialog.show()
        }

        fun playOrRegisterDialog(context: Context, fragment: Fragment) {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle(R.string.login_or_register)
            dialog.setMessage(R.string.message_play_or_register)
            dialog.setPositiveButton(R.string.yes) { _, _ ->
                fragment.findNavController().navigate(R.id.action_videoPlayerFragment_to_loginFragment)
                MainWidget.bnv.visibility = View.GONE
                MainWidget.toolbar.visibility = View.GONE
            }
            dialog.setNegativeButton(R.string.no) { _, _ ->

            }
            dialog.show()
        }
    }
}