package com.baymax.studysolutions.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.baymax.studysolutions.R
import com.baymax.studysolutions.databinding.FragmentProfileBinding
import com.baymax.studysolutions.ui.auth.AuthListener
import com.baymax.studysolutions.ui.auth.AuthViewModel
import com.baymax.studysolutions.ui.auth.AuthViewModelFactory
import com.baymax.studysolutions.utils.startLoginActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.kcontext

class ProfileFragment : Fragment(), AuthListener, KodeinAware {

    private lateinit var viewModel: AuthViewModel


    override val kodeinContext = kcontext<Fragment>(this)
    override val kodein: Kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentProfileBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_profile, container,false)
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
        return binding.root
    }

    override fun onStarted() {
        Toast.makeText(context,"Logging you out ",Toast.LENGTH_SHORT)
        progressbar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        context?.startLoginActivity()
        Toast.makeText(context,"User is successfully logged out",Toast.LENGTH_LONG).show()
        progressbar.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        Toast.makeText(context,"Something went wrong, Unable to logout user",Toast.LENGTH_LONG).show()
        progressbar.visibility = View.GONE
    }


}
