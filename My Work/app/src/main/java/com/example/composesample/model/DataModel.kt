package com.example.composesample.model

data class Place(
        var id: Int,
        var name: String,
        var address: String,
        var city: String,
        var rating: Float,
        var types: List<String>
)