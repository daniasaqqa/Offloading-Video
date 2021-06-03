package com.example.offloadingvideo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {

    var videoURL = ""
    var playerr: SimpleExoPlayer? =null
    private var playReady =true
    private  var currentWindow = 0
    private var playedPostion:Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        if (intent != null){

           val vedioo = intent.getStringExtra("video").toString()
            videoURL = when (vedioo) {
                "v1" -> {
                    intent.getStringExtra("url").toString()
                }
                "v2" -> {
                    intent.getStringExtra("url").toString()
                }
                else -> {
                    intent.getStringExtra("url").toString()
                }
            }


        }
    }
    fun initVideo(){
        playerr= ExoPlayerFactory.newSimpleInstance(this)
        video_view.player =playerr!!
        var uri = Uri.parse(videoURL)
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