package com.manas.vibe.feature.auth.login.data

import com.google.i18n.phonenumbers.PhoneNumberUtil

class PhoneNumberValidator {

    private val phoneNumberUtil = PhoneNumberUtil.getInstance()

    fun validate(
        phoneNumber: String,
        regionCode: String
    ): Boolean {

        return try {

            val parsedNumber = phoneNumberUtil.parse(
                phoneNumber,
                regionCode
            )

            phoneNumberUtil.isValidNumberForRegion(
                parsedNumber,
                regionCode
            )

        } catch (e: Exception) {

            false
        }
    }
}