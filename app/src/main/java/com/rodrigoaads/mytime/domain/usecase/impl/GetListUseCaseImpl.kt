package com.rodrigoaads.mytime.domain.usecase.impl

import com.rodrigoaads.mytime.data.AppRepository
import com.rodrigoaads.mytime.domain.entity.ItemModel
import com.rodrigoaads.mytime.domain.usecase.GetListUseCase
import kotlinx.coroutines.flow.Flow

class GetListUseCaseImpl(
    private val appRepository: AppRepository
): GetListUseCase {
    override fun invoke(): Flow<List<ItemModel>> {
        return appRepository.getList()
    }
}