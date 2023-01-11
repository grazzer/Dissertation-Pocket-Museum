package com.example.nationalmuseum

import android.os.Parcel
import android.os.Parcelable

data class Artifact(

    val Cultures: String?,
    val discription: String?,
    val findCountry: String?,
    val id: Int,
    val name: String?,
    val productionDate: String?,
    val productionPlace: String?

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Cultures)
        parcel.writeString(discription)
        parcel.writeString(findCountry)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(productionDate)
        parcel.writeString(productionPlace)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Artifact> {
        override fun createFromParcel(parcel: Parcel): Artifact {
            return Artifact(parcel)
        }

        override fun newArray(size: Int): Array<Artifact?> {
            return arrayOfNulls(size)
        }
    }
}
