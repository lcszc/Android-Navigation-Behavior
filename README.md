# Android Navigation
Since Android Team has released Jetpack, a lot of people started using the *Navigation* component, because of it easily usage and less management of fragment's back stack, because this is really something annoying to do.

However, I'm not here to teach you about this component, but to show you how to modify the behavior of him to achieve the same back stack management as **Youtube** and **Instagram**.

# Navigation Principles
According to the [navigation principles](https://material.io/design/navigation/understanding-navigation.html) for **Android**, the app must navigate to *top-level destinations* and any temporary screen should be reset when the user comes back to  the destination.

On **iOS** is different, every screen get their state saved. The applications mentioned above follow this pattern.

# The Component

Unfortunately this component doesn't support this type of behavior yet, but I think they will add it later. Remember that the library is yet in alpha. So, Youtube and Instagram is purely hand-made back stack management. I know that Instagram uses *ReactNative*.

Anyway, we can also apply the same pattern by making some simple management of navigation. I our scenario, we need to set up two things:

 - Make every item of bottom navigation a [NavHostFragment](https://developer.android.com/reference/androidx/navigation/fragment/NavHostFragment)
 - Handle fragment addition and replacement (no need to handle `onBackPress`)

Using NavHostFragment with BottomNavigationView
---

*Note: you can also do this using **TabLayout**.*

All we need to do here is to go back to the old times when we was using a `FrameLayout` to host ours `Fragment`. Something like the example below.

    <FrameLayout
		android:id="@+id/container"
		android:name="androidx.navigation.fragment.NavHostFragment"
		android:layout_width="0dp"
		android:layout_height="0dp"
		app:layout_constraintStart_toStartOf="parent"
		app:layout_constraintEnd_toEndOf="parent"
		app:layout_constraintTop_toTopOf="parent"
		app:layout_constraintBottom_toTopOf="@+id/bottomMenu" />
		
	<com.google.android.material.bottomnavigation.BottomNavigationView
		android:id="@+id/bottomMenu"
		android:layout_width="0dp"
		android:layout_height="wrap_content"
		android:layout_marginStart="0dp"
		android:layout_marginEnd="0dp"
		android:background="@color/colorWhite"
		app:layout_constraintBottom_toBottomOf="parent"
		app:layout_constraintLeft_toLeftOf="parent"
		app:layout_constraintRight_toRightOf="parent"
		app:elevation="8dp"
		app:menu="@menu/main_navigation" />

After that we will need to create the **graph** for every of our **menu**. In this case we have 3 items (Home, Account and Settings) and which one of them will have a **graph** that represents the flow of the screen. 

Every item should also have a TAG to be easily identified by the **FragmentManager**.

    companion object {
	    private const val ROOT_NAV_HOME = "ROOT-NAV-HOME"
		private const val ROOT_NAV_ACCOUNT = "ROOT-NAV-ACCOUNT"
		private const val ROOT_NAV_SETTINGS = "ROOT-NAV-SETTINGS"
	}

I call them by `ROOT-NAV` because they will host every fragment that we want to show in the flow.

	private val pages: List<Pair<Fragment, String?>> by lazy {
		listOf(
			NavHostFragment.create(R.navigation.home_nav_graph) to ROOT_NAV_HOME,
			NavHostFragment.create(R.navigation.account_nav_graph) to ROOT_NAV_ACCOUNT,
			NavHostFragment.create(R.navigation.settings_nav_graph) to ROOT_NAV_SETTINGS
		)
	}

Our set up is a bit different than default Android's Navigation. We'll not be linking our `BottomNavigationView` to the **NavHostFragment**. Instead, we will show or hide the specific **NavHostFragment** of the item selected.

	bottomMenu.setOnNavigationItemSelectedListener { item ->
		val page = pages[item.order]
		// the magic works here
		appNavigator.navigate(container.id, page.fragment, page.tag)
		true // return true to onNavigationItemSelectedListener
	}

And then finally...

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

Basically, when the user selects an item of the navigator, we tell the **FragmentManager** to hide the actual *NavHostFragment* and show the new one that the user wants to see. This is cool because what happens inside *NavHostFragment* we do not need to manage, so **actions** and **arguments** will works fine and also **onBackPress** will be managed by the **Navigation Component**.
