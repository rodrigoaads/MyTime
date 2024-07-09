package com.rodrigoaads.mytime.domain.usecase.impl

import com.rodrigoaads.mytime.data.AppRepository
import com.rodrigoaads.mytime.domain.entity.ActionState
import com.rodrigoaads.mytime.domain.usecase.ChangeTimeUntilUseCase

class ChangeTimeUntilUseCaseImpl(
    private val appRepository: AppRepository
): ChangeTimeUntilUseCase {
    override suspend fun invoke(
        id: String,
        timeUntil: String
    ): ActionState<Nothing> {
        return  appRepository.changeTimeUntil(
            id = id,
            timeUntil = timeUntil
        )
    }
}