package com.discoverita.pixum.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {
    companion object MarvelApi {
        const val BASE_URL = "https://gateway.marvel.com/v1/public/"
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val PUBLIC_KEY = "aac9865447193706268f4e47da105c5e"
        private const val PRIVATE_KEY = "af1c08ea6994e3b02b06fc6b347bfcd3bd597c6a"

        fun hash(): String {
            val input = "$timeStamp$PRIVATE_KEY$PUBLIC_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }

    object AppModuleKey {
        const val API_KEY = "apikey"
        const val TIMESTAMP = "ts"
        const val HASH_KEY = "hash"
    }

    object CharacterConstant {
        const val NO_DESCRIPTION_AVAILABLE = "No Description Available"
        const val COMICS = "Comics:"
        const val SERIES = "Series:"
    }
}
