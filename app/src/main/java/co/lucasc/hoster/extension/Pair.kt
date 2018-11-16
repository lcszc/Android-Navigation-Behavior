package co.lucasc.hoster.extension

import androidx.fragment.app.Fragment

val Pair<Fragment, String?>.fragment: Fragment
    get() = first

val Pair<Fragment, String?>.tag: String?
    get() = second