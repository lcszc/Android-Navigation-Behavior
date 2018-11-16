package co.lucasc.hoster.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.lucasc.hoster.R
import kotlinx.android.synthetic.main.fragment_settings_first_action.*

class FirstSettingsFragment : Fragment() {

    private val quantityOfLove by lazy {
        FirstSettingsFragmentArgs.fromBundle(arguments).idQuantityOfLove
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings_first_action, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
    }

    private fun setUpView() {
        firstActionData.text = getString(R.string.text_first_action_data, quantityOfLove)

        openSecondSettingsAction.setOnClickListener {
            findNavController().navigate(R.id.secondSettings)
        }
    }

}
