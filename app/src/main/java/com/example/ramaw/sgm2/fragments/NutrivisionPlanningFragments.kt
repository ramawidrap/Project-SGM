package com.example.ramaw.sgm2.fragments


import android.app.*
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RemoteViews
import android.widget.Switch
import android.widget.Toast

import com.example.ramaw.sgm2.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NutrivisionPlanningFragments : Fragment() {

    lateinit var notificationManager:NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "SGM"
    private val description = "Set Reminder"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("cekbool", "masuk sokin")
        val v = inflater.inflate(R.layout.fragment_nutrivision_planning_fragments2, container, false)
        // Inflate the layout for this fragment
        val setreminder = v.findViewById<Switch>(R.id.setreminder)
        val intent = Intent(v.context, LauncherActivity::class.java)
        val pending = PendingIntent.getActivity(v.context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            setreminder.setOnCheckedChangeListener { buttonView,isChecked ->
                if (isChecked == true) {
                    Toast.makeText(v.rootView.context,"Fitur Reminder Telah Aktif",Toast.LENGTH_LONG)

                }
            }
        return v
    }


}
