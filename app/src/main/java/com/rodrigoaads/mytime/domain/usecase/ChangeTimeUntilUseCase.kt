package com.rodrigoaads.mytime.domain.usecase

import com.rodrigoaads.mytime.domain.entity.ActionState

interface ChangeTimeUntilUseCase {
    suspend operator fun invoke(
        id: String,
        timeUntil: String
    ): ActionState<Nothing>
}