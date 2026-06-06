package me.yeojoy.runingtracker.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import me.yeojoy.runingtracker.domain.model.Run
import me.yeojoy.runingtracker.domain.repository.RunRepository

class MockRunRepositoryImpl : RunRepository {
    private val _runs = MutableStateFlow(
        listOf(
            Run(
                id = 1,
                distanceInMeters = 5000.0,
                timeInMillis = 1500000L,
                timestamp = System.currentTimeMillis(),
                avgSpeedInKMH = 12.0f
            ),
            Run(
                id = 2,
                distanceInMeters = 8000.0,
                timeInMillis = 2400000L,
                timestamp = System.currentTimeMillis(),
                avgSpeedInKMH = 13.0f
            ),
            Run(
                id = 3,
                distanceInMeters = 3000.0,
                timeInMillis = 1000000L,
                timestamp = System.currentTimeMillis(),
                avgSpeedInKMH = 8.0f
            ),
            Run(
                id = 4,
                distanceInMeters = 15000.0,
                timeInMillis = 8500000L,
                timestamp = System.currentTimeMillis(),
                avgSpeedInKMH = 9.0f
            ),

        )
    )

    override suspend fun insertRun(run: Run) {
        // TODO("Not yet implemented")
        // update: safely update _runs flow object
        // it + run object meaning create another list with "run"
        // copy() data class provides this function to make another object with id value
        _runs.update {
            it + run.copy(id = (it.maxByOrNull { r -> r.id ?: 0 }?.id ?: 0) + 1)
        }
    }

    override suspend fun deleteRun(run: Run) {
        _runs.update { it.filter { r -> r.id != run.id } }
    }

    override fun getAllRunsSortedByDate(): Flow<List<Run>> {
        return _runs.map { it.sortedByDescending { r -> r.timestamp } }
    }

    override fun getAllRunsSortedByTimeInMillis(): Flow<List<Run>> {
        return _runs.map { it.sortedByDescending { r -> r.timeInMillis } }
    }

    override fun getAllRunsSortedByDistance(): Flow<List<Run>> {
        return _runs.map { it.sortedByDescending { r -> r.distanceInMeters } }
    }

    override fun getAllRunsSortedByAvgSpeed(): Flow<List<Run>> {
        return _runs.map { it.sortedByDescending { r -> r.avgSpeedInKMH } }
    }
}