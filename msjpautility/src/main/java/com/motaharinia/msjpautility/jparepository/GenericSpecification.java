package com.motaharinia.msjpautility.jparepository;

import com.motaharinia.msjpautility.search.filter.SearchFilterNextConditionOperatorEnum;
import com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum;
import com.motaharinia.msutility.calendar.CalendarTools;
import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.msutility.customfield.CustomDateTime;
import com.motaharinia.msutility.json.CustomObjectMapper;
import com.motaharinia.msjpautility.search.filter.SearchFilterRestrictionModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-16<br>
 * Time: 23:27:46<br>
 * Description:<br>
 * این کلاس جنریک بیلدر لازم برای تمام کلاسهای مربوط به انجام کوئری های پیشرفته دیتابیس را دارا میباشد
 */
public class GenericSpecification<T> implements Specification<T> {

    private CustomObjectMapper customObjectMapper = new CustomObjectMapper();

    private List<SearchFilterRestrictionModel> searchFilterRestrictionModelList;

    public GenericSpecification() {
        this.searchFilterRestrictionModelList = new ArrayList<>();
    }

    public void add(SearchFilterRestrictionModel searchFilterRestrictionModel) {
        searchFilterRestrictionModelList.add(searchFilterRestrictionModel);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        Date date = null;
        String string = null;
        //create a new predicate list
        List<Predicate> predicateList = new ArrayList<>();

        Predicate newPredicate = null;
        Predicate predicate = null;

        //add add criteria to predicateList
        for (SearchFilterRestrictionModel searchFilterRestrictionModel : searchFilterRestrictionModelList) {
            newPredicate = null;
            try {
                Path path = this.getPath(root, searchFilterRestrictionModel.getFieldName());

                Object fieldValue = searchFilterRestrictionModel.getFieldValue();

                switch (searchFilterRestrictionModel.getFieldOperation()) {

                    case GREATER_THAN:
                        /**
                         * بزرگتر از عدد یا تاریخ
                         */
//                        System.out.println("GenericSpecification.toPredicate case GREATER_THAN: fieldValue:"+fieldValue + " fieldValue.getClass():"+ fieldValue.getClass().toString());
                        if (Integer.class.equals(fieldValue.getClass())) {
                            predicateList.add(builder.greaterThan(path, (Integer) fieldValue));
                        } else {
                            LinkedHashMap linkedHashMap = (LinkedHashMap) fieldValue;
                            if (linkedHashMap.containsKey("hour")) {
                                date = CalendarTools.getDateFromCustomDateTime(this.customObjectMapper.convertValue(linkedHashMap, CustomDateTime.class));
                            } else {
                                date = CalendarTools.getDateFromCustomDate(this.customObjectMapper.convertValue(linkedHashMap, CustomDate.class));
                            }
//                            System.out.println("GenericSpecification.toPredicate case GREATER_THAN : date:"+date);
                            newPredicate = builder.greaterThan(path, date);

                        }
                        break;

                    case LESS_THAN:
                        /**
                         * کوچکتر  از عدد یا تاریخ
                         */
//                        System.out.println("GenericSpecification.toPredicate case LESS_THAN: fieldValue:"+fieldValue + " fieldValue.getClass():"+ fieldValue.getClass().toString());
                        if (Integer.class.equals(fieldValue.getClass())) {
                            predicateList.add(builder.lessThan(path, (Integer) fieldValue));
                        } else {
                            LinkedHashMap linkedHashMap = (LinkedHashMap) fieldValue;
                            if (linkedHashMap.containsKey("hour")) {
                                date = CalendarTools.getDateFromCustomDateTime(this.customObjectMapper.convertValue(linkedHashMap, CustomDateTime.class));
                            } else {
                                date = CalendarTools.getDateFromCustomDate(this.customObjectMapper.convertValue(linkedHashMap, CustomDate.class));
                            }
//                            System.out.println("GenericSpecification.toPredicate case LESS_THAN : date:"+date);
                            newPredicate = builder.lessThan(path, date);
                        }
                        break;

                    case GREATER_THAN_EQUAL:
                        /**
                         * بزرگتر مساوی از عدد یا تاریخ
                         */
                        //                        System.out.println("GenericSpecification.toPredicate case GREATER_THAN_EQUAL: fieldValue:"+fieldValue + " fieldValue.getClass():"+ fieldValue.getClass().toString());
                        if (Integer.class.equals(fieldValue.getClass())) {
                            predicateList.add(builder.greaterThanOrEqualTo(path, (Integer) fieldValue));
                        } else {
                            LinkedHashMap linkedHashMap = (LinkedHashMap) fieldValue;
                            if (linkedHashMap.containsKey("hour")) {
                                date = CalendarTools.getDateFromCustomDateTime(this.customObjectMapper.convertValue(linkedHashMap, CustomDateTime.class));
                            } else {
                                date = CalendarTools.getDateFromCustomDate(this.customObjectMapper.convertValue(linkedHashMap, CustomDate.class));
                            }
//                            System.out.println("GenericSpecification.toPredicate case GREATER_THAN_EQUAL : date:"+date);
                            newPredicate = builder.greaterThanOrEqualTo(path, date);
                        }
                        break;

                    case LESS_THAN_EQUAL:
                        /**
                         * کوچکتر مساوی از عدد یا تاریخ
                         */
                        //                        System.out.println("GenericSpecification.toPredicate case LESS_THAN_EQUAL: fieldValue:"+fieldValue + " fieldValue.getClass():"+ fieldValue.getClass().toString());
                        if (Integer.class.equals(fieldValue.getClass())) {
                            predicateList.add(builder.lessThanOrEqualTo(path, (Integer) fieldValue));
                        } else {
                            LinkedHashMap linkedHashMap = (LinkedHashMap) fieldValue;
                            if (linkedHashMap.containsKey("hour")) {
                                date = CalendarTools.getDateFromCustomDateTime(this.customObjectMapper.convertValue(linkedHashMap, CustomDateTime.class));
                            } else {
                                date = CalendarTools.getDateFromCustomDate(this.customObjectMapper.convertValue(linkedHashMap, CustomDate.class));
                            }
                            //                            System.out.println("GenericSpecification.toPredicate case LESS_THAN_EQUAL : date:"+date);
                            newPredicate = builder.lessThanOrEqualTo(path, date);
                        }
                        break;

                    case EQUAL:
                        /**
                         * برابر باشد با
                         */
                        newPredicate = builder.equal(path, fieldValue);
                        break;

                    case NOT_EQUAL:
                        /**
                         * برابر نباشد با
                         */
                        newPredicate = builder.notEqual(path, fieldValue);
                        break;

                    case MATCH:
                        /**
                         * تطبیق رشته ای داشته باشد با
                         */
                        if (String.class.equals(fieldValue.getClass())) {
                            string = (String) fieldValue;
                            newPredicate = builder.like(builder.lower(path), "%" + string.toLowerCase() + "%");
                        }
                        break;

                    case MATCH_START:
                        /**
                         * با ابتدای عبارت تطبیق رشته ای داشته باشد
                         */
                        if (String.class.equals(fieldValue.getClass())) {
                            string = (String) fieldValue;
                            newPredicate = builder.like(builder.lower(path), string.toLowerCase() + "%");
                        }
                        break;

                    case MATCH_END:
                        /**
                         * با انتهای عبارت تطبیق رشته ای داشته باشد
                         */
                        if (String.class.equals(fieldValue.getClass())) {
                            string = (String) fieldValue;
                            newPredicate = builder.like(builder.lower(path), "%" + string.toLowerCase());
                        }
                        break;

                    case IN:
                        /**
                         * مقدار فیلد انتیتی در بین یکی از گزینه های لیست مقادیر ورودی دلخواه باشد<br>
                         * SELECT a FROM EntityA a WHERE a.field IN :valueCollection
                         */
                        newPredicate = builder.in(path).value(searchFilterRestrictionModel.getFieldValue());
                        break;
                    case NOT_IN:
                        /**
                         * مقدار فیلد انتیتی در بین هیچ یک از گزینه های لیست مقادیر ورودی دلخواه نباشد<br>
                         * SELECT a FROM EntityA a WHERE a.field NOT IN :valueCollection
                         */
                        newPredicate = builder.not(path).in(searchFilterRestrictionModel.getFieldValue());
                        break;
                    case MEMBER_OF:
                        /**
                         *مقدار ورودی دلخواه عضوی از گزینه های فیلد انتیتی از نوع لیست باشد<br>
                         * SELECT a FROM EntityA a WHERE :value MEMBER OF a.fieldCollection
                         */
                        newPredicate = builder.isMember(searchFilterRestrictionModel.getFieldValue(), path);
                        break;
                    case NOT_MEMBER_OF:
                        /**
                         *مقدار ورودی دلخواه عضوی از گزینه های فیلد انتیتی از نوع لیست نباشد<br>
                         * SELECT a FROM EntityA a WHERE :value NOT MEMBER OF a.fieldCollection
                         */
                        newPredicate = builder.isNotMember(searchFilterRestrictionModel.getFieldValue(), path);
                        break;
                }
                if (!ObjectUtils.isEmpty(newPredicate)) {
                    predicate = this.addNextCondition(builder, searchFilterRestrictionModel, predicate, newPredicate);
                }
            } catch (Exception e) {
                System.out.println("GenericSpecification.toPredicate  Exception:" + e.toString());
            }
        }
//        return builder.and(predicateList.toArray(new Predicate[0]));
        return predicate;
    }


    /**
     * این متد سازنده و مدل و شرط قبلی و شرط جدید را از ورودی میگرد و بر اساس نوع and یا or مدل شرط نهایی آن دو شرط ورودی را خروجی میدهد
     *
     * @param builder                      سازنده
     * @param searchFilterRestrictionModel مدل
     * @param predicate                    شرط قبلی
     * @param newPredicate                 شرط جدید
     * @return خروجی: شرط نهایی
     */
    private Predicate addNextCondition(CriteriaBuilder builder, SearchFilterRestrictionModel searchFilterRestrictionModel, Predicate predicate, Predicate newPredicate) {
        if (ObjectUtils.isEmpty(predicate)) {
            predicate = newPredicate;
        } else {
            if (searchFilterRestrictionModel.getNextConditionOperator().equals(SearchFilterNextConditionOperatorEnum.AND)) {
                predicate = builder.and(predicate, newPredicate);
            } else {
                predicate = builder.or(predicate, newPredicate);
            }
        }
        return predicate;
    }


    /**
     * این متد برای به دست آوردن Path فیلد مورد نظر برای اضافه شدن شرایط میباشد. اگر این متد و path استفاده نشود در اینترفیسهای projection که میخواهیم فیلدی با جنس انتیتی را بخوانیم null pointer خواهیم گرفت
     *
     * @param root ریشه انتیتی
     * @param path نام فیلد (در صورت نیاز میتواند چند فیلد درون هم با نقطه از هم جدا شوند)
     * @return خروجی: مقدار path مورد نیاز برای استفاده در شرطها
     */
    private Path getPath(Root root, String path) {
        String[] pathArray = path.split("\\.");
        if (pathArray.length > 1) {
            //اگر داخل نام فیلد نقطه وجود داشته باشد باید به تعداد یکی کمتر از فیلدهای نقطه خورده جوین بزنیم تا بتوانیم روی آخرین جوین path فیلد نهایی را خروجی بدهیم
            Join join = null;
            for (int i = 0; i < pathArray.length - 1; i++) {
                if (join == null) {
                    join = root.join(pathArray[i], JoinType.LEFT);
                } else {
                    join = join.join(pathArray[i], JoinType.LEFT);
                }
            }
            return join.get(pathArray[pathArray.length - 1]);
        } else {
            //اگر داخل نام فیلد نقطه وجود نداشته باشد فقط path همان فیلد در root انتیتی را خروجی میدهیم
            return root.get(path);
        }
    }


}
