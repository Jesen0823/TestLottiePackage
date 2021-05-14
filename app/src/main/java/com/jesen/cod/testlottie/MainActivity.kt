package com.jesen.cod.testlottie

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity(), ItemCallBack {
    var jsPaths: MutableList<String> = ArrayList()
    lateinit var mLottieView: LottieAnimationView
    lateinit var mDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (jsPaths.isEmpty()) {
            Thread {
                jsPaths = AssertUtil.getAssetPath(this) as MutableList<String>
            }.start()
        }

        choiceBtn.setOnClickListener {
            showSelectDialog()
        }
        mLottieView = findViewById(R.id.lottie_view)
    }

    private fun getPeekHeight(): Int {
        val peekHeight = resources.displayMetrics.heightPixels;
        return peekHeight - peekHeight / 3
    }

    private fun getWindowHeight(): Int {
        return resources.displayMetrics.heightPixels
    }

    private fun showSelectDialog() {
        val view = View.inflate(this, R.layout.dialog_bottomsheet, null)
        val dialogRecycler = view.findViewById<RecyclerView>(R.id.dialog_recycler)
        dialogRecycler.layoutManager = LinearLayoutManager(this)
        val sourceAdapter = SourceAdapter(this, jsPaths, R.layout.item_source)
        dialogRecycler.adapter = sourceAdapter
        val recycleViewDivider = RecycleViewDivider(
            this,
            LinearLayoutManager.HORIZONTAL, 2, Color.BLACK
        )
        dialogRecycler.addItemDecoration(recycleViewDivider)
        //dialogRecycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        dialogRecycler.itemAnimator = DefaultItemAnimator()

        mDialog = BottomSheetDialog(this, R.style.Theme_AppCompat_Dialog)
        mDialog.setContentView(view)

        val dialogBehavior = BottomSheetBehavior.from(view.parent as View)
        //dialogBehavior.peekHeight = getPeekHeight()
        dialogBehavior.peekHeight = getWindowHeight()
        dialogBehavior.addBottomSheetCallback(SheetCallback(dialogBehavior, mDialog))
        mDialog.show()
    }

    class SheetCallback(
        private val dialogBehavior: BottomSheetBehavior<View>,
        private val bottomSheetDialog: BottomSheetDialog
    ) : BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            Log.i("SheetCallback", " slideOffset = $slideOffset")
        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                bottomSheetDialog.dismiss()
                dialogBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

    }

    override fun onClick(name: String) {
        Log.i("MainActivity", "onClick, name ：$name")
        mDialog.dismiss()
        mLottieView.setAnimation(name)
        mLottieView.loop(true)
        mLottieView.playAnimation()

    }

}



























