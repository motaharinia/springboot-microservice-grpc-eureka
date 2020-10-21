package com.motaharinia.msutility.json.sample;

import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.msutility.customfield.CustomDateTime;
import com.motaharinia.msutility.customvalidation.required.Required;


import com.motaharinia.msutility.fso.view.FileViewModel;
import com.motaharinia.msutility.genericmodel.AutoCompleteModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-22<br>
 * Time: 13:41:19<br>
 * Description:<br>
 * کلاس سمپل مدل برای تست سریالایز
 */

@Getter
@Setter
@NoArgsConstructor
public class MembershipRequestFrontModelUpdate extends MembershipRequestFrontModel {

    @Required
    private Integer id;
    private String code;
    private CustomDateTime registerDateTime;
    private CustomDate meetingDate;
    private String meetingTime;
    private CustomDateTime meetingDoneDateTime;
    private CustomDateTime rejectDateTime;
    private String rejectDescription;
    private Boolean meetingRetry;
    private String status_langKey;
    private String status_value;
    private String description;
    private CustomDateTime statusDateTime;

    //user:
    private String user_gender_langKey;
    private String user_maritalStatus_langKey;
    private String user_maritalStatus_value;
    private String user_shType_langKey;
    private String user_nationality_langKey;
    private String user_inhabitancy_langKey;
    private String user_religion_langKey;
    private String user_religion_value;
    private String user_faith_langKey;
    private String user_faith_value;
    private String user_education_langKey;
    private String user_job_title;
    private String meetingLocation_langKey;
    //-----آیدی اطلاعات تماس جهت مراجعه حضوری عضو
    private Integer userContact_id;
    private String userContact_title;
    private String userContact_address;

    //socialGroup:
    private String socialGroup_name;
    private String socialGroup_code;
    private Integer socialGroup_trend_id;
    private String socialGroup_trend_name;
    private String socialGroup_defaultSocialEntityContact_city_name;
    private Integer socialGroup_defaultSocialEntityContact_city_id;
    private String socialGroup_defaultSocialEntityContact_address;
    private String socialGroup_defaultSocialEntityContact_postalCode;
    private String socialGroup_defaultSocialEntityContact_phoneNo;
    private String socialGroup_defaultSocialEntityContact_faxNo;

    //درخواست به افراد ارجاع شده
    private String referredBanker_name;
    // متصدی تایید نهایی
    private String referredFinalApprovalUser_name;


    private AutoCompleteModel socialGroup;

//    //history:
//    private List<RequestStatusHistoryFrontModel> statusHistoryList = new ArrayList<>();

    //تصویر روی کارت ملی
    private List<FileViewModel> onNationalCartFileList = new ArrayList<>();

    //تصویر پشت کارت ملی
    private List<FileViewModel> backNationalCartFileList  = new ArrayList<>();

    // ویدیو یک دقیقه ایی
    private List<FileViewModel> selfieVideo = new ArrayList<>();

    //تصویر شناسنامه صفحه اول
    private List<FileViewModel> firstPageBirthCertificateFileList = new ArrayList<>();

    //تصویر شناسنامه صفحه توضیحات
    private List<FileViewModel> descriptionBirthCertificateFileList = new ArrayList<>();

    //تصویر عکس پرسنلی
    private List<FileViewModel> personalPicture = new ArrayList<>();

    //تصویر نمونه امضا
    private List<FileViewModel> signSample = new ArrayList<>();

    //تصویر عمومی
    private List<FileViewModel> personalityFileList = new ArrayList<>();

    //زمان پیشنهادی تحویل کارت
    private CustomDate cartDeliveryDateOffer;

}
