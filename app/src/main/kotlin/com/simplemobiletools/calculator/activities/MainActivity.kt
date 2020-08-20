package com.simplemobiletools.calculator.activities

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import com.simplemobiletools.calculator.R
import com.simplemobiletools.calculator.extensions.config
import com.simplemobiletools.calculator.extensions.updateViewColors
import com.simplemobiletools.calculator.helpers.*
import com.simplemobiletools.calculator.BuildConfig
import com.simplemobiletools.commons.extensions.*
import com.simplemobiletools.commons.helpers.LICENSE_AUTOFITTEXTVIEW
import com.simplemobiletools.commons.helpers.LICENSE_ESPRESSO
import com.simplemobiletools.commons.helpers.LICENSE_ROBOLECTRIC
import com.simplemobiletools.commons.models.FAQItem
import com.simplemobiletools.commons.models.Release
import kotlinx.android.synthetic.main.activity_main.*
import me.grantland.widget.AutofitHelper
import java.math.BigDecimal

class MainActivity : SimpleActivity(), Calculator {
    private var storedTextColor = 0
    private var vibrateOnButtonPress = true
    private var selectedOperation:String = ""

    lateinit var calc: CalculatorImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appLaunched(BuildConfig.APPLICATION_ID)

        calc = CalculatorImpl(this, applicationContext)

        btn_plus.setOnClickListener { handleOperatorClick(PLUS); checkHaptic(it) }
        btn_minus.setOnClickListener { handleOperatorClick(MINUS); checkHaptic(it) }
        btn_multiply.setOnClickListener { handleOperatorClick(MULTIPLY); checkHaptic(it) }
        btn_divide.setOnClickListener { handleOperatorClick(DIVIDE); checkHaptic(it) }
        btn_percent.setOnClickListener { handleOperatorClick(PERCENT); checkHaptic(it) }
        btn_power.setOnClickListener { handleOperatorClick(POWER); checkHaptic(it) }
        btn_root.setOnClickListener { calc.handleOperation(ROOT); checkHaptic(it) }

        btn_clear.setOnClickListener { calc.handleClear(); checkHaptic(it) }
        btn_clear.setOnLongClickListener { calc.handleReset(); true }

        getButtonIds().forEach {
            it.setOnClickListener { calc.numpadClicked(it.id); checkHaptic(it) }
        }

        btn_equals.setOnClickListener { calc.handleEquals(); checkHaptic(it) }
        formula.setOnLongClickListener { copyToClipboard(false) }
        result.setOnLongClickListener { copyToClipboard(true) }

        AutofitHelper.create(result)
        AutofitHelper.create(formula)
        storeStateVariables()
        updateViewColors(calculator_holder, config.textColor)
        checkWhatsNewDialog()
        checkAppOnSDCard()
    }

    override fun onResume() {
        super.onResume()
        if (storedTextColor != config.textColor) {
            updateViewColors(calculator_holder, config.textColor)
        }

        if (config.preventPhoneFromSleeping) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }

        vibrateOnButtonPress = config.vibrateOnButtonPress
    }

    override fun onPause() {
        super.onPause()
        storeStateVariables()
        if (config.preventPhoneFromSleeping) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> launchSettings()
            R.id.about -> launchAbout()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun storeStateVariables() {
        config.apply {
            storedTextColor = textColor
        }
    }

    private fun checkHaptic(view: View) {
        if (vibrateOnButtonPress) {
            view.performHapticFeedback()
        }
    }

    private fun launchSettings() {
        startActivity(Intent(applicationContext, SettingsActivity::class.java))
    }

    private fun launchAbout() {
        val licenses = LICENSE_AUTOFITTEXTVIEW or LICENSE_ROBOLECTRIC or LICENSE_ESPRESSO

        val faqItems = arrayListOf(
            FAQItem(R.string.faq_1_title, R.string.faq_1_text),
            FAQItem(R.string.faq_1_title_commons, R.string.faq_1_text_commons),
            FAQItem(R.string.faq_4_title_commons, R.string.faq_4_text_commons),
            FAQItem(R.string.faq_2_title_commons, R.string.faq_2_text_commons),
            FAQItem(R.string.faq_6_title_commons, R.string.faq_6_text_commons)
        )

        startAboutActivity(R.string.app_name, licenses, BuildConfig.VERSION_NAME, faqItems, true)
    }

    private fun getButtonIds() = arrayOf(btn_decimal, btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9)

    private fun copyToClipboard(copyResult: Boolean): Boolean {
        var value = formula.value
        if (copyResult) {
            value = result.value
        }

        return if (value.isEmpty()) {
            false
        } else {
            copyToClipboard(value)
            true
        }
    }

    override fun setValue(value: String, context: Context) {
        if (selectedOperation.isNotEmpty())
            removeSelectFromOperator(selectedOperation)
        result.text = value
    }

    private fun checkWhatsNewDialog() {
        arrayListOf<Release>().apply {
            add(Release(18, R.string.release_18))
            add(Release(28, R.string.release_28))
            checkWhatsNew(this, BuildConfig.VERSION_CODE)
        }
    }

    // used only by Robolectric
    override fun setValueBigDecimal(d: BigDecimal) {
        calc.setValue(Formatter.bigDecimalToString(d))
        calc.lastKey = DIGIT
    }

    override fun setFormula(value: String, context: Context) {
        formula.text = value
    }

    private fun handleOperatorClick(operation: String) {
        if (selectedOperation.isNotEmpty())
            removeSelectFromOperator(selectedOperation)
        selectedOperation = operation
        selectOperator(operation)
        calc.handleOperation(operation)
    }

    private fun selectOperator(operation: String) {
        when(operation) {
            PLUS -> btn_plus.typeface = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)
            MINUS -> btn_minus.typeface = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)
            MULTIPLY -> btn_multiply.typeface = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)
            DIVIDE -> btn_divide.typeface = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)
            POWER -> btn_power.typeface = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)
            PERCENT -> btn_percent.typeface = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)
        }
    }

    private fun removeSelectFromOperator(operation: String) {
        when(operation) {
            PLUS -> btn_plus.typeface = Typeface.DEFAULT
            MINUS -> btn_minus.typeface = Typeface.DEFAULT
            MULTIPLY -> btn_multiply.typeface = Typeface.DEFAULT
            DIVIDE -> btn_divide.typeface = Typeface.DEFAULT
            POWER -> btn_power.typeface = Typeface.DEFAULT
            PERCENT -> btn_percent.typeface = Typeface.DEFAULT
        }
    }
}
