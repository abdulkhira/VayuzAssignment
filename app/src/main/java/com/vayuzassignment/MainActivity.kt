package com.vayuzassignment

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Observer
import com.vayuzassignment.data.model.Resource
import com.vayuzassignment.data.viewmodels.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    lateinit var button: Button
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)

        button = findViewById(R.id.fetchData)

        button.setOnClickListener {
            viewModel.getImages()
        }


        viewModel.getImageResponse.observe(this, { resource ->
            if (resource != null) {
                when (resource.status) {
                    Resource.Status.LOADING -> {
                        progressDialog.show()
                    }
                    Resource.Status.SUCCESS -> {
                        if (progressDialog.isShowing) {
                            progressDialog.dismiss()
                        }
                        startActivity(Intent(this, SecondActivity::class.java).putExtra("url",
                            resource.data?.message
                        ))
                    }
                    Resource.Status.ERROR -> {
                        Log.e("tag", " == = = = " + resource.message)
                        if (progressDialog.isShowing) {
                            progressDialog.dismiss()
                        }
                    }
                    Resource.Status.OFFLINE_ERROR -> {
                        if (progressDialog.isShowing) {
                            progressDialog.dismiss()
                        }
                    }
                    else -> {
                        if (progressDialog.isShowing) {
                            progressDialog.dismiss()
                        }
                    }
                }
            }
        })


    }

}