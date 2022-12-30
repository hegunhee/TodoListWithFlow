package com.hegunhee.todolistwithflow.domain

import com.hegunhee.todolistwithflow.model.Repository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class DeleteAllMemoUseCase @Inject constructor(private val repository: Repository) : UseCase {

    suspend operator fun invoke(){
        repository.deleteAll()
    }
}