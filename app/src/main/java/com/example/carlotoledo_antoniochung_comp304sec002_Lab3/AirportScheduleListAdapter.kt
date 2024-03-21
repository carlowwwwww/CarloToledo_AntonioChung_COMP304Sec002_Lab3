package com.example.carlotoledo_antoniochung_comp304sec002_Lab3

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.database.schedule.Flights
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.databinding.FlightDetailsItemBinding
import com.example.carlotoledo_antoniochung_comp304sec002_Lab3.databinding.FlightInfoItemBinding


class AirportScheduleListAdapter(
    private val onItemClicked: (Flights, AirportViewHolder) -> Unit) :
    ListAdapter<Flights, AirportScheduleListAdapter.AirportViewHolder>(DiffCallback) {
    companion object {
        var currentView = 0

        private val DiffCallback = object : DiffUtil.ItemCallback<Flights>() {
            override fun areItemsTheSame(oldItem: Flights, newItem: Flights): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Flights, newItem: Flights): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirportViewHolder {

        // Inflate the layout for the view holder
        val inflater = LayoutInflater.from(parent.context)
        var binding: ViewBinding

        if (currentView == 1) {
            binding = FlightDetailsItemBinding.inflate(inflater, parent, false)
        } else {
            binding = FlightInfoItemBinding.inflate(inflater, parent, false)
        }

        // Create a view holder with the inflated binding
        val viewHolder = AirportViewHolder(binding)

        // Set click listener for the view holder's itemView
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            currentView = 1
            onItemClicked(getItem(position), viewHolder)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: AirportViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AirportViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(flights: Flights) {
            when (binding) {
                is FlightInfoItemBinding -> {
                    binding.airlineTextView.text = flights.airline
                    binding.arrivalTimeTextView.text = flights.arrivalTime
                    binding.terminalTextView.text = flights.terminal
                }
                is FlightDetailsItemBinding -> {
                    binding.airlineTextView.text = flights.airline
                    binding.arrivalTimeTextView.text = flights.arrivalTime
                    binding.terminalTextView.text = flights.terminal
                    binding.statusTextView.text = flights.status

                    if (binding.statusTextView.text == "On Time") {
                        // If using ViewBinding
                        binding.statusTextView.setTextColor(Color.parseColor("#00FF00"))
                    } else {
                        binding.statusTextView.setTextColor(Color.parseColor("#FF0000"))
                    }
                }
            }
        }
    }

}
