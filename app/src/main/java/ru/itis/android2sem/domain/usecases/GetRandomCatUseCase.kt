package ru.itis.android2sem.domain.usecases

import ru.itis.android2sem.domain.repository.CataasRepository
import javax.inject.Inject

class GetRandomCatUseCase @Inject constructor(
    private val repository: CataasRepository
) {
    suspend operator fun invoke() = repository.getRandomCat()
}