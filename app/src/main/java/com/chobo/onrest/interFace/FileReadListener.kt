package com.chobo.onrest.interFace

interface FileReadListener {
    fun onFileReadSuccess(fileLines: List<String>)
    fun onFileReadFailure(errorMessage: String)
}