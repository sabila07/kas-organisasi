package com.naveenapps.expensemanager.initializer

import android.content.Context
import androidx.startup.Initializer
import com.naveenapps.expensemanager.core.data.R as CategoryStringsR
import com.naveenapps.expensemanager.core.domain.usecase.account.AddAccountUseCase
import com.naveenapps.expensemanager.core.domain.usecase.account.GetAllAccountsUseCase
import com.naveenapps.expensemanager.core.domain.usecase.account.UpdateAccountUseCase
import com.naveenapps.expensemanager.core.domain.usecase.category.AddCategoryUseCase
import com.naveenapps.expensemanager.core.domain.usecase.category.GetAllCategoryUseCase
import com.naveenapps.expensemanager.core.domain.usecase.category.UpdateCategoryUseCase
import com.naveenapps.expensemanager.core.domain.usecase.settings.onboarding.GetPreloadStatusUseCase
import com.naveenapps.expensemanager.core.domain.usecase.settings.onboarding.SetPreloadStatusUseCase
import com.naveenapps.expensemanager.core.model.Account
import com.naveenapps.expensemanager.core.model.AccountType
import com.naveenapps.expensemanager.core.model.Category
import com.naveenapps.expensemanager.core.model.CategoryType
import com.naveenapps.expensemanager.core.model.StoredIcon
import java.util.Calendar
import java.util.Date
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.core.context.GlobalContext

val BASE_CATEGORY_LIST = listOf(
    Category(
        id = "1",
        name = "Pakaian",
        type = CategoryType.EXPENSE,
        storedIcon = StoredIcon(
            name = "apparel",
            backgroundColor = "#F44336",
        ),
        createdOn = Date(),
        updatedOn = Date(),
        titleResId = CategoryStringsR.string.category_clothing,
    ),
    Category(
        id = "2",
        name = "Hiburan",
        type = CategoryType.EXPENSE,
        storedIcon = StoredIcon(
            name = "sports_esports",
            backgroundColor = "#E91E63",
        ),
        createdOn = Date(),
        updatedOn = Date(),
        titleResId = CategoryStringsR.string.category_entertainment,
    ),
    Category(
        id = "3",
        name = "Makanan",
        type = CategoryType.EXPENSE,
        storedIcon = StoredIcon(
            name = "restaurant",
            backgroundColor = "#9C27B0",
        ),
        createdOn = Date(),
        updatedOn = Date(),
        titleResId = CategoryStringsR.string.category_food,
    ),
    Category(
        id = "4",
        name = "Kesehatan",
        type = CategoryType.EXPENSE,
        storedIcon = StoredIcon(
            name = "home_health",
            backgroundColor = "#673AB7",
        ),
        createdOn = Date(),
        updatedOn = Date(),
        titleResId = CategoryStringsR.string.category_health,
    ),
    Category(
        id = "5",
        name = "Rekreasi",
        type = CategoryType.EXPENSE,
        storedIcon = StoredIcon(
            name = "flights_and_hotels",
            backgroundColor = "#3F51B5",
        ),
        createdOn = Date(),
        updatedOn = Date(),
        titleResId = CategoryStringsR.string.category_leisure,
    ),
    Category(
        id = "6",
        name = "Belanja",
        type = CategoryType.EXPENSE,
        storedIcon = StoredIcon(
            name = "shopping_cart",
            backgroundColor = "#2196F3",
        ),
        createdOn = Date(),
        updatedOn = Date(),
        titleResId = CategoryStringsR.string.category_shopping,
    ),
    Category(
        id = "7",
        name = "Transportasi",
        type = CategoryType.EXPENSE,
        storedIcon = StoredIcon(
            name = "travel",
            backgroundColor = "#03A9F4",
        ),
        createdOn = Date(),
        updatedOn = Date(),
        titleResId = CategoryStringsR.string.category_transportation,
    ),
    Category(
        id = "8",
        name = "Utilitas",
        type = CategoryType.EXPENSE,
        storedIcon = StoredIcon(
            name = "other_admission",
            backgroundColor = "#00BCD4",
        ),
        createdOn = Date(),
        updatedOn = Date(),
        titleResId = CategoryStringsR.string.category_utilities,
    ),
    Category(
        id = "9",
        name = "Gaji",
        type = CategoryType.INCOME,
        storedIcon = StoredIcon(
            name = "savings",
            backgroundColor = "#4CAF50",
        ),
        createdOn = Date(),
        updatedOn = Date(),
        titleResId = CategoryStringsR.string.category_salary,
    ),
    Category(
        id = "10",
        name = "Hadiah",
        type = CategoryType.INCOME,
        storedIcon = StoredIcon(
            name = "featured_seasonal_and_gifts",
            backgroundColor = "#E65100",
        ),
        createdOn = Date(),
        updatedOn = Date(),
        titleResId = CategoryStringsR.string.category_gift,
    ),
    Category(
        id = "11",
        name = "Kupon",
        type = CategoryType.INCOME,
        storedIcon = StoredIcon(
            name = "redeem",
            backgroundColor = "#3E2723",
        ),
        createdOn = Date(),
        updatedOn = Date(),
        titleResId = CategoryStringsR.string.category_coupons,
    ),
)

