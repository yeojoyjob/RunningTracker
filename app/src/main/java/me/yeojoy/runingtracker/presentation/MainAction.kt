package me.yeojoy.runingtracker.presentation

import me.yeojoy.runingtracker.domain.model.Run
import me.yeojoy.runingtracker.domain.model.SortType

/**
 * Define actions that a user can do
 */
sealed interface MainAction {
    data object ClickRun: MainAction // If it needs a data, change object to class
    data object FinishRun: MainAction

    data class DeleteRun(val run: Run): MainAction // It needs a run data
    data class ChangeSortType(val sortType: SortType): MainAction
    data class SelectRun(val run: Run): MainAction
    data object ToggleGpsStatus: MainAction
}