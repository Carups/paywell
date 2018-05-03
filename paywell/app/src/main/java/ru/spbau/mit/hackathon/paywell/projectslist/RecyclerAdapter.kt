package ru.spbau.mit.hackathon.paywell.projectslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.spbau.mit.hackathon.paywell.R

class RecyclerAdapter(projects: List<ProjectInfo>) : RecyclerView.Adapter<ProjectViewHolder>() {

    private val projectsList: MutableList<ProjectInfo> = mutableListOf()

    init {
        projectsList.addAll(projects)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val itemView: View = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.card_view, parent, false)

        return ProjectViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return projectsList.size
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val projectInfo: ProjectInfo = projectsList[position]
        holder.projectNameText.text = projectInfo.projectName
        holder.projectOwnerText.text = projectInfo.projectOwner
        holder.projectShortDescription.text = projectInfo.projectShortDescription
    }
}