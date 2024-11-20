package com.example.myproperties.domain.rules

interface NumberRuleValidatorInterface {
    fun isNumberOrEmpty(string: String):Boolean
}