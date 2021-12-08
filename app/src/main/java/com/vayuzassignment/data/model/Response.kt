package com.vayuzassignment.data.model

import java.io.Serializable


data class Response(
    val status: String,
    val message: String
) : Serializable