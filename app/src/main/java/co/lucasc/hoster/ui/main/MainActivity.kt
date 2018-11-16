package co.lucasc.hoster.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import co.lucasc.hoster.R
import co.lucasc.hoster.extension.fragment
import co.lucasc.hoster.extension.tag
import co.lucasc.hoster.ui.AppNavigator
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener as BottomNavListener

class MainActivity : AppCompatActivity() {

    companion object {
        private const val ROOT_NAV_HOME = "ROOT-NAV-HOME"
        private const val ROOT_NAV_ACCOUNT = "ROOT-NAV-ACCOUNT"
        private const val ROOT_NAV_SETTINGS = "ROOT-NAV-SETTINGS"
    }

    private val pages: List<Pair<Fragment, String?>> by lazy {
        listOf(
                NavHostFragment.create(R.navigation.home_nav_graph) to ROOT_NAV_HOME,
                NavHostFragment.create(R.navigation.account_nav_graph) to ROOT_NAV_ACCOUNT,
                NavHostFragment.create(R.navigation.settings_nav_graph) to ROOT_NAV_SETTINGS
        )
    }

    private val appNavigator: AppNavigator by lazy {
        AppNavigator(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            // show main nav host fragment if it is first instance of main activity
            val page = pages[0]
            appNavigator.navigate(container.id, page.fragment, page.tag)
        }

        setUpNavigation()
    }

    private fun setUpNavigation() {
        bottomMenu.setOnNavigationItemSelectedListener { item ->
            val page = pages[item.order]
            appNavigator.navigate(container.id, page.fragment, page.tag)
            true
        }
    }
}
