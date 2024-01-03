package ru.easycode.zerotoheroandroidtdd.main

import ru.easycode.zerotoheroandroidtdd.core.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.Screen

interface Navigation {

    interface Update : LiveDataWrapper.Update<Screen>

    interface Read : LiveDataWrapper.Read<Screen>

    interface Mutable : Update, Read

    class Base : LiveDataWrapper.Abstract<Screen>(), Mutable
}

