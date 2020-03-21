package com.example.weatherapp.core.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserInfoEntity")
data class UserInfoEntity(

    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,

    @SerializedName("lastName")
    @ColumnInfo(name = "lastName")
    var lastName: String
    ,
    @SerializedName("isSocial")
    @ColumnInfo(name = "isSocial")
    var isSocial: Boolean
    ,
    @SerializedName("profilePhoto")
    @ColumnInfo(name = "profilePhoto")
    var profilePhoto: String,

    @SerializedName("protectionType")
    @ColumnInfo(name = "protectionType")
    var protectionType: String,

    @SerializedName("profileThumbnail")
    @ColumnInfo(name = "profileThumbnail")
    var profileThumbnail: String,

    @SerializedName("mobile")
    @ColumnInfo(name = "mobile")
    var mobile: String,

    @SerializedName("isMobileVerified")
    @ColumnInfo(name = "isMobileVerified")
    var isMobileVerified: Boolean,

    @SerializedName("firstName")
    @ColumnInfo(name = "firstName")
    var firstName: String,

    @SerializedName("email")
    @ColumnInfo(name = "email")
    var email: String,

    @SerializedName("isEmailVerified")
    @ColumnInfo(name = "isEmailVerified")
    var isEmailVerified: Boolean,

    @SerializedName("Authentication")
    @ColumnInfo(name = "Authentication")
    var mAuthentication: String,

    @SerializedName("gender")
    @ColumnInfo(name = "gender")
    var gender: String,

    @SerializedName("birthdate")
    @ColumnInfo(name = "birthdate")
    var birthdate: String,

    @SerializedName("hasPassword")
    @ColumnInfo(name = "hasPassword")
    var hasPassword: Boolean,

    @SerializedName("referralCode")
    @ColumnInfo(name = "referalCode")
    var referalCode: String?,


    @SerializedName("numberOfNotUsedPromoCode")
    @ColumnInfo(name = "numberOfNotUsedPromoCode")
    var numberOfNotUsedPromoCode: Int?,

    @SerializedName("notificatios")
    @ColumnInfo(name = "notifications")
//    @TypeConverters(DBConverters::class)//TODO add this after type finalized
    var notifications: String?, // TODO change type

    @SerializedName("accountStatus")
    @ColumnInfo(name = "accountStatus")
    var accountStatus: String,

    @SerializedName("frozenDate")
    @ColumnInfo(name = "frozenDate")
    var frozenDate: String,

    @SerializedName("joinedDate")
    @ColumnInfo(name = "joinedDate")
    var joinedDate: String,

    @TypeConverters(DBConverters::class)
    @SerializedName("favoritesChannels")
    @ColumnInfo(name = "favoritesChannelsIDs")
    var favoritesChannelsIDs: List<String>?,

    @SerializedName("language")
    @ColumnInfo(name = "language")
    var language: String?
) {

    fun replaceUserInfoEntityNulls() {

        if (id == null) {
            id = ""
        }
        if (isEmailVerified == null) {
            isEmailVerified = false
        }
        if (email == null) {
            email = ""
        }
        if (isMobileVerified == null) {
            isEmailVerified = false
        }
        if (isSocial == null) {
            isSocial = false
        }

        if (birthdate == null) {
            birthdate = ""
        }
        if (firstName == null) {
            firstName = ""
        }
        if (lastName == null) {
            lastName = ""
        }
        if (gender == null) {
            gender = ""
        }
        if (mAuthentication == null) {
            mAuthentication = ""
        }

        if (accountStatus == null) {
            accountStatus = ""
        }

        if (frozenDate == null) {
            frozenDate = ""
        }

        if (joinedDate == null) {
            joinedDate = ""
        }

        if (profilePhoto == null) {
            profilePhoto = ""
        }

        if (profileThumbnail == null) {
            profileThumbnail = ""
        }

        if (protectionType == null) {
            protectionType = ""
        }

        if (mobile == null) {
            mobile = ""
        }
        if (numberOfNotUsedPromoCode == null) {
            numberOfNotUsedPromoCode = null
        }
        if (notifications == null) {
            notifications = null
        }

        if (favoritesChannelsIDs == null)
            favoritesChannelsIDs = listOf()
    }
}
