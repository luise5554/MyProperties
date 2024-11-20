package com.example.myproperties.infrastructure.implementation.rules

import com.example.myproperties.domain.rules.NumberRuleValidatorInterface

class NumberRuleValidator: NumberRuleValidatorInterface {

    override fun isNumberOrEmpty(string: String):Boolean{
        if (string.isEmpty()){
            return true
        }

        val valueAsLong = string.toLongOrNull()
        return valueAsLong != null
    }

}