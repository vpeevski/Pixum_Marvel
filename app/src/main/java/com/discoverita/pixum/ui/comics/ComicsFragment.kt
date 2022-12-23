package com.discoverita.pixum.ui.comics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class ComicsFragment : Fragment() {

    companion object {
        fun newInstance() = ComicsFragment()
    }

    private lateinit var viewModel: AllComicsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_comics, container, false)
        return ComposeView(requireContext()).apply {
            setContent {
                LazyColumn(modifier = Modifier.padding(20.dp)) {
                    
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AllComicsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}