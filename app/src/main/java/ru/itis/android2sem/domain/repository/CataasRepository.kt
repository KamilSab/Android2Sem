package ru.itis.android2sem.domain.repository

import ru.itis.android2sem.domain.models.Cat


interface CataasRepository {
    suspend fun getRandomCat(): Cat
    suspend fun getCatWithText(text: String): Cat
}