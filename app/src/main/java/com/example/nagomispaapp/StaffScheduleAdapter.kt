package com.example.nagomispaapp

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip

class StaffScheduleAdapter(
    private val staffList: List<StaffModel>
) : RecyclerView.Adapter<StaffScheduleAdapter.StaffViewHolder>() {

    class StaffViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val initials: TextView = itemView.findViewById(R.id.profileInitials)
        val name: TextView = itemView.findViewById(R.id.staffName)
        val role: TextView = itemView.findViewById(R.id.staffRole)
        val client: TextView = itemView.findViewById(R.id.clientName)

        val monday: TextView = itemView.findViewById(R.id.mondayHours)
        val tuesday: TextView = itemView.findViewById(R.id.tuesdayHours)
        val weekend: TextView = itemView.findViewById(R.id.weekendHours)

        val total: TextView = itemView.findViewById(R.id.totalHours)
        val statusChip: Chip = itemView.findViewById(R.id.statusChip)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_staff_schedule, parent, false)
        return StaffViewHolder(view)
    }

    override fun onBindViewHolder(holder: StaffViewHolder, position: Int) {
        val staff = staffList[position]

        holder.initials.text = staff.initials
        holder.name.text = staff.name
        holder.role.text = staff.role
        holder.client.text = "Client: ${staff.clientName}"

        holder.monday.text = staff.monday
        holder.tuesday.text = staff.tuesday
        holder.weekend.text = staff.weekend

        holder.total.text = "Total: ${staff.totalHours}"
        holder.statusChip.text = staff.status

        // Status chip color
        val color = when (staff.status) {
            "Active" -> "#4CAF50"
            "Inactive" -> "#9E9E9E"
            "On Leave" -> "#FF9800"
            else -> "#757575"
        }

        holder.statusChip.chipBackgroundColor =
            ColorStateList.valueOf(Color.parseColor(color))
    }

    override fun getItemCount(): Int = staffList.size
}
