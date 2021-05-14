package com.jesen.cod.testlottie

import android.content.Context
import android.util.Log
import java.io.IOException
import java.util.*

object AssertUtil {
    fun getAssetPath(context: Context): List<String> {
        val am = context.assets
        var path: Array<String>? = null
        try {
            path = am.list("") // ""获取所有,填入目录获取该目录下所有资源
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val jsPaths: MutableList<String> =
            ArrayList()
        for (i in path!!.indices) {
            if (path[i].endsWith(".json")) { // 根据图片特征找出图片
                jsPaths.add(path[i])
                Log.i("9999", path[i])
            }
        }
        return jsPaths
    }
}