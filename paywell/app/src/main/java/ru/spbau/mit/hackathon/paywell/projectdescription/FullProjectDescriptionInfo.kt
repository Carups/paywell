package ru.spbau.mit.hackathon.paywell.projectdescription

import ru.spbau.mit.hackathon.paywell.projectslist.ProjectInfo

class FullProjectDescriptionInfo(override val projectName: String,
                                 override val projectOwner: String,
                                 override val projectShortDescription: String,
                                 val projectFullDescription: String): ProjectInfo(projectName, projectOwner, projectShortDescription)