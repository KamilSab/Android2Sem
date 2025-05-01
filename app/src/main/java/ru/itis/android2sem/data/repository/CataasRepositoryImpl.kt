package ru.itis.android2sem.data.repository


import ru.itis.android2sem.data.remote.api.CataasApi
import ru.itis.android2sem.data.remote.dto.CatDto
import ru.itis.android2sem.domain.models.Cat
import ru.itis.android2sem.domain.repository.CataasRepository
import javax.inject.Inject

class CataasRepositoryImpl @Inject constructor(
    private val api: CataasApi
) : CataasRepository {

    override suspend fun getRandomCat(): Cat {
        return api.getRandomCat().toDomainModel()
    }

    override suspend fun getCatWithText(text: String): Cat {
        return api.getCatWithText(text).toDomainModel()
    }

    private fun CatDto.toDomainModel(): Cat {
        return Cat(
            imageUrl = "https://cataas.com$url"
        )
    }
}