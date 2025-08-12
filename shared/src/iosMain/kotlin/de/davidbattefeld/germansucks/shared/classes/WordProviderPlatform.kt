package de.davidbattefeld.germansucks.shared.classes

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.stringWithContentsOfFile

actual class WordProviderPlatform : WordProvider() {
    @OptIn(ExperimentalForeignApi::class)
    override fun getWords(): List<String> {
        val list = mutableListOf<String>()
        val bundle = NSBundle.mainBundle()
        val path = bundle.pathForResource(name = "output", ofType = "txt")

        val fileContent = path?.let {
            NSString.stringWithContentsOfFile(it, NSUTF8StringEncoding, null)
        } as String
        fileContent.lines().forEach {
            list.add(it)
        }
        return list
    }
}