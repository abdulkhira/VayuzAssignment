package com.vayuzassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso

class SecondActivity : AppCompatActivity() {

    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val url = intent.getStringExtra("url")

        imageView = findViewById(R.id.img)

        Picasso.get()
            .load(url)
            .into(imageView)

    }
}