package ru.spbau.mit.hackathon.paywell.exception

class DataRetrievingException(override val message: String?, override val cause: Throwable?): Exception(message, cause)