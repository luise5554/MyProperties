package com.example.myproperties.infrastructure.di

import android.content.Context
import com.example.myproperties.domain.rules.NumberRuleValidatorInterface
import com.example.myproperties.domain.rules.PropertyInfoValidatorInterface
import com.example.myproperties.infrastructure.implementation.rules.NumberRuleValidator
import com.example.myproperties.infrastructure.implementation.rules.PropertyInfoValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RulesModule {

    @Provides
    @Singleton
    fun provideNumberRuleValidator(): NumberRuleValidatorInterface {
        return NumberRuleValidator()
    }

    @Provides
    @Singleton
    fun providePropertyInfoValidator(@ApplicationContext context: Context): PropertyInfoValidatorInterface {
        return PropertyInfoValidator(context)
    }

}