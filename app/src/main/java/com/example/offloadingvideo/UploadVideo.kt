package com.example.offloadingvideo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.activity_upload_video.*
import kotlinx.android.synthetic.main.activity_video.*

class UploadVideo : AppCompatActivity() {
    var videoPath = ""
    var playerr: SimpleExoPlayer? =null
    private var playReady =true
    private  var currentWindow = 0
    private var playedPostion:Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_video)

        btn_uploadd.setOnClickListener {
            uploadVideo()
        }
    }

    private fun uploadVideo() {
        val intent = Intent()
        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Select Video"), 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }

            videoPath = data.data.toString()

        }
    }

    fun initVideo(){
        playerr= ExoPlayerFactory.newSimpleInstance(this)
        video_view_upload.player =playerr!!
        var uri = Uri.parse(videoPath)
        var dataSource = DefaultDataSourceFactory(this,"exoplayer-codelab")
        var mediaSource : MediaSource = ProgressiveMediaSource.Factory(dataSource).createMediaSource(uri)
        playerr!!.playWhenReady = playReady
        playerr!!.seekTo(currentWindow,playedPostion)
        playerr!!.prepare(mediaSource,false,false)

    }

    fun releaseVideo(){
        if (playerr != null){
            playReady = playerr!!.playWhenReady
            playedPostion = playerr!!.currentPosition
            currentWindow = playerr!!.currentWindowIndex
            playerr!!.release()
            playerr = null
        }
    }

    override fun onStart() {
        super.onStart()
        initVideo()

    }

    override fun onResume() {
        super.onResume()
        if (playerr != null) {
            initVideo()
        }
    }

    override fun onStop() {
        super.onStop()
        releaseVideo()
    }

    override fun onPause() {
        super.onPause()
        releaseVideo()
    }
}