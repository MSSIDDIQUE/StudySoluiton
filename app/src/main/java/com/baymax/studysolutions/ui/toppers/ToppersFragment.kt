package com.baymax.studysolutions.ui.toppers


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.baymax.studysolutions.R
import com.baymax.studysolutions.data.Topper
import com.baymax.studysolutions.databinding.FragmentToppersBinding
import com.baymax.studysolutions.ui.adapter.ToppersAdapter
import kotlinx.android.synthetic.main.fragment_toppers.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext

class ToppersFragment : Fragment(), KodeinAware {


    override val kodeinContext = kcontext<Fragment>(this)
    override val kodein: Kodein by kodein()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter : ToppersAdapter

    private lateinit var viewModel: ToppersViewModel
    private val factory:ToppersViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentToppersBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_toppers, container,false)

        viewModel = ViewModelProviders.of(this, factory).get(ToppersViewModel::class.java)
        binding.viewmodel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_topper.setOnClickListener{
            val nextAction = ToppersFragmentDirections.nextAction()
            nextAction.setNumOfToppers(10)
            Navigation.findNavController(it).navigate(nextAction)
        }
        filter(ClassXIIth.rootView)
    }

    fun filter(view: View)
    {
        val standard:String

        if(view.equals(ClassXIIth))
        {
            standard = "XIIth"
        }
        else
        {
            standard = "Xth"
        }
        viewModel.getToppers().observe(this, Observer { toppers->
            adapter = ToppersAdapter(toppers as ArrayList<Topper>,standard)
            linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
            recycler_view.layoutManager = linearLayoutManager
            recycler_view.adapter = adapter
            Log.d("(Saquib)","adapter is set for standard "+standard)
        })
    }
}
