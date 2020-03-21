package com.example.weatherapp.core.data.local.db

import androidx.room.*


@Dao
interface UserInfoDao {

    @Query("select COUNT(*) FROM  UserInfoEntity")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userInfo: UserInfoEntity)


    @Query("select * from UserInfoEntity LIMIT 1")
    fun getUserInfo(): UserInfoEntity

    @Query("delete from UserInfoEntity")
    fun deleteAllUsers()

    @Transaction
    fun refreshUsers(user: UserInfoEntity) {
        deleteAllUsers()
        insertUser(user)
    }
}