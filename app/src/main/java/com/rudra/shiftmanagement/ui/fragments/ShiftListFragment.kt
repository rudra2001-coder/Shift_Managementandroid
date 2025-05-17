package com.rudra.shiftmanagement.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rudra.shiftmanagement.databinding.FragmentShiftListBinding
import com.rudra.shiftmanagement.ui.adapters.ShiftAdapter
import com.rudra.shiftmanagement.viewmodel.ShiftViewModel

class ShiftListFragment : Fragment() {
    private var _binding: FragmentShiftListBinding? = null
    private val binding get() = _binding!!
    private lateinit var shiftViewModel: ShiftViewModel
    private lateinit var shiftAdapter: ShiftAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShiftListBinding.inflate(inflater, container, false)
   return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shiftAdapter = ShiftAdapter()
        binding.recyclerView.apply {
            adapter = shiftAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        shiftViewModel = ViewModelProvider(this)[ShiftViewModel::class.java]
        shiftViewModel.allShifts.observe(viewLifecycleOwner) { shifts ->
            shifts?.let { shiftAdapter.submitList(it) }
        }

        binding.fabAddShift.setOnClickListener {
            // Navigate to AddEditShiftFragment
            // You can use Navigation Component or other navigation methods
          // findNavController().navigate(R.id.action_shiftListFragmentt)
            //
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}