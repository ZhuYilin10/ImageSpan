package com.example.zhuyilin.imagespan

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.widget.TextView
import com.squareup.picasso.Picasso


class URLImageParser(private val textView: TextView, private val context: Context) {

    fun getDrawable(url: String): Drawable {
        val urlDrawable = URLDrawable()
        ImageGetterAsyncTask(context, urlDrawable, url).execute(textView)
        return urlDrawable
    }
}

class ImageGetterAsyncTask(private val context: Context, private val urlDrawable: URLDrawable, val url: String) : AsyncTask<TextView, Void, Bitmap?>() {
    private var textView: TextView? = null

    override fun doInBackground(vararg params: TextView): Bitmap? {
        textView = params[0]
        return try {
            Picasso.get().load(url).get()
        } catch (e: Exception) {
            null
        }
    }

    override fun onPostExecute(bitmap: Bitmap?) {
        val bitmapDrawable = BitmapDrawable(context.resources, bitmap)
        bitmapDrawable.setBounds(0, 0, 100, 100)
        urlDrawable.setBounds(0, 0, 100, 100)
        urlDrawable.drawable = bitmapDrawable
        urlDrawable.invalidateSelf()
        textView!!.invalidate()
    }
}

class URLDrawable : BitmapDrawable() {
    var drawable: Drawable? = null

    override fun draw(canvas: Canvas) {
        if (drawable != null) {
            drawable!!.draw(canvas)
        }
    }
}