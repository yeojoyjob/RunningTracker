package me.yeojoy.runningtracker.domain.use_case

import me.yeojoy.runningtracker.domain.model.Run
import me.yeojoy.runningtracker.domain.repository.RunRepository

class SaveRunUseCase(
    private val repository: RunRepository
) {
    suspend fun execute(run: Run) {
        repository.insertRun(run)
    }
}