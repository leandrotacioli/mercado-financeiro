package com.leandrotacioli.mercadofinanceiro.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DoubleTransformation {

	/**
	 * Transforma um objeto <i>Double</i> em um objeto <i>String</i>.
	 * 
	 * @param value - Valor a ser transformado
	 * @param decimalPlaces - Quantidade de casas decimais
	 * 
	 * @return Ex: <i>120,63</i>
	 */
	public static String doubleToString(double value, int decimalPlaces) {
		DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
		decimalFormatSymbols.setGroupingSeparator('.');
		decimalFormatSymbols.setDecimalSeparator(',');

		DecimalFormat decimalFormat = new DecimalFormat(); 
		decimalFormat.setMinimumFractionDigits(decimalPlaces);
		decimalFormat.setMaximumFractionDigits(decimalPlaces);
		decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
		
		return decimalFormat.format(value);
	}
	
	/**
	 * Arredonda um objeto <i>Double</i> para a quantidade de casas decimais desejada.
	 *
	 * @param value - Valor a ser transformado
	 * @param decimalPlaces - Quantidade de casas decimais
	 * 
	 * @return valorArredondado
	 */
	public static double roundDouble(double value, int decimalPlaces) {
		return roundDouble(value, decimalPlaces, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * Arredonda um objeto <i>Double</i> para a quantidade de casas decimais desejada.
	 *
	 * @param value - Valor a ser transformado
	 * @param decimalPlaces - Quantidade de casas decimais
	 * @param roundingMode - Modo de Arredondamento
	 * 
	 * @return valorArredondado
	 */
	public static double roundDouble(double value, int decimalPlaces, int roundingMode) {
		return BigDecimal.valueOf(value).setScale(decimalPlaces, roundingMode).doubleValue();
	}

}