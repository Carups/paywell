package ru.spbau.mit.hackathon.paywell.projectslist

import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import org.json.JSONObject
import ru.spbau.mit.hackathon.paywell.R
import ru.spbau.mit.hackathon.paywell.projectdescription.ProjectDescriptionActivity

class ProjectViewHolder(projectView: View): RecyclerView.ViewHolder(projectView) {

    val projectNameText: TextView = projectView.findViewById(R.id.projectName)
    val projectOwnerText: TextView = projectView.findViewById(R.id.ownerName)
    val projectShortDescription: TextView = projectView.findViewById(R.id.shortProjectDescription)
    val cardView: CardView = projectView as CardView

    init {
        cardView.setOnClickListener { v ->
            val intent: Intent = Intent(v.context, ProjectDescriptionActivity::class.java)
            intent.putExtra("project_info", stringifyPassingParameters())
            v.context.startActivity(intent) }
    }

    private fun stringifyPassingParameters(): String {
        val json: JSONObject = JSONObject()
        json.put("name", projectNameText.text)
        json.put("owner", projectOwnerText.text)
        json.put("short_desc", projectShortDescription.text)
        return json.toString()
    }
}