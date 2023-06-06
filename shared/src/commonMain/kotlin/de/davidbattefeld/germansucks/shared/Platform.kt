package de.davidbattefeld.germansucks.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform