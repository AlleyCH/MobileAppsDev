package com.example.alleychaggar_comp304lab3

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sin


class TweenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sun: ImageView = findViewById(R.id.sunImageView)
        val earth: ImageView = findViewById(R.id.earthImageView)

        val orbitRadius = resources.getDimensionPixelSize(R.dimen.orbit_radius).toFloat()
        val orbitTime = 10000L // 5 seconds for one complete orbit

        val orbitAnimator = createOrbitAnimator(orbitRadius, orbitTime)

        // Create rotation animation for the Earth
        val rotateAnimator = createRotateAnimator(orbitTime)

        // Combine the animations
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(orbitAnimator, rotateAnimator)

        // Start the animation
        animatorSet.start()
    }

    private fun createOrbitAnimator(radius: Float, duration: Long): Animator {
        val orbitAnimator = ValueAnimator.ofFloat(360f, 0f)
        orbitAnimator.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Float
            val angleRadians = Math.toRadians(animatedValue.toDouble())
            val x = radius * kotlin.math.cos(angleRadians)
            val y = radius * sin(angleRadians)
            val earthImageView: ImageView = findViewById(R.id.earthImageView)
            earthImageView.translationX = x.toFloat()
            earthImageView.translationY = y.toFloat()
        }
        orbitAnimator.repeatCount = ValueAnimator.INFINITE
        orbitAnimator.interpolator = LinearInterpolator()
        orbitAnimator.duration = duration
        return orbitAnimator
    }

    private fun createRotateAnimator(duration: Long): ObjectAnimator {
        val earthImageView: ImageView = findViewById(R.id.earthImageView)
        val rotateAnimator = ObjectAnimator.ofFloat(earthImageView, "rotation", 0f, 360f)
        rotateAnimator.repeatCount = ValueAnimator.INFINITE
        rotateAnimator.interpolator = LinearInterpolator()
        rotateAnimator.duration = duration
        return rotateAnimator
    }
}
