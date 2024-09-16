package com.simplemobiletools.calculator.helpers.converters

import com.simplemobiletools.calculator.R

object VolumeConverter : Converter {
    override val nameResId: Int = R.string.unit_volume
    override val imageResId: Int = R.drawable.ic_drop_vector
    override val key: String = "VolumeConverter"

    sealed class Unit(nameResId: Int, symbolResId: Int, factor: Double, key: String) : Converter.Unit(nameResId, symbolResId, factor, key) {
        data object CubicMeter : Unit(
            nameResId = R.string.unit_volume_cubic_meter,
            symbolResId = R.string.unit_volume_cubic_meter_symbol,
            factor = 1.0,
            key = "CubicMeter"
        )

        data object CubicDecimeter : Unit(
            nameResId = R.string.unit_volume_cubic_decimeter,
            symbolResId = R.string.unit_volume_cubic_decimeter_symbol,
            factor = 0.001,
            key = "CubicDecimeter"
        )

        data object CubicCentimeter : Unit(
            nameResId = R.string.unit_volume_cubic_centimeter,
            symbolResId = R.string.unit_volume_cubic_centimeter_symbol,
            factor = 0.000001,
            key = "CubicCentimeter"
        )

        data object CubicMillimeter : Unit(
            nameResId = R.string.unit_volume_cubic_millimeter,
            symbolResId = R.string.unit_volume_cubic_millimeter_symbol,
            factor = 0.000000001,
            key = "CubicMillimeter"
        )

        data object Liter : Unit(
            nameResId = R.string.unit_volume_liter,
            symbolResId = R.string.unit_volume_liter_symbol,
            factor = 0.001,
            key = "Liter"
        )

        data object Deciliter : Unit(
            nameResId = R.string.unit_volume_deciliter,
            symbolResId = R.string.unit_volume_deciliter_symbol,
            factor = 0.0001,
            key = "Deciliter"
        )

        data object Centiliter : Unit(
            nameResId = R.string.unit_volume_centiliter,
            symbolResId = R.string.unit_volume_centiliter_symbol,
            factor = 0.00001,
            key = "Centiliter"
        )
        
        data object Milliliter : Unit(
            nameResId = R.string.unit_volume_milliliter,
            symbolResId = R.string.unit_volume_milliliter_symbol,
            factor = 0.000001,
            key = "Milliliter"
        )

        data object AcreFoot : Unit(
            nameResId = R.string.unit_volume_acre_foot,
            symbolResId = R.string.unit_volume_acre_foot_symbol,
            factor = 1_233.48183754752,
            key = "AcreFoot"
        )

        data object CubicFoot : Unit(
            nameResId = R.string.unit_volume_cubic_foot,
            symbolResId = R.string.unit_volume_cubic_foot_symbol,
            factor = 0.028316846592,
            key = "CubicFoot"
        )

        data object CubicInch : Unit(
            nameResId = R.string.unit_volume_cubic_inch,
            symbolResId = R.string.unit_volume_cubic_inch_symbol,
            factor = 0.000016387064,
            key = "CubicInch"
        )

        data object BarrelUS : Unit(
            nameResId = R.string.unit_volume_barrel_us,
            symbolResId = R.string.unit_volume_barrel_us_symbol,
            factor = 0.119240471196,
            key = "BarrelUS"
        )

        data object GallonUS : Unit(
            nameResId = R.string.unit_volume_gallon_us,
            symbolResId = R.string.unit_volume_gallon_us_symbol,
            factor = 0.003785411784,
            key = "GallonUS"
        )

        data object QuartUS : Unit(
            nameResId = R.string.unit_volume_quart_us,
            symbolResId = R.string.unit_volume_quart_us_symbol,
            factor = 0.000946352946,
            key = "QuartUS"
        )

        data object PintUS : Unit(
            nameResId = R.string.unit_volume_pint_us,
            symbolResId = R.string.unit_volume_pint_us_symbol,
            factor = 0.000473176473,
            key = "PintUS"
        )

        data object GillUS : Unit(
            nameResId = R.string.unit_volume_gill_us,
            symbolResId = R.string.unit_volume_gill_us_symbol,
            factor = 0.00011829411825,
            key = "GillUS"
        )

        data object FluidOunceUS : Unit(
            nameResId = R.string.unit_volume_fluid_ounce_us,
            symbolResId = R.string.unit_volume_fluid_ounce_us_symbol,
            factor = 0.00003,
            key = "FluidOunceUS"
        )

        data object BarrelImperial : Unit(
            nameResId = R.string.unit_volume_barrel_imperial,
            symbolResId = R.string.unit_volume_barrel_imperial_symbol,
            factor = 0.16365924,
            key = "BarrelImperial"
        )

        data object GallonImperial : Unit(
            nameResId = R.string.unit_volume_gallon_imperial,
            symbolResId = R.string.unit_volume_gallon_imperial_symbol,
            factor = 0.00454609,
            key = "GallonImperial"
        )

        data object QuartImperial : Unit(
            nameResId = R.string.unit_volume_quart_imperial,
            symbolResId = R.string.unit_volume_quart_imperial_symbol,
            factor = 0.0011365225,
            key = "QuartImperial"
        )

        data object PintImperial : Unit(
            nameResId = R.string.unit_volume_pint_imperial,
            symbolResId = R.string.unit_volume_pint_imperial_symbol,
            factor = 0.00056826125,
            key = "PintImperial"
        )

        data object GillImperial : Unit(
            nameResId = R.string.unit_volume_gill_imperial,
            symbolResId = R.string.unit_volume_gill_imperial_symbol,
            factor = 0.0001420653125,
            key = "GillImperial"
        )

        data object FluidOunceImperial : Unit(
            nameResId = R.string.unit_volume_fluid_ounce_imperial,
            symbolResId = R.string.unit_volume_fluid_ounce_imperial_symbol,
            factor = 0.0000284130625,
            key = "FluidOunceImperial"
        )
    }

    override val units: List<Unit> = listOf(
        Unit.CubicMeter,
        Unit.CubicDecimeter,
        Unit.CubicCentimeter,
        Unit.CubicMillimeter,
        Unit.Liter,
        Unit.Centiliter,
        Unit.Deciliter,
        Unit.Milliliter,
        Unit.AcreFoot,
        Unit.CubicFoot,
        Unit.CubicInch,
        Unit.BarrelUS,
        Unit.GallonUS,
        Unit.QuartUS,
        Unit.PintUS,
        Unit.GillUS,
        Unit.FluidOunceUS,
        Unit.BarrelImperial,
        Unit.GallonImperial,
        Unit.QuartImperial,
        Unit.PintImperial,
        Unit.GillImperial,
        Unit.FluidOunceImperial,
    )

    override val defaultTopUnit: Unit = Unit.Liter
    override val defaultBottomUnit: Unit = Unit.CubicMeter
}
