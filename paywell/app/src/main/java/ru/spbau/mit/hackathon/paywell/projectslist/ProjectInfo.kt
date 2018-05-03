package ru.spbau.mit.hackathon.paywell.projectslist

open class ProjectInfo(open val projectName: String,
                       open val projectOwner: String,
                       open val projectShortDescription: String) {

    override fun toString(): String {
        return super.toString()
    }
}