package com.motaharinia.msjpautility.search;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-16<br>
 * Time: 23:14:13<br>
 * Description:<br>
 *     اینترفیس پدر تمامی اینترفیسهای جستجوی پیشرفته دیتابیس که حکم میکند تمام آنها باید متد گتر آی دی را داشته باشند<br>
 * تمام اینترفیسهای جستجوی پیشرفته دیتابیس باید از این اینترفیس گسترش یابند
 */
public interface SearchRowView {

    Integer getId();
    String toOut();

}
