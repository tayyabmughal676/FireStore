package com.example.mrtayyab.firestore

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class MainActivity : AppCompatActivity() {


    lateinit var mBtn : Button
    lateinit var  mTexTEdit : EditText
//    lateinit var mFirebase : FirebaseDatabase
    lateinit var mFireStore : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTexTEdit = findViewById(R.id.editText)
        mBtn = findViewById(R.id.myBtn)

//        mFirebase = FirebaseDatabase.getInstance()
        mFireStore = FirebaseFirestore.getInstance()

        mBtn.setOnClickListener {

            val name = mTexTEdit.text.toString().trim()

            if(name.isEmpty()){

                mTexTEdit.error = "Enter value"
                return@setOnClickListener
            }
//            Toast.makeText(this , " Hello " + name  , Toast.LENGTH_SHORT).show()

            val userMap = HashMap<String , Any>()

            userMap.put("name", name)

            mFireStore.collection("Users").add(userMap).addOnCompleteListener { task: Task<DocumentReference> ->

                if(task.isSuccessful){
                    Toast.makeText(applicationContext , " Successfully" , Toast.LENGTH_LONG).show()
                }
            }
        }

    }


    override fun onBackPressed() {

        val builder = AlertDialog.Builder(this )
        builder.setMessage("Are You sure want to Exit ? ")
        builder.setCancelable(true)
        builder.setNegativeButton("No" , DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.cancel()
        })

        builder.setPositiveButton("Close!" , DialogInterface.OnClickListener { dialogInterface, i ->
            finish()
        })

        val  alertDialog = builder.create()
        alertDialog.show()

    }



}
