package github.challenge.gemography.util

import org.slf4j.LoggerFactory

fun loggerFor(java: Class<out Any>) = LoggerFactory.getLogger(java)!!