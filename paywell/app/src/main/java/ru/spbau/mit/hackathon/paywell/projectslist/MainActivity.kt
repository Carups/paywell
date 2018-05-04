package ru.spbau.mit.hackathon.paywell.projectslist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.json.JSONArray
import ru.spbau.mit.hackathon.paywell.R
import ru.spbau.mit.hackathon.paywell.server.AppClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.projectsRecyclerList)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm

        recyclerView.adapter = RecyclerAdapter(getRecordsFromJson(AppClient().execute().get()))
    }

    private fun getRecordsFromJson(jsonString: String): MutableList<ProjectInfo> {
        val projectsData = JSONArray(jsonString)
        val projectsList: MutableList<ProjectInfo> = mutableListOf()
        for (i in 0 until projectsData.length()) {
            val nextProject = projectsData.getJSONObject(i)
            projectsList.add(ProjectInfo(
                    nextProject.getString("name"),
                    nextProject.getString("owner"),
                    nextProject.getString("short_desc"),
                    nextProject.getString("full_desc")
            ))
        }
        return projectsList
    }
}
