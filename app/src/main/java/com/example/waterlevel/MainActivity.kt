package com.example.waterlevel

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import com.example.waterlevel.databinding.ActivityMainBinding
import com.example.waterlevel.databinding.WaterwaveBinding
import com.example.wave.lib.Water_Wave
import java.util.Random

class MainActivity : AppCompatActivity() {
    lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

       b.waterwave.initWave()

    }


}