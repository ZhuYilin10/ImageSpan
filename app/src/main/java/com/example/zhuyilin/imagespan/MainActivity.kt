package com.example.zhuyilin.imagespan

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ImageSpan
import android.util.Base64
import android.widget.TextView
import java.io.ByteArrayInputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.image_span)

        //本地图片加载
        val spannableString = SpannableString("0000000ddddddddd")
        val drawable = ContextCompat.getDrawable(this, R.drawable.ic_launcher_foreground)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        spannableString.setSpan(ImageSpan(drawable), 4, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        //网络图片加载
        val drawableFromNet = URLImageParser(textView, this).getDrawable("https://www.baidu.com/img/bd_logo1.png")
        drawableFromNet.setBounds(0, 0, 100, 100)
        spannableString.setSpan(ImageSpan(drawableFromNet), 4, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        //Data Base64
        val stream = ByteArrayInputStream(Base64.decode("".toByteArray(), Base64.DEFAULT))
        val bitmap = BitmapFactory.decodeStream(stream)
        val bitmapDrawable = BitmapDrawable(this.resources, bitmap)
        bitmapDrawable.setBounds(0, 0, 100, 100)
        spannableString.setSpan(ImageSpan(bitmapDrawable), 0, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.text = spannableString
    }
}
