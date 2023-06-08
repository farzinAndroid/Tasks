package com.example.tasks.task2

import java.text.DecimalFormat

object DigitHelper {

    fun digitByLang(enString: String): String {
        var result = ""
        var fa = '۰'
        for (char in enString) {
            fa = char
            when (char) {
                '0' -> fa = '۰'
                '1' -> fa = '۱'
                '2' -> fa = '۲'
                '3' -> fa = '۳'
                '4' -> fa = '۴'
                '5' -> fa = '۵'
                '6' -> fa = '۶'
                '7' -> fa = '۷'
                '8' -> fa = '۸'
                '9' -> fa = '۹'
            }
            result = "${result}$fa"
        }
        return result
    }


    fun digitBySeparator(price: String): String {
        return DecimalFormat("###,###").format(Integer.valueOf(price))
    }

    fun digitByLangAndSeparator(price: String): String {
        val priceWithOutCommas = price.replace(",", "")
        val persianDigit = digitByLang(priceWithOutCommas)
        return digitBySeparator(persianDigit)
    }

}