package com.motaharinia.msjpautility.search.sample;

import java.util.Date;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-14<br>
 * Time: 20:49:58<br>
 * Description:<br>
 * کلاس نمونه جهت تست SearchDataModelTests
 */
public class SearchRowViewAdminUserBriefImpl implements SearchRowViewAdminUserBrief {

    private Integer id;

    public SearchRowViewAdminUserBriefImpl() {
    }

    public SearchRowViewAdminUserBriefImpl(Integer id) {
        this.id = id;
    }


    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getFirstName() {
        return "Mostafa" + this.id;
    }

    @Override
    public String getLastName() {
        return "Motaharinia" + this.id;
    }

    @Override
    public Date getDateOfBirth() {
        return new Date();
    }

    @Override
    public DefaultAdminUserContact getDefaultAdminUserContact() {
        return new DefaultAdminUserContactImpl(this.id);
    }

    class DefaultAdminUserContactImpl implements DefaultAdminUserContact {

        private Integer id;

        public DefaultAdminUserContactImpl() {
        }

        public DefaultAdminUserContactImpl(Integer id) {
            this.id = id;
        }

        @Override
        public String getAddress() {
            return "Tehran" + this.id;
        }
    }
}
