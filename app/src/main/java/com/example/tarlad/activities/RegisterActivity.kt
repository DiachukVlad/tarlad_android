package com.example.tarlad.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.tarlad.R
import com.example.tarlad.helpers.ServerConnector
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private val TAG = "RegisterActivity"
    private var token: String? = null
    private val serverConnector = ServerConnector()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initMessagingToken()
        initButtonCreate()
    }

    private fun initButtonCreate() {
        btn_create.setOnClickListener {
            if (field_name.text.isEmpty()) {
                return@setOnClickListener
            }
            if (field_surname.text.isEmpty()) {
                return@setOnClickListener
            }
            if (field_email.text.isEmpty()) {
                return@setOnClickListener
            }
            if (field_password.text.isEmpty()) {
                return@setOnClickListener
            }
            serverConnector.getRequest(serverConnector.REGISTER,
                HashMap<String, String>().apply {
                    put("name", field_name.text.toString())
                    put("surname", field_surname.text.toString())
                    put("email", field_email.text.toString())
                    put("password", field_password.text.toString())
                    put("token", token!!)
                },
                object : ServerConnector.DownloadListener{
                    override fun onError(error: String) {
                    }

                    override fun onDownloaded(result: String?) {
                        println(result)
                    }

                })
        }
    }

    private fun initMessagingToken(){
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                token = task.result?.token
                println(token)
            })

    }
}
