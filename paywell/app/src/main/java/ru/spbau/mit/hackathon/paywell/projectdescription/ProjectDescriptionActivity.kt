package ru.spbau.mit.hackathon.paywell.projectdescription

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import org.json.JSONObject
import ru.spbau.mit.hackathon.paywell.R

class ProjectDescriptionActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.project_description)

        val projectName: TextView = findViewById(R.id.projectName)
        val projectOwner: TextView = findViewById(R.id.projectOwner)
        val projectShortDescription: TextView = findViewById(R.id.shortProjectDescription)
        val projectFullDescription: TextView = findViewById(R.id.fullProjectDescription)

        val paramsMap = JSONObject(intent.getStringExtra("project_info"))

        projectName.text = paramsMap.getString("name")
        projectOwner.text = paramsMap.getString("owner")
        projectShortDescription.text = paramsMap.getString("short_desc")
        projectFullDescription.text = "Full description"
    }
}