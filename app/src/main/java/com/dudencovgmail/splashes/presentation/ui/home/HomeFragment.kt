package com.dudencovgmail.splashes.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import com.dudencovgmail.splashes.R
import com.dudencovgmail.splashes.databinding.FragmentHomeBinding
import com.dudencovgmail.splashes.presentation.ui.Adapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    @Inject
    lateinit var adapter: Adapter

    private val viewModel: HomeViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(inflater).apply { binding = this; vm = viewModel }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rv.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.list.collect { adapter.list = it }
        }

        viewModel.getData()
    }
}