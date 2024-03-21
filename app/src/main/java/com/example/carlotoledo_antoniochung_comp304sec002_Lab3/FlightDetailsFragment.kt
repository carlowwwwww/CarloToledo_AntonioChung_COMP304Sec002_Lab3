package com.example.carlotoledo_antoniochung_comp304sec002_Lab3


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.databinding.FragmentFlightDetailsBinding
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.viewmodels.AirportScheduleViewModel
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.viewmodels.AirportScheduleViewModelFactory
import kotlinx.coroutines.launch

class FlightDetailsFragment: Fragment() {

    companion object {
        var AIRLINE = "airline"
    }

    private var _binding: FragmentFlightDetailsBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var airline: String

    private val viewModel: AirportScheduleViewModel by activityViewModels {
        AirportScheduleViewModelFactory(
            (activity?.application as AirportScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            airline = it.getString(AIRLINE).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFlightDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val airportScheduleAdapter = AirportScheduleListAdapter { _, _ -> }

        recyclerView.adapter = airportScheduleAdapter

        lifecycle.coroutineScope.launch {
            viewModel.scheduleForAirlineName(airline).collect() {
                airportScheduleAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
