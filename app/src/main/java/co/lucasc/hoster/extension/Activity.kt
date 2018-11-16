package co.lucasc.hoster.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

fun AppCompatActivity.fragmentTransaction(
        function: FragmentTransaction.() -> FragmentTransaction
) {
    supportFragmentManager.beginTransaction().function().commit()
}