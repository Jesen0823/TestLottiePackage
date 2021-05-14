package com.jesen.cod.testlottie

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    lateinit var mLottieAnim: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        mLottieAnim = findViewById(R.id.lottie_anim_view)
        mLottieAnim.addAnimatorUpdateListener {
            if (it.animatedFraction == 1.0f) {
                startMainPage()
            }
        }

        skipBtn.setOnClickListener {
            mLottieAnim.pauseAnimation()
            startMainPage()
        }

        //mLottieAnim.addAnimatorListener(LottieAnimListener())
    }

    private fun startMainPage() {
        spStartActivity<MainActivity>(this) {}
        finish()
    }

}

class LottieAnimListener : Animator.AnimatorListener {
    override fun onAnimationStart(animation: Animator?, isReverse: Boolean) {}
    override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {}
    override fun onAnimationRepeat(p0: Animator?) {}

    override fun onAnimationEnd(p0: Animator?) {
        Log.i("7777", "completed")
    }

    override fun onAnimationCancel(p0: Animator?) {}
    override fun onAnimationStart(p0: Animator?) {}

}
