package com.rudra.shiftmanagement.ui.fragments



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rudra.shiftmanagement.databinding.FragmentAddEditShiftBinding
import com.rudra.shiftmanagement.model.Shift
import com.rudra.shiftmanagement.viewmodel.ShiftViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddEditShiftFragment : Fragment() {
    private var _binding: FragmentAddEditShiftBinding? = null
    private val binding get() = _binding!!
    private lateinit var shiftViewModel: ShiftViewModel
    private var shiftId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEditShiftBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shiftViewModel = ViewModelProvider(this)[ShiftViewModel::class.java]

        arguments?.let {
            shiftId = it.getInt("shiftId", -1)
            if (shiftId != -1) {
                shiftViewModel.getShiftById(shiftId).observe(viewLifecycleOwner) { shift ->
                    shift?.let {
                        populateFields(it)
                    }
                }
            }
        }

        binding.btnSave.setOnClickListener {
            saveShift()
        }
    }

    private fun populateFields(shift: Shift) {
        binding.etEmployeeName.setText(shift.employeeName)
        binding.etShiftDate.setText(shift.shiftDate)
        binding.etStartTime.setText(shift.startTime)
        binding.etEndTime.setText(shift.endTime)
        binding.etPosition.setText(shift.position)
        binding.etNotes.setText(shift.notes)
    }

    private fun saveShift() {
        val employeeName = binding.etEmployeeName.text.toString()
        val shiftDate = binding.etShiftDate.text.toString()
        val startTime = binding.etStartTime.text.toString()
        val endTime = binding.etEndTime.text.toString()
        val position = binding.etPosition.text.toString()
        val notes = binding.etNotes.text.toString()

        if (employeeName.isBlank() || shiftDate.isBlank() || startTime.isBlank() || endTime.isBlank()) {
            // Show error
            return
        }

        val shift = Shift(
            id = shiftId,
            employeeName = employeeName,
            shiftDate = shiftDate,
            startTime = startTime,
            endTime = endTime,
            position = position,
            notes = notes
        )

        if (shiftId == -1) {
            shiftViewModel.insert(shift)
        } else {
            shiftViewModel.update(shift)
        }

        // Navigate back
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}