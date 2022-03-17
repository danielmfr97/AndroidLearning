package br.com.daniel.ramos.learningjetpackcompose.domain.util

interface DomainMapper<T , DomainModel> {

    fun mapToDomainModel(model: T): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): T
}