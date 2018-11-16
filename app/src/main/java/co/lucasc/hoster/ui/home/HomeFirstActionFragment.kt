package co.lucasc.hoster.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.lucasc.hoster.R
import kotlinx.android.synthetic.main.fragment_home_first_action.*
import android.widget.LinearLayout.LayoutParams as ViewParams

class HomeFirstActionFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_first_action, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openSecondAction.setOnClickListener(::onViewClick)
    }

    private fun onViewClick(view: View) {
        if (view.id == openSecondAction.id) {
            findNavController().navigate(R.id.openHomeSecondAction)
        }
    }
}
