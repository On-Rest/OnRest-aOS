package com.chobo.onrest

interface ToggleStateChangeListener {
    fun onToggleStateChanged(mission: String, isChecked: Boolean, position: Int)
}
