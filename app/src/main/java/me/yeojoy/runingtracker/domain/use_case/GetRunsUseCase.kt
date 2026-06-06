package me.yeojoy.runingtracker.domain.use_case

import kotlinx.coroutines.flow.Flow
import me.yeojoy.runingtracker.domain.model.Run
import me.yeojoy.runingtracker.domain.model.SortType
import me.yeojoy.runingtracker.domain.repository.RunRepository

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