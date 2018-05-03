package ru.spbau.mit.hackathon.paywell.projectslist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import ru.spbau.mit.hackathon.paywell.R
import ru.spbau.mit.hackathon.paywell.projectdescription.ProjectDescriptionActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.projectsRecyclerList)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm

        recyclerView.adapter = RecyclerAdapter(generateRecords())
//        val projectCard = findViewById<View>(R.id.projectCard)
//        projectCard.setOnClickListener { v -> v.context.startActivity(Intent(this, ProjectDescriptionActivity::class.java)) }
    }

    private fun generateRecords(): MutableList<ProjectInfo> {
        return mutableListOf(
                ProjectInfo("Поддержка telegram-а", "Фонд защиты начинающих блоггеров", "Поможем начинающим блоггерам стать опытными блоггерами"),
                ProjectInfo("Фильм \"Красный маркер\"", "Фонд Кино", "Собираем деньги на новую ленту про любовь, про людей, про нас с вами")
        )
    }
}
