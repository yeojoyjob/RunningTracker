package me.yeojoy.runingtracker.domain.use_case

import me.yeojoy.runingtracker.domain.model.Run
import me.yeojoy.runingtracker.domain.repository.RunRepository

class SaveRunUseCase(
    private val repository: RunRepository
) {
    suspend fun execute(run: Run) {
        repository.insertRun(run)
    }
}