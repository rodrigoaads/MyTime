package com.rodrigoaads.mytime.domain.usecase.impl

import com.rodrigoaads.mytime.data.AppRepository
import com.rodrigoaads.mytime.domain.entity.ActionState
import com.rodrigoaads.mytime.domain.entity.ItemModel
import com.rodrigoaads.mytime.domain.usecase.GetItemByIdUseCase

class GetItemByIdUseCaseImpl(
    private val appRepository: AppRepository
): GetItemByIdUseCase {
    override suspend fun invoke(id: String): ActionState<ItemModel> {
        return appRepository.getItemById(id)
    }
}