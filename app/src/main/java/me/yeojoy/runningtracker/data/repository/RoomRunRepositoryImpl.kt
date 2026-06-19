package me.yeojoy.runningtracker.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.yeojoy.runningtracker.data.database.RunDao
import me.yeojoy.runningtracker.data.database.RunEntity
import me.yeojoy.runningtracker.domain.model.Run
import me.yeojoy.runningtracker.domain.repository.RunRepository

class RoomRunRepositoryImpl(
    private val runDao: RunDao
): RunRepository {

    override suspend fun insertRun(run: Run) {
        runDao.insertRun(RunEntity.fromRun(run))
    }

    override suspend fun deleteRun(run: Run) {
        runDao.deleteRun(RunEntity.fromRun(run))
    }

    override fun getAllRunsSortedByDate(): Flow<List<Run>> {
        return runDao.getAllRunsSortedByDate().map {
            it.map { entity ->
                entity.toRun()
            }
        }
    }

    override fun getAllRunsSortedByTimeInMillis(): Flow<List<Run>> {
        return runDao.getAllRunsSortedByTimeInMillis().map {
            it.map { entity ->
                entity.toRun()
            }
        }
    }

    override fun getAllRunsSortedByDistance(): Flow<List<Run>> {
        return runDao.getAllRunsSortedByDistance().map {
            it.map { entity ->
                entity.toRun()
            }
        }
    }

    override fun getAllRunsSortedByAvgSpeed(): Flow<List<Run>> {
        return runDao.getAllRunsSortedByAvgSpeed().map {
            it.map { entity ->
                entity.toRun()
            }
        }
    }
}