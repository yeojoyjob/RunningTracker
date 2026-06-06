package me.yeojoy.runingtracker.domain.repository

import kotlinx.coroutines.flow.Flow
import me.yeojoy.runingtracker.domain.model.Run

/**
 * Repository를 통한 기능 정의.
 * UseCase에서 실체화
 */
interface RunRepository {
    suspend fun insertRun(run: Run)
    suspend fun deleteRun(run: Run)

    fun getAllRunsSortedByDate(): Flow<List<Run>>
    fun getAllRunsSortedByTimeInMillis(): Flow<List<Run>>
    fun getAllRunsSortedByDistance(): Flow<List<Run>>
    fun getAllRunsSortedByAvgSpeed(): Flow<List<Run>>
}