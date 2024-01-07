package ru.easycode.zerotoheroandroidtdd.note.core

import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper

interface NoteLiveDataWrapper {
    fun update(value: String)

    class Base : LiveDataWrapper.Abstract<String>(), NoteLiveDataWrapper
}