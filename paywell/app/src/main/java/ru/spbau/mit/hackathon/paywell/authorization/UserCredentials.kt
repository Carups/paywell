package ru.spbau.mit.hackathon.paywell.authorization

data class UserCredentials(val email: String, val password: String, val loginByDefault: Boolean = false)