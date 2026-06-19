package me.yeojoy.runningtracker.data.database

import androidx.room.TypeConverter
import me.yeojoy.runningtracker.domain.model.LocationPoint
import org.json.JSONArray
import org.json.JSONObject

object Converters {

    private const val LATITUDE = "latitude"
    private const val LONGITUDE = "longitude"

    @TypeConverter // Define a converter for a room DB. SHOULD assign it in Database
    fun fromLocationPointList(values: List<LocationPoint>): String {
        val jsonArray = JSONArray()
        values.forEach { point ->
            val jsonObject = JSONObject()
            jsonObject.put(LATITUDE, point.latitude)
            jsonObject.put(LONGITUDE, point.longitude)

            jsonArray.put(jsonObject)
        }
        return jsonArray.toString()
    }

    @TypeConverter // Define a converter for a room DB. SHOULD assign it in Database
    fun toLocationPointList(jsonArrayString: String): List<LocationPoint> {
        val jsonArray = JSONArray(jsonArrayString)
        val list = mutableListOf<LocationPoint>()
        for (index in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(index)
            list.add(
                LocationPoint(
                    latitude = jsonObject.getDouble(LATITUDE),
                    longitude = jsonObject.getDouble(LONGITUDE)
                )
            )
        }
        return list.toList()
    }
}