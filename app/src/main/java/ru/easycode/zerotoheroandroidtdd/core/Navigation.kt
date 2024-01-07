package ru.easycode.zerotoheroandroidtdd.core

interface Navigation {
    interface Update : LiveDataWrapper.Update<Screen>

    interface Read : LiveDataWrapper.Read<Screen>

    interface Mutable : Update, Read

    class Base : LiveDataWrapper.Abstract<Screen>(), Mutable
}