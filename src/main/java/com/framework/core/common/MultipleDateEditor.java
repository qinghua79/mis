package com.framework.core.common;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

import org.springframework.util.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;



/**
 * Property editor for java.util.Date, supporting multiple custom input formats and custom output formats. This class utilizes DateUtils and DateFormatUtils of Apache Commons Lang, and has been tested for version 2.4 but may work with older versions.
 * 
 * It should be noted that the date input formats should be inserted into the array based on the length of the string, longest to shortest.
 * 
 * This is not meant to be used as system PropertyEditor but rather as locale-specific date editor within custom controller code, parsing user-entered number strings into Date properties of beans and rendering them in the UI form.
 * 
 * In web MVC code, this editor will typically be registered with binder.registerCustomEditor calls in a custom initBinder method.
 * 
 * @see java.beans.PropertyEditorSupport
 * @see java.util.Date
 * @see org.apache.commons.lang.time.DateUtils
 * @see org.apache.commons.lang.time.DateFormatUtils
 */

public class MultipleDateEditor extends PropertyEditorSupport {

	/**
	 * The date format used to format a Date into a String
	 */
	public final static String DEFAULT_OUTPUT_FORMAT = "dd/mm/yyyy";
	/**
	 * The date formats used to parse a String into a Date
	 */
	public final static String[] DEFAULT_INPUT_FORMATS = { "dd/mm/yyyy hh:mm:ss", "dd-mm-yyyy hh:mm:ss", "dd/mm/yy hh:mm:ss", "dd-mm-yy hh:mm:ss", "dd/mm/yyyy", "dd-mm-yyyy", "dd/mm/yy", "dd-mm-yy" };
	/** The format used to convert a Date into a String */
	private String outputFormat;
	/** An array of date formats used to convert a String into a Date */
	private String[] inputFormats;
	/** Allow empty strings to be parsed instead of treated as null */
	private boolean allowEmpty;

	/**
	 * Create a new MultipleDateEditor instance using the default values
	 */
	public MultipleDateEditor() {
		outputFormat = MultipleDateEditor.DEFAULT_OUTPUT_FORMAT;
		inputFormats = MultipleDateEditor.DEFAULT_INPUT_FORMATS;
		allowEmpty = false;
	}

	/**
	 * Create a new MultipleDateEditor instance using the given date input and output formats.
	 * 
	 * The "allowEmpty" parameter states if an empty String should be allowed for parsing, i.e. get interpreted as null value. Otherwise, an IllegalArgumentException gets thrown in that case.
	 * 
	 * @param outputFormat
	 *            The format used to convert a Date into a String
	 * @param inputFormats
	 *            An array of date formats used to convert a String into a Date
	 */
	public MultipleDateEditor(String outputFormat, String[] inputFormats) {
		this.outputFormat = outputFormat;
		this.inputFormats = inputFormats;
		allowEmpty = false;
	}

	/**
	 * Create a new MultipleDateEditor instance using the given date input and output formats.
	 * 
	 * The "allowEmpty" parameter states if an empty String should be allowed for parsing, i.e. get interpreted as null value. Otherwise, an IllegalArgumentException gets thrown in that case.
	 * 
	 * @param outputFormat
	 *            The format used to convert a Date into a String
	 * @param inputFormats
	 *            An array of date formats used to convert a String into a Date
	 * @param allowEmpty
	 *            Allow empty strings to be parsed instead of treated as null
	 */
	public MultipleDateEditor(String outputFormat, String[] inputFormats, boolean allowEmpty) {
		this.outputFormat = outputFormat;
		this.inputFormats = inputFormats;
		this.allowEmpty = allowEmpty;
	}

	/**
	 * Format the Date as String, using the specified outputFormat.
	 * 
	 * If allowEmpty is true (default is false), and the Date is null, an empty String will be returned; otherwise it is possible that a NPE or other parsing exception will occur.
	 * 
	 * @return The string value of the Date
	 */
	@Override
	public String getAsText() {
		if (allowEmpty && getValue() == null) {
			return "";
		}

		return DateFormatUtils.format((Date) getValue(), outputFormat);
	}

	/**
	 * Parse the Date from the given text, using the first matching date format specified in the inputFormats array.
	 * 
	 * If no matching input format is found, an IllegalArgumentException is thrown.
	 * 
	 * If allowEmpty is true (default is false), and the String is null, the Date will be interpreted as null; otherwise an IllegalArgumentException is thrown.
	 * 
	 * @param text
	 *            the text to convert into a java.util.Date
	 * @throws IllegalArgumentException
	 *             thrown if no matching format is found
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			if (StringUtils.hasText(text)) {
				setValue(DateUtils.parseDate(text, inputFormats));
			} else {
				if (allowEmpty) {
					setValue(null);
				} else {
					throw new IllegalArgumentException("The text specified for parsing is null");
				}
			}
		} catch (ParseException ex) {
			throw new IllegalArgumentException("Could not parse text [" + text + "] into any available date input formats", ex);
		}
	}

	/**
	 * If allowEmpty is true (default is false), and the Date is null, an empty String will be returned; otherwise it is possible that a NPE or other parsing exception will occur.
	 * 
	 * If allowEmpty is true (default is false), and the String is null, the Date will be interpreted as null; otherwise an IllegalArgumentException is thrown.
	 * 
	 * @return whether empty strings are allowed
	 */
	public boolean isAllowEmpty() {
		return allowEmpty;
	}

	/**
	 * If allowEmpty is true (default is false), and the Date is null, an empty String will be returned; otherwise it is possible that a NPE or other parsing exception will occur.
	 * 
	 * If allowEmpty is true (default is false), and the String is null, the Date will be interpreted as null; otherwise an IllegalArgumentException is thrown.
	 * 
	 * @param allowEmpty
	 *            whether empty strings should be allowed
	 */
	public void setAllowEmpty(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}

	/**
	 * Get the date formats used to parse a String into a Date
	 * 
	 * @return The date formats used to parse a String into a Date
	 */
	public String[] getInputFormats() {
		return inputFormats;
	}

	/**
	 * Set the date formats used to parse a String into a Date. It should be noted that the date formats should be inserted into the array based on the length of the format, longest to shortest.
	 * 
	 * 
	 * @param inputFormats
	 *            The date formats used to parse a String into a Date
	 */
	public void setInputFormats(String[] inputFormats) {
		this.inputFormats = inputFormats;
	}

	/**
	 * Get the format used to convert a Date into a String
	 * 
	 * @return The format used to convert a Date into a String
	 */
	public String getOutputFormat() {
		return outputFormat;
	}

	/**
	 * Set the format used to convert a Date into a String
	 * 
	 * @param outputFormat
	 *            The format used to convert a Date into a String
	 */
	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}
}
