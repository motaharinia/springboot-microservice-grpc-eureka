package com.motaharinia.msutility.search.sample;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.motaharinia.msutility.search.SearchRowView;
import com.motaharinia.msutility.search.annotation.SearchDataColumn;

import java.util.Date;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-14<br>
 * Time: 20:49:58<br>
 * Description:<br>
 *     اینترفیس نمونه جهت تست SearchDataModelTests
 */
@JsonDeserialize
public interface SearchRowViewAdminUserBrief extends SearchRowView {

    @SearchDataColumn(index = 1,name = "id")
    Integer getId();

    @SearchDataColumn(index = 2,name = "firstName")
    String getFirstName();

    @SearchDataColumn(index = 3,name = "lastName")
    String getLastName();

    @SearchDataColumn(index = 4,name = "dateOfBirth")
    Date getDateOfBirth();

    @SearchDataColumn(index = -1,name = "defaultAdminUserContact")
    DefaultAdminUserContact getDefaultAdminUserContact();


    interface DefaultAdminUserContact {
        @SearchDataColumn(index = 5,name = "address")
        String getAddress();
    }


    @Override
    default String toOut() {
        return this.getId() + "," + this.getFirstName() + "," + this.getLastName();
    }
}
