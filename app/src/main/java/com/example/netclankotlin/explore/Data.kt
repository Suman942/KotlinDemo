package com.example.netclankotlin.explore

data class Data(
    val bio: Bio,
    val countryCode: String,
    val distanceAway: Int,
    val firstName: String,
    val gender: Int,
    val invStatus: Int,
    val isFromCloseContact: Boolean,
    val isVerified: Int,
    val lastName: String,
    val location: Location,
    val phone: Long,
    val phoneStatus: Int,
    val places: Places,
    val profession: Profession,
    val profilePicUrl: String,
    val profileScore: Int,
    val startup: Startup,
    val uid: String
)