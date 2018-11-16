package co.lucasc.hoster.ui.account


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.lucasc.hoster.R
import kotlinx.android.synthetic.main.fragment_account_first_action.*

class AccountSecondActionFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account_first_action, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        openSecondAccountAction.setOnClickListener {
            findNavController().navigate(R.id.openAccountThirdAction)
        }
    }

}
