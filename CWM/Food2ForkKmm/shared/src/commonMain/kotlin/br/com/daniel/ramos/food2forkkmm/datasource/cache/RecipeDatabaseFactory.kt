package br.com.daniel.ramos.food2forkkmm.datasource.cache

import com.squareup.sqldelight.db.SqlDriver

class RecipeDatabaseFactory(private val driverFactory: DriverFactory) {
    fun createDatabase():RecipeDatabase = RecipeDatabase(driverFactory.createDriver())
}

expect class DriverFactory {
    fun createDriver():SqlDriver
}