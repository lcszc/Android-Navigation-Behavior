package co.lucasc.hoster.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import co.lucasc.hoster.R

class HomeSecondActionFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return TextView(requireContext()).apply {
            text = getString(R.string.text_second_action)
        }
    }
}
