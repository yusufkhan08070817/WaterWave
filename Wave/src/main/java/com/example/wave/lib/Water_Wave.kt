package com.example.wave.lib


import android.animation.ObjectAnimator
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.wave.R
import java.util.Random

class Water_Wave @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private lateinit var handler: Handler
    private var i = 0
    private val range = 2100f
    private lateinit var wave1: ImageView
    private lateinit var wave2: ImageView
    private lateinit var wave3: ImageView
    private lateinit var wave4: ImageView
    private lateinit var restimage: ImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.waterwave, this, true)
        handler = Handler(Looper.getMainLooper())
        wave1 = findViewById(R.id.waterwave1)
        wave2 = findViewById(R.id.waterwave2)
        wave3 = findViewById(R.id.waterwave3)
        wave4 = findViewById(R.id.waterwave4)
        restimage = findViewById(R.id.restwater)
    }

    fun initWave() {
        handlerLooper {
            if (i % 2 == 0) {
                val last = Random().nextInt(100)
                waveAnimation(wave1, -range, 10f * i + Random().nextInt(100), waveOBJ.duratation)
                waveAnimation(wave2, range, 10f * i + Random().nextInt(100), waveOBJ.duratation)
                waveAnimation(wave3, -range, 10f * i + Random().nextInt(100), waveOBJ.duratation)
                waveAnimation(wave4, range, 10f * i + last, waveOBJ.duratation)
                if (i < 50)
                    rest(restimage, 10f * i + last, waveOBJ.duratation * 2)
                else
                    rest(restimage, 8f * i, waveOBJ.duratation * 2)
            } else {
                val last = Random().nextInt(100)
                waveAnimation(wave1, range, 10f * i + Random().nextInt(100), waveOBJ.duratation)
                waveAnimation(wave2, -range, 10f * i + Random().nextInt(100), waveOBJ.duratation)
                waveAnimation(wave3, range, 10f * i + Random().nextInt(100), waveOBJ.duratation)
                waveAnimation(wave4, -range, 10f * i + last, waveOBJ.duratation)
            }
            ++i
        }
    }

    private fun handlerLooper(function: () -> Unit) {
        handler.postDelayed(object : Runnable {
            override fun run() {
                function()
                val fu = function
                handlerLooper(fu)
            }
        }, waveOBJ.duratation)
    }

    private fun waveAnimation(v: View, tx: Float, ty: Float, duration: Long) {
        val translateX = ObjectAnimator.ofFloat(v, "translationX", tx)
        val translateY = ObjectAnimator.ofFloat(v, "translationY", -ty)
        translateX.duration = duration
        translateY.duration = duration
        translateY.start()
        translateX.start()
    }

    private fun rest(v: View, ty: Float, duration: Long) {
        val translateY = ObjectAnimator.ofFloat(v, "scaleY", ty)
        translateY.duration = duration
        translateY.start()
    }
}
