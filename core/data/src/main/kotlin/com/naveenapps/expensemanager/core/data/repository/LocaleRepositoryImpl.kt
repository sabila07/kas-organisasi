package com.naveenapps.expensemanager.core.data.repository

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.naveenapps.expensemanager.core.common.utils.AppCoroutineDispatchers
import com.naveenapps.expensemanager.core.data.R
import com.naveenapps.expensemanager.core.datastore.LocaleDataStore
import com.naveenapps.expensemanager.core.model.AppLocale
import com.naveenapps.expensemanager.core.repository.LocaleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

const val DEFAULT_APP_LOCALE_TAG = "id"

val defaultLocale = AppLocale(
    DEFAULT_APP_LOCALE_TAG,
    R.string.language_indonesian,
)

class LocaleRepositoryImpl(
    private val dataStore: LocaleDataStore,
    private val dispatchers: AppCoroutineDispatchers,
) : LocaleRepository {

    override suspend fun saveLocale(locale: AppLocale): Boolean = withContext(dispatchers.main) {
        applyLocaleTag(DEFAULT_APP_LOCALE_TAG)
        withContext(dispatchers.io) {
            dataStore.setLocaleTag(DEFAULT_APP_LOCALE_TAG)
        }
        true
    }

    override suspend fun applyLocale() = withContext(dispatchers.io) {
        dataStore.setLocaleTag(DEFAULT_APP_LOCALE_TAG)
        withContext(dispatchers.main) {
            applyLocaleTag(DEFAULT_APP_LOCALE_TAG)
        }
    }

    private fun applyLocaleTag(tag: String) {
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(tag),
        )
    }

    override fun getSelectedLocale(): Flow<AppLocale> = dataStore.getLocaleTag(DEFAULT_APP_LOCALE_TAG).map {
        defaultLocale
    }

    override fun getLocales(): List<AppLocale> = listOf(defaultLocale)
}
