package ru.itis.android2sem.domain.usecases

import ru.itis.android2sem.domain.repository.CataasRepository
import javax.inject.Inject

class GetCatWithTextUseCase @Inject constructor(
    private val repository: CataasRepository
) {
    suspend operator fun invoke(text: String) = repository.getCatWithText(text)
}