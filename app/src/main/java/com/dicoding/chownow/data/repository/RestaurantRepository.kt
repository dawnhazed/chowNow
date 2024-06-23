package com.dicoding.chownow.data.repository

import com.dicoding.chownow.data.local.dao.RestaurantDao
import com.dicoding.chownow.data.remote.retrofit.ApiService
import com.dicoding.chownow.data.local.entity.RestaurantEntity
import kotlinx.coroutines.flow.Flow

class RestaurantRepository private constructor(
    private val apiService: ApiService,
    private val restaurantDao: RestaurantDao
) {
    fun getRestaurantsByLocation(location: String): Flow<List<RestaurantEntity>> {
        return restaurantDao.getRestaurantsByLocation(location)
    }

    suspend fun insertAll(restaurants: List<RestaurantEntity>) {
        restaurantDao.insertAll(restaurants)
    }

    companion object {
        @Volatile
        private var instance: RestaurantRepository? = null

        fun getInstance(apiService: ApiService, restaurantDao: RestaurantDao): RestaurantRepository =
            instance ?: synchronized(this) {
                instance ?: RestaurantRepository(apiService, restaurantDao).also { instance = it }
            }
    }
}
