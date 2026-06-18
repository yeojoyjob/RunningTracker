package me.yeojoy.runningtracker.domain.use_case

import kotlinx.coroutines.flow.Flow
import me.yeojoy.runningtracker.domain.model.Run
import me.yeojoy.runningtracker.domain.model.SortType
import me.yeojoy.runningtracker.domain.repository.RunRepository

class GetRunsUseCase(
    private val repository: RunRepository
) {
    fun execute(sortType: SortType): Flow<List<Run>> {
        return when (sortType) {
            SortType.DATE -> repository.getAllRunsSortedByDate()
            SortType.RUNNING_TIME -> repository.getAllRunsSortedByTimeInMillis()
            SortType.DISTANCE -> repository.getAllRunsSortedByDistance()
            SortType.AVG_SPEED -> repository.getAllRunsSortedByAvgSpeed()
        }
    }
}