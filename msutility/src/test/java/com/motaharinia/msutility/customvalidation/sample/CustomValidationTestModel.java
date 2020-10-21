package com.motaharinia.msutility.customvalidation.sample;

import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.msutility.customvalidation.companyphone.CompanyPhone;
import com.motaharinia.msutility.customvalidation.daterange.DateRange;
import com.motaharinia.msutility.customvalidation.decimalcount.DecimalCount;
import com.motaharinia.msutility.customvalidation.doublerange.DoubleRange;
import com.motaharinia.msutility.customvalidation.email.Email;
import com.motaharinia.msutility.customvalidation.integerrange.IntegerRange;
import com.motaharinia.msutility.customvalidation.latincharacters.LatinCharacters;
import com.motaharinia.msutility.customvalidation.listlength.ListLength;
import com.motaharinia.msutility.customvalidation.listnoduplicate.ListNoDuplicate;
import com.motaharinia.msutility.customvalidation.listnoduplicatebyfields.ListNoDuplicateByFields;
import com.motaharinia.msutility.customvalidation.mobile.Mobile;
import com.motaharinia.msutility.customvalidation.nationalcode.NationalCode;
import com.motaharinia.msutility.customvalidation.organizationeconomiccode.OrganizationEconomicCode;
import com.motaharinia.msutility.customvalidation.organizationnationalcode.OrganizationNationalCode;
import com.motaharinia.msutility.customvalidation.organizationregistrationno.OrganizationRegistrationNo;
import com.motaharinia.msutility.customvalidation.password.Password;
import com.motaharinia.msutility.customvalidation.persiancharacters.PersianCharacters;
import com.motaharinia.msutility.customvalidation.personphone.PersonPhone;
import com.motaharinia.msutility.customvalidation.postalcode.PostalCode;
import com.motaharinia.msutility.customvalidation.price.Price;
import com.motaharinia.msutility.customvalidation.required.Required;
import com.motaharinia.msutility.customvalidation.stringlength.StringLength;
import com.motaharinia.msutility.customvalidation.stringpattern.StringPattern;
import com.motaharinia.msutility.customvalidation.usernameemailormobile.UsernameEmailOrMobile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:30:27<br>
 * Description:<br>
 * کلاس مدل نمونه برای تست اعتبارسنجی CustomValidation
 */
public class CustomValidationTestModel {

    //CompanyPhone:
    @CompanyPhone
    private String companyPhoneNo;

    //DateRange:
    @DateRange(from = "1399-01-01", to = "1399-12-29")
    private CustomDate dateRange1;
    @DateRange(from = "1399-01-01")
    private CustomDate dateRange2;
    @DateRange(to = "1399-12-29")
    private CustomDate dateRange3;
    @DateRange(from = "today", to = "1399-12-29")
    private CustomDate dateRange4;

    //DecimalCount:
    @DecimalCount(min = 2, max = 4)
    private Double decimalCount1;
    @DecimalCount(exact = 3)
    private Double decimalCount2;

    //DoubleRange:
    @DoubleRange(min = 12, max = 13)
    private Double doubleRange;

    //Email:
    @Email
    private String email;

    //IntegerRange:
    @IntegerRange(min = 12, max = 13)
    private Integer integerRange;

    //LatinCharacters:
    @LatinCharacters
    private String latinCharacter;

    //ListLength:
    @ListLength(min = 2, max = 4)
    private List<String> listLength1;
    @ListLength(exact = 3)
    private List<String> listLength2;

    //ListNoDuplicate:
    @ListNoDuplicate
    private List<String> listNoDuplicate;

    //ListNoDuplicateByFields:
    @ListNoDuplicateByFields(fields = {"field1"})
    private List<ListNoDuplicateByFieldsModel> listNoDuplicateByFields;

    //Mobile:
    @Mobile
    private String mobile;

    //NationalCode:
    @NationalCode
    private String nationalCode;

    //OrganizationEconomicCode:
    @OrganizationEconomicCode
    private String organizationEconomicCode;

    //OrganizationNationalCode:
    @OrganizationNationalCode
    private String organizationNationalCode;

    //OrganizationRegistrationNo:
    @OrganizationRegistrationNo
    private String organizationRegistrationNo;

    //Password:
    @Password(minLength = 6,complicated = false)
    private String password1;
    @Password(minLength = 6,complicated = true)
    private String password2;

    //PersianCharacters:
    @PersianCharacters
    private String persianCharacters;

    //PersonPhone:
    @PersonPhone
    private String personPhone;

    //PostalCode:
    @PostalCode
    private String postalCode;

    //Price:
    @Price
    private BigDecimal price;

    //Required:
    @Required
    private String required1;
    @Required
    private Integer required2;
    @Required
    private Boolean required3;
    @Required
    private List<String> required4;
    @Required
    private Map<Integer,String> required5;

    //StringLength:
    @StringLength(min=2,max=4)
    private String stringLength1;
    @StringLength(exact=3)
    private String stringLength2;

    //StringPattern (Visa Card. All Visa card numbers start with a 4. New cards have 16 digits. Old cards have 13):
    @StringPattern(pattern = "^4[0-9]{12}(?:[0-9]{3})?$")
    private String stringPattern;

    //UsernameEmailOrMobile:
    @UsernameEmailOrMobile
    private String username1;
    @UsernameEmailOrMobile
    private String username2;

    //getter-setter:
    public String getCompanyPhoneNo() {
        return companyPhoneNo;
    }

    public void setCompanyPhoneNo(String companyPhoneNo) {
        this.companyPhoneNo = companyPhoneNo;
    }

    public CustomDate getDateRange1() {
        return dateRange1;
    }

    public void setDateRange1(CustomDate dateRange1) {
        this.dateRange1 = dateRange1;
    }

    public CustomDate getDateRange2() {
        return dateRange2;
    }

    public void setDateRange2(CustomDate dateRange2) {
        this.dateRange2 = dateRange2;
    }

    public CustomDate getDateRange3() {
        return dateRange3;
    }

    public void setDateRange3(CustomDate dateRange3) {
        this.dateRange3 = dateRange3;
    }

    public CustomDate getDateRange4() {
        return dateRange4;
    }

    public void setDateRange4(CustomDate dateRange4) {
        this.dateRange4 = dateRange4;
    }

    public Double getDecimalCount1() {
        return decimalCount1;
    }

    public void setDecimalCount1(Double decimalCount1) {
        this.decimalCount1 = decimalCount1;
    }

    public Double getDecimalCount2() {
        return decimalCount2;
    }

    public void setDecimalCount2(Double decimalCount2) {
        this.decimalCount2 = decimalCount2;
    }

    public Double getDoubleRange() {
        return doubleRange;
    }

    public void setDoubleRange(Double doubleRange) {
        this.doubleRange = doubleRange;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIntegerRange() {
        return integerRange;
    }

    public void setIntegerRange(Integer integerRange) {
        this.integerRange = integerRange;
    }

    public String getLatinCharacter() {
        return latinCharacter;
    }

    public void setLatinCharacter(String latinCharacter) {
        this.latinCharacter = latinCharacter;
    }

    public List<String> getListLength1() {
        return listLength1;
    }

    public void setListLength1(List<String> listLength1) {
        this.listLength1 = listLength1;
    }

    public List<String> getListLength2() {
        return listLength2;
    }

    public void setListLength2(List<String> listLength2) {
        this.listLength2 = listLength2;
    }

    public List<String> getListNoDuplicate() {
        return listNoDuplicate;
    }

    public void setListNoDuplicate(List<String> listNoDuplicate) {
        this.listNoDuplicate = listNoDuplicate;
    }

    public List<ListNoDuplicateByFieldsModel> getListNoDuplicateByFields() {
        return listNoDuplicateByFields;
    }

    public void setListNoDuplicateByFields(List<ListNoDuplicateByFieldsModel> listNoDuplicateByFields) {
        this.listNoDuplicateByFields = listNoDuplicateByFields;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getOrganizationEconomicCode() {
        return organizationEconomicCode;
    }

    public void setOrganizationEconomicCode(String organizationEconomicCode) {
        this.organizationEconomicCode = organizationEconomicCode;
    }

    public String getOrganizationNationalCode() {
        return organizationNationalCode;
    }

    public void setOrganizationNationalCode(String organizationNationalCode) {
        this.organizationNationalCode = organizationNationalCode;
    }

    public String getOrganizationRegistrationNo() {
        return organizationRegistrationNo;
    }

    public void setOrganizationRegistrationNo(String organizationRegistrationNo) {
        this.organizationRegistrationNo = organizationRegistrationNo;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPersianCharacters() {
        return persianCharacters;
    }

    public void setPersianCharacters(String persianCharacters) {
        this.persianCharacters = persianCharacters;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRequired1() {
        return required1;
    }

    public void setRequired1(String required1) {
        this.required1 = required1;
    }

    public Integer getRequired2() {
        return required2;
    }

    public void setRequired2(Integer required2) {
        this.required2 = required2;
    }

    public Boolean getRequired3() {
        return required3;
    }

    public void setRequired3(Boolean required3) {
        this.required3 = required3;
    }

    public List<String> getRequired4() {
        return required4;
    }

    public void setRequired4(List<String> required4) {
        this.required4 = required4;
    }

    public Map<Integer, String> getRequired5() {
        return required5;
    }

    public void setRequired5(Map<Integer, String> required5) {
        this.required5 = required5;
    }

    public String getStringLength1() {
        return stringLength1;
    }

    public void setStringLength1(String stringLength1) {
        this.stringLength1 = stringLength1;
    }

    public String getStringLength2() {
        return stringLength2;
    }

    public void setStringLength2(String stringLength2) {
        this.stringLength2 = stringLength2;
    }

    public String getStringPattern() {
        return stringPattern;
    }

    public void setStringPattern(String stringPattern) {
        this.stringPattern = stringPattern;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }
}