val BASE_ACCOUNT_LIST = listOf(
    Account(
        "1",
        "Uang Tunai",
        AccountType.REGULAR,
        storedIcon = StoredIcon(
            name = "savings",
            backgroundColor = "#4CAF50",
        ),
        Calendar.getInstance().time,
        Calendar.getInstance().time,
    ),
    Account(
        "2",
        "Kartu Kredit",
        AccountType.CREDIT,
        storedIcon = StoredIcon(
            name = "credit_card",
            backgroundColor = "#4CAF50",
        ),
        Calendar.getInstance().time,
        Calendar.getInstance().time,
    ),
    Account(
        "3",
        "Rekening Bank",
        AccountType.REGULAR,
        storedIcon = StoredIcon(
            name = "account_balance",
            backgroundColor = "#4CAF50",
        ),
        Calendar.getInstance().time,
        Calendar.getInstance().time,
    ),
)

class PreloadDatabaseInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        val getPreloadStatusUseCase: GetPreloadStatusUseCase = GlobalContext.get().get()
        val setPreloadStatusUseCase: SetPreloadStatusUseCase = GlobalContext.get().get()
        val addAccountUseCase: AddAccountUseCase = GlobalContext.get().get()
        val getAllAccountsUseCase: GetAllAccountsUseCase = GlobalContext.get().get()
        val updateAccountUseCase: UpdateAccountUseCase = GlobalContext.get().get()
        val addCategoryUseCase: AddCategoryUseCase = GlobalContext.get().get()
        val getAllCategoryUseCase: GetAllCategoryUseCase = GlobalContext.get().get()
        val updateCategoryUseCase: UpdateCategoryUseCase = GlobalContext.get().get()

        CoroutineScope(SupervisorJob() + Dispatchers.Main).launch {
            val isPreloaded = getPreloadStatusUseCase.invoke()

            if (isPreloaded.not()) {
                BASE_CATEGORY_LIST.forEach { value ->
                    addCategoryUseCase.invoke(value)
                }

                BASE_ACCOUNT_LIST.forEach { value ->
                    addAccountUseCase.invoke(value)
                }

                setPreloadStatusUseCase.invoke(true)
            } else {
                // Translate only unchanged built-in seed names. This preserves custom names,
                // account balances, categories, and all existing transactions.
                val accountNameTranslations = mapOf(
                    "1" to ("Cash" to "Uang Tunai"),
                    "2" to ("Card-xxx" to "Kartu Kredit"),
                    "3" to ("Bank Account" to "Rekening Bank"),
                )
                val existingAccounts = getAllAccountsUseCase.invoke().first()
                existingAccounts.forEach { account ->
                    val translation = accountNameTranslations[account.id]
                    if (translation != null && account.name == translation.first) {
                        updateAccountUseCase.invoke(
                            account.copy(name = translation.second, updatedOn = Date()),
                        )
                    }
                }

                val categoryNameTranslations = mapOf(
                    "1" to ("Clothing" to "Pakaian"),
                    "2" to ("Entertainment" to "Hiburan"),
                    "3" to ("Food" to "Makanan"),
                    "4" to ("Health" to "Kesehatan"),
                    "5" to ("Leisure" to "Rekreasi"),
                    "6" to ("Shopping" to "Belanja"),
                    "7" to ("Transportation" to "Transportasi"),
                    "8" to ("Utilities" to "Utilitas"),
                    "9" to ("Salary" to "Gaji"),
                    "10" to ("Gift" to "Hadiah"),
                    "11" to ("Coupons" to "Kupon"),
                )
                val existingCategories = getAllCategoryUseCase.invoke().first()
                existingCategories.forEach { category ->
                    val translation = categoryNameTranslations[category.id]
                    val translatedNameAlreadyExists = existingCategories.any {
                        it.id != category.id && it.name.equals(translation?.second, ignoreCase = true)
                    }
                    if (
                        translation != null &&
                        category.name == translation.first &&
                        !translatedNameAlreadyExists
                    ) {
                        updateCategoryUseCase.invoke(
                            category.copy(name = translation.second, updatedOn = Date()),
                        )
                    }
                }
            }
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = listOf(
        KoinInitializer::class.java,
    )
}
