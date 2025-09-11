package com.sv.youapp.app.test

class TextEngine {

    external fun processFrom(input: String): String

    companion object {
        init {
            System.loadLibrary("text_engine")
        }
    }
}