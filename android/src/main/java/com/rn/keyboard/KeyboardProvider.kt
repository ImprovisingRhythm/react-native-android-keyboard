package com.rn.keyboard

import android.app.Activity
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.widget.PopupWindow
import kotlin.math.max

class KeyboardProvider(private val activity: Activity) : PopupWindow(activity) {
    private var previousKeyboardHeight = -1
    private var resizableView: View
    private var parentView: View? = null
    private var keyboardListeners = ArrayList<KeyboardListener>()

    init {
        contentView = View.inflate(activity, R.layout.keyboard_popup, null)
        resizableView = contentView.findViewById(R.id.keyResizeContainer)

        softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE or
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE

        inputMethodMode = INPUT_METHOD_NEEDED

        width = 0
        height = WindowManager.LayoutParams.MATCH_PARENT

        parentView = activity.window.decorView.rootView
        parentView?.post {
            resizableView.viewTreeObserver.addOnGlobalLayoutListener(getGlobalLayoutListener())

            if (!isShowing && parentView?.windowToken != null) {
                showAtLocation(parentView, Gravity.NO_GRAVITY, 0, 0)
            }
        }
    }

    fun addKeyboardListener(listener: KeyboardListener) {
        keyboardListeners.add(listener)
    }

    fun removeKeyboardListener() {
        keyboardListeners.clear()
    }

    private fun getGlobalLayoutListener() = ViewTreeObserver.OnGlobalLayoutListener {
        computeKeyboardState()
    }

    private fun computeKeyboardState() {
        val metrics = DisplayMetrics()
        val rect = Rect()

        activity.windowManager.defaultDisplay.getMetrics(metrics)
        resizableView.getWindowVisibleDisplayFrame(rect)

        val orientation = activity.resources.configuration.orientation
        val keyboardHeight = max(metrics.heightPixels - rect.bottom, 0)

        if (keyboardHeight != previousKeyboardHeight) {
            notifyKeyboardHeightChanged(keyboardHeight, orientation)
        }

        previousKeyboardHeight = keyboardHeight
    }

    private fun notifyKeyboardHeightChanged(height: Int, orientation: Int) {
        keyboardListeners.forEach {
            it.onHeightChanged(height)
        }
    }

    interface KeyboardListener {
        fun onHeightChanged(height: Int)
    }
}
