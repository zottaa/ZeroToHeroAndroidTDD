package ru.easycode.zerotoheroandroidtdd

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {
    fun show(supportFragmentManager: FragmentManager, containerId: Int)

    abstract class Replace(
        private val fragmentClass: Class<out Fragment>
    ) : Screen {
        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.beginTransaction()
                .replace(containerId, fragmentClass.getDeclaredConstructor().newInstance())
                .commit()
        }
    }

    abstract class Add(
        private val fragmentClass: Class<out Fragment>
    ) : Screen {
        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.beginTransaction()
                .add(containerId, fragmentClass.getDeclaredConstructor().newInstance())
                .addToBackStack(fragmentClass.name)
                .commit()
        }
    }

    object Pop : Screen {
        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.popBackStack()
        }
    }
}

object MainScreen : Screen.Replace(MainFragment::class.java)

object AddScreen : Screen.Add(AddFragment::class.java)