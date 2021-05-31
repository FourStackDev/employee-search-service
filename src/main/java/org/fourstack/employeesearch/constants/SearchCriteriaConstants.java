package org.fourstack.employeesearch.constants;

import org.fourstack.employeesearch.models.EmpSearchData;

/**
 * Class defined to maintain Constants related to Search Criteria.
 * <p>
 * Constants defined as SEARCH_CRITERIA from 1 to 29 are based upon the request
 * Payload {@link EmpSearchData} model.The binary strings of each
 * SEARCH_CRITERIA depends on the parameters (FirstName, LastName, MiddleName,
 * Role, Project Name, Department Name, Account Name, Location)
 * 
 * <p>
 * <i>SEARCH_CRITERIA_1 = "10000000" :- </i> Only First Name parameter
 * populated. <br />
 * <i>SEARCH_CRITERIA_28 = "11101000" :- </i>First Name, LastName, MiddleName
 * and Project Name are populated. <br />
 * </p>
 * 
 * @author Manjunath HM
 *
 */
public class SearchCriteriaConstants {
	public static final String SEARCH_CRITERIA_1 = "10000000";
	public static final String SEARCH_CRITERIA_2 = "01000000";
	public static final String SEARCH_CRITERIA_3 = "00100000";
	public static final String SEARCH_CRITERIA_4 = "11000000";
	public static final String SEARCH_CRITERIA_5 = "11100000";
	public static final String SEARCH_CRITERIA_6 = "00010000";
	public static final String SEARCH_CRITERIA_7 = "00001000";
	public static final String SEARCH_CRITERIA_8 = "00000100";
	public static final String SEARCH_CRITERIA_9 = "00000010";
	public static final String SEARCH_CRITERIA_10 = "00000001";
	public static final String SEARCH_CRITERIA_11 = "10010000";
	public static final String SEARCH_CRITERIA_12 = "10001000";
	public static final String SEARCH_CRITERIA_13 = "10000100";
	public static final String SEARCH_CRITERIA_14 = "10000010";
	public static final String SEARCH_CRITERIA_15 = "10000001";
	public static final String SEARCH_CRITERIA_16 = "01010000";
	public static final String SEARCH_CRITERIA_17 = "01001000";
	public static final String SEARCH_CRITERIA_18 = "01000100";
	public static final String SEARCH_CRITERIA_19 = "01000010";
	public static final String SEARCH_CRITERIA_20 = "01000001";
	public static final String SEARCH_CRITERIA_21 = "00011000";
	public static final String SEARCH_CRITERIA_22 = "00001001";
	public static final String SEARCH_CRITERIA_23 = "00001010";
	public static final String SEARCH_CRITERIA_24 = "00011010";
	public static final String SEARCH_CRITERIA_25 = "11010000";
	public static final String SEARCH_CRITERIA_26 = "11000010";
	public static final String SEARCH_CRITERIA_27 = "11110000";
	public static final String SEARCH_CRITERIA_28 = "11101000";
	public static final String SEARCH_CRITERIA_29 = "11111000";

}
