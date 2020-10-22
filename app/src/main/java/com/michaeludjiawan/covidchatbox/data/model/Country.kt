package com.michaeludjiawan.covidchatbox.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country(
    @SerializedName("Country") val country: String,
    @SerializedName("CountryCode") @PrimaryKey val countryCode: String,
    @SerializedName("Slug") val slug: String,
    @Embedded val statusStatistic: StatusStatistic
)