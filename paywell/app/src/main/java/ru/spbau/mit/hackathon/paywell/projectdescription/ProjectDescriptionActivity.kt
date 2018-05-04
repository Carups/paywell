package ru.spbau.mit.hackathon.paywell.projectdescription

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
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
        projectFullDescription.text = paramsMap.getString("full_desc")

        val donate1Button: Button = findViewById(R.id.pay1)
        val donate2Button: Button = findViewById(R.id.pay2)
        val donate3Button: Button = findViewById(R.id.pay3)

        donate1Button.setOnClickListener { Snackbar.make(findViewById(R.id.descriptionLayout), "Paid ${donate1Button.text}", 1000).show() }
        donate2Button.setOnClickListener { Snackbar.make(findViewById(R.id.descriptionLayout), "Paid ${donate2Button.text}", 1000).show() }
        donate3Button.setOnClickListener { Snackbar.make(findViewById(R.id.descriptionLayout), "Paid ${donate3Button.text}", 1000).show() }
    }
}