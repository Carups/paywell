package ru.spbau.mit.hackathon.paywell.projectslist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.json.JSONArray
import ru.spbau.mit.hackathon.paywell.R
import ru.spbau.mit.hackathon.paywell.exception.DataRetrievingException
import ru.spbau.mit.hackathon.paywell.server.AppClient
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val swipeRefresh: SwipeRefreshLayout = findViewById(R.id.projectsRefreshLayout)
        val recyclerView: RecyclerView = findViewById(R.id.projectsRecyclerList)

        swipeRefresh.setOnRefreshListener {
            fillProjectsToRecyclerView(recyclerView)
        }

        fillProjectsToRecyclerView(recyclerView)
    }

    private fun fillProjectsToRecyclerView(recyclerView: RecyclerView) {
        try {
            val llm = LinearLayoutManager(this)
            llm.orientation = LinearLayoutManager.VERTICAL
            recyclerView.layoutManager = llm
            recyclerView.adapter = RecyclerAdapter(getRecordsFromJson(receiveProjectsInformation()))
        } catch (_: DataRetrievingException) {
            sendNotificationToApp("Не получилось загрузить проекты", 10000)
        }
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

    private fun receiveProjectsInformation(): String {
        var result: String = AppClient().execute().get()
        if (result == getString(R.string.read_from_server_err_code)) {
            sendNotificationToApp("Не удается подключиться к серверу", 5000)
            val directory = applicationContext.filesDir
            val file = File(directory, getString(R.string.projects_saved_file))
            try {
                result = file.readText()
            } catch (e: IOException) {
                throw DataRetrievingException(e.message, e.cause)
            }
        } else {
            val directory = applicationContext.filesDir
            val file = File(directory, getString(R.string.projects_saved_file))
            file.writeText(result)
        }
        return result
    }

    private fun sendNotificationToApp(text: String, durationInMilliseconds: Int) {
        Snackbar.make(findViewById(R.id.projectsListLayout), text, durationInMilliseconds).show()
    }
}
