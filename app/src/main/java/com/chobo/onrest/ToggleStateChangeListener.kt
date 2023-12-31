package com.chobo.onrest

interface ToggleStateChangeListener {
    fun onToggleStateChanged(position: Int, isChecked: Boolean)
}
