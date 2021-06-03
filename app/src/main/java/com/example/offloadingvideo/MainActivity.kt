package com.example.offloadingvideo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_v1.setOnClickListener {
            var urlv1 = "https://cdn.videvo.net/videvo_files/video/free/2020-05/small_watermarked/3d_ocean_1590675653_preview.webm"
            var v1 = "v1"
            intentFun(v1,urlv1)
        }
        btn_v2.setOnClickListener {
            var urlv2 = "https://cdn.videvo.net/videvo_files/video/free/2017-08/small_watermarked/170724_15_Setangibeach_preview.webm"
            var v2 = "v2"
            intentFun(v2,urlv2)
        }
        btn_v3.setOnClickListener {
            val urlv3 = "https://cdn.videvo.net/videvo_files/video/free/2018-01/small_watermarked/171124_H1_006_preview.webm"
            val v3 = "v3"
            intentFun(v3,urlv3)
        }
        btn_upload.setOnClickListener {
            startActivity(Intent(this,UploadVideo::class.java))
        }
    }

    fun intentFun(noVideo:String,urlV:String){
        val inte = Intent(this,VideoActivity::class.java)
        inte.putExtra("url", urlV)
        inte.putExtra("video", noVideo)
        startActivity(inte)
    }
}