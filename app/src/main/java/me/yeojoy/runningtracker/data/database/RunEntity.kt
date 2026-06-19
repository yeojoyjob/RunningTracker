package me.yeojoy.runningtracker.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.yeojoy.runningtracker.domain.model.LocationPoint
import me.yeojoy.runningtracker.domain.model.Run

@Entity(tableName = "runs")
data class RunEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val distanceInMeters: Double,
    val timeInMillis: Long,
    val timestamp: Long,
    val avgSpeedInKMH: Float,
    val pathPoints: List<LocationPoint>
) {
    companion object {
        fun fromRun(run: Run): RunEntity = RunEntity(
            id = run.id,
            distanceInMeters = run.distanceInMeters,
            timeInMillis = run.timeInMillis,
            timestamp = run.timestamp,
            avgSpeedInKMH = run.avgSpeedInKMH,
            pathPoints = run.pathPoints
        )
    }

    fun toRun(): Run = Run(
        id = id,
        distanceInMeters = distanceInMeters,
        timeInMillis = timeInMillis,
        timestamp = timestamp,
        avgSpeedInKMH = avgSpeedInKMH,
        pathPoints = pathPoints
    )

}