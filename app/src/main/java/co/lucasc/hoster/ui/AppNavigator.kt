package co.lucasc.hoster.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class AppNavigator(
        private val manager: FragmentManager
) {
    fun navigate(containerId: Int, fragment: Fragment, tag: String?) {
        val currentPrimaryFragment = manager.primaryNavigationFragment
        if (fragment == currentPrimaryFragment) return

        val transaction = manager.beginTransaction()
        currentPrimaryFragment?.run(transaction::hide)

        val addedFragment = manager.findFragmentByTag(tag)
        addedFragment?.let { addedFrag ->
            transaction.setUpAndShowFragment(addedFrag)
        } ?: run {
            transaction.add(containerId, fragment, tag)
            transaction.setUpAndShowFragment(fragment)
        }

        transaction.commit()
    }

    fun findCurrentPage(containerId: Int): Fragment? = manager.findFragmentById(containerId)

    private fun FragmentTransaction.setUpAndShowFragment(fragment: Fragment) {
        setPrimaryNavigationFragment(fragment)
        show(fragment)
    }
}