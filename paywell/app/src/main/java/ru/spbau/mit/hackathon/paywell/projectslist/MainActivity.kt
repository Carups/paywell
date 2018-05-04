package ru.spbau.mit.hackathon.paywell.projectslist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.json.JSONArray
import ru.spbau.mit.hackathon.paywell.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.projectsRecyclerList)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm

        recyclerView.adapter = RecyclerAdapter(getRecordsFromJson(jsonString = json))
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

    private val json: String = "[\n" +
            "  {\n" +
            "    \"name\": \"Поддержи челядь!\",\n" +
            "    \"owner\": \"Фонд защиты начинающих блоггеров\",\n" +
            "    \"short_desc\": \"Поможем начинающим блоггерам стать опытными блоггерами\",\n" +
            "    \"full_desc\": \"Проект направлен на спонсирование молодых блоггеров с интересными и необычными идеями и концептами\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Фильм \\\"Красный маркер\\\"\",\n" +
            "    \"owner\": \"Фонд Кино\",\n" +
            "    \"short_desc\": \"Собираем деньги на новую ленту про любовь, про людей, про нас с вами\",\n" +
            "    \"full_desc\": \"Само название главного бестселлера 2021 года говорит о том, что каждый человек, которому небезразличны такие понятия, как любовь, честь и совесть, должен помочь развитию проекта\"\n" +
            "  }\n" +
            "]\n"
}
