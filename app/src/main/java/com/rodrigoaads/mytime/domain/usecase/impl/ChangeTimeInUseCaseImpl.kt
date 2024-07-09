package com.rodrigoaads.mytime.domain.usecase.impl

import com.rodrigoaads.mytime.data.AppRepository
import com.rodrigoaads.mytime.domain.entity.ActionState
import com.rodrigoaads.mytime.domain.usecase.ChangeTimeInUseCase

class ChangeTimeInUseCaseImpl(
    private val appRepository: AppRepository
): ChangeTimeInUseCase {
    override suspend fun invoke(
        id: String,
        timeIn: String
    ): ActionState<Nothing> {
        return appRepository.changeTimeIn(
            id = id,
            timeIn = timeIn
        )
    }
}