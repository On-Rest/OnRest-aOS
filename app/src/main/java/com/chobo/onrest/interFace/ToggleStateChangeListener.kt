package com.chobo.onrest.interFace

interface ToggleStateChangeListener {
    fun onToggleStateChanged(mission: String, isChecked: Boolean, position: Int)
}
