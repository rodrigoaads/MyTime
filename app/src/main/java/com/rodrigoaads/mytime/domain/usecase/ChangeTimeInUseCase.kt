package com.rodrigoaads.mytime.domain.usecase

import com.rodrigoaads.mytime.domain.entity.ActionState

interface ChangeTimeInUseCase {
    suspend operator fun invoke(
        id: String,
        timeIn: String
    ): ActionState<Nothing>
}