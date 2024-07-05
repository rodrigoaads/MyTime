package com.rodrigoaads.mytime.domain.usecase.impl

import com.rodrigoaads.mytime.data.AppRepository
import com.rodrigoaads.mytime.domain.entity.ActionState
import com.rodrigoaads.mytime.domain.usecase.DeleteItemUseCase

class DeleteItemUseCaseImpl(
    private val appRepository: AppRepository
): DeleteItemUseCase {
    override suspend fun invoke(id: String): ActionState<Nothing> {
        return appRepository.deleteItem(id)
    }
}