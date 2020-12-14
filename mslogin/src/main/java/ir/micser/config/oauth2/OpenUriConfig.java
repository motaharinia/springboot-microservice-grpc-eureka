package ir.micser.config.oauth2;//package com.motaharinia.oauthsecurityconfig;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Set;
//import java.util.stream.Collectors;
//
////آدرسهایی که در این کلاس تنظیم میگردند نیازی به لاگین بودن ندارند و خطای لاگین برای این آدرسها داده نخواهد شد
//public class OpenUriConfig {
//
//    private static final Logger logger = LoggerFactory.getLogger(OpenUriConfig.class);
//    private static List<RunConfigurationOpenUriModel> openUriAuthenticationList = new ArrayList<RunConfigurationOpenUriModel>();
//    private static List<RunConfigurationOpenUriModel> openUriAuthorizationList = new ArrayList<RunConfigurationOpenUriModel>();
//
//    static {
//        //root:
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("localhost", "/", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("common", "/", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("ehelp", "/", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("eshop", "/", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/shop/**", OpenUriConfig.class.getSimpleName()));
//
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/favicon.ico", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("common", "/favicon.ico", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("ehelp", "/favicon.ico", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("eshop", "/favicon.ico", OpenUriConfig.class.getSimpleName()));
//
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("common", "/bp", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("ehelp", "/bp", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("eshop", "/bp", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/fp", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/fp2", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w", OpenUriConfig.class.getSimpleName()));
//
//        //other: async:
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("common", "/async/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/async/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel2/async/**", OpenUriConfig.class.getSimpleName()));
//        //other: /fso:
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("common", "/fso/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("ehelp", "/fso/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("eshop", "/fso/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/fso/**", OpenUriConfig.class.getSimpleName()));
//        //other: /general/:
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("common", "/backPanel/general/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("ehelp", "/backPanel/general/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("eshop", "/backPanel/general/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/general/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel2/general/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/general/**", OpenUriConfig.class.getSimpleName()));
////        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/general/getLoggedInModel", OpenUriConfig.class.getSimpleName()));
//        //user: /expired:
////        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("common", "/backPanel/general/expired**", OpenUriConfig.class.getSimpleName()));
////        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("front", "/frontPanel/general/expired**", OpenUriConfig.class.getSimpleName()));
//        //user: login:
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("common", "/backPanel/login**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("ehelp", "/backPanel/login**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("eshop", "/backPanel/login**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/login**", OpenUriConfig.class.getSimpleName()));
//        //user: logout:
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("common", "/logout**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("eshop", "/logout**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/logout**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("common", "/logoutToken**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("eshop", "/logoutToken**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/logoutToken**", OpenUriConfig.class.getSimpleName()));
//        //user: /captcha:
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("common", "/captcha/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/captcha/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("eshop", "/captcha/**", OpenUriConfig.class.getSimpleName()));
//
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/user/resetPassword", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/user/submaster/findByUsername", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/user/submaster/findByUsername", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/user/submaster/find", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/user/submaster/find", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/user/submaster/notificationType", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/user/submaster/notificationType", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/user/submaster/notificationType", OpenUriConfig.class.getSimpleName()));
//
//        //user: /confirmationCode/send:
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/confirmationCode/send", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/confirmationCode/send", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/confirmationCode/sendCart", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/confirmationCode/sendCart", OpenUriConfig.class.getSimpleName()));
//        //confirmationCode/check:
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/confirmationCode/check", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/confirmationCode/check", OpenUriConfig.class.getSimpleName()));
//        //customer: /signup:
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/customer/signup", OpenUriConfig.class.getSimpleName()));
//        //customer: /customer/create:
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/customer/create", OpenUriConfig.class.getSimpleName()));
//        //helpPage:
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/hp**/**", OpenUriConfig.class.getSimpleName()));
//        //membership:
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/membershipRequest/create", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/membershipCompanyRequest/create", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/membershipShopRequest/create", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/membershipCompanyRequest/checkByEconomicCode", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/membershipCompanyRequest/requestAppointment", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/membershipShopRequest/checkByEconomicCode", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/membershipShopRequest/requestAppointment", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/membershipCompanyRequest/checkByEconomicCode", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/membershipShopRequest/checkByEconomicCode", OpenUriConfig.class.getSimpleName()));
//
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/user/submaster/checkByNationalCode", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/user/submaster/checkByNationalCode", OpenUriConfig.class.getSimpleName()));
//
//        //shop:
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/productType/departmentProductType", OpenUriConfig.class.getSimpleName()));
//
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/getRelatedProductTypeFullPage", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/productTypeByAdvancedSearch", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/advancedSearchSidebar", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/simpleSearch", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/departmentProductTypeList", OpenUriConfig.class.getSimpleName()));
//
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/getDepartmentMaxDiscountList", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/getDepartmentBestSellingList", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/getDepartmentBestFacilityList", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/getDepartmentNewProductTypeList", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/getDepartmentRrecomendedList", OpenUriConfig.class.getSimpleName()));
//
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/findByProductTypeGroupFrontType", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/full", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/getOtherList", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/getOtherListSidebar", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/getProductTypeAttributeHashMap", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/getstockByVitrin/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/caclulateCacheProductType", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/fixStockIncreaseQuantity/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/fixAllStockIncreaseQuantity", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/departmentAllProductTypeList", OpenUriConfig.class.getSimpleName()));
//
//        //PurchasOrder
//        /*openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/purchaseOrder/factor/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/purchaseOrder/error/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/purchaseOrder/createGW/**", OpenUriConfig.class.getSimpleName()));*/
//        //EshopDashboard
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("eshop", "/backPanel/eshopDashboard/getModel/**", OpenUriConfig.class.getSimpleName()));
//
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/purchaseOrder/downloadVirtualFile/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/membershipRequest/submaster/loadRequest", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/membershipRequest/requestAppointment", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/general/autocomplete", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel2/general/autocomplete", OpenUriConfig.class.getSimpleName()));
//
//        //Department
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/department/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/department/**", OpenUriConfig.class.getSimpleName()));
//
//        //Organization
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/organization/submaster/findByUsername", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/organization/submaster/findByUsername", OpenUriConfig.class.getSimpleName()));
//
//        //cart
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/cart/submaster/findProductTypeBriefList", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/cart/submaster/updatePaymentWithGateway", OpenUriConfig.class.getSimpleName()));
//
//        //new cart
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/cart/submaster/productTypeFindCheck", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/cart/submaster/getProductTypeTitle", OpenUriConfig.class.getSimpleName()));
//
//        //city
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/city/getVitrinModel/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/city/getVitrinModel/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel2/city/getVitrinModel/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/city/submaster/customCombo", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/city/submaster/customCombo", OpenUriConfig.class.getSimpleName()));
//
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("eshop", "/backPanel/city/submaster/customCombo", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("common", "/backPanel/city/submaster/customCombo", OpenUriConfig.class.getSimpleName()));
//        // etcItem
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/frontPanel/etcItem/submaster/customCombo", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/etcItem/submaster/customCombo", OpenUriConfig.class.getSimpleName()));
//
//        //shaparak
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/shaparak/**", OpenUriConfig.class.getSimpleName()));
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/bankGateway/**", OpenUriConfig.class.getSimpleName()));
//
//        //frontProductType
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/w/productType/getRelatedProductTypeFullPage", OpenUriConfig.class.getSimpleName()));
//
//        //serverCheckReachable
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("www", "/serverCheckReachable", OpenUriConfig.class.getSimpleName()));
//
//        //superAdmin
//        openUriAuthenticationList.add(new RunConfigurationOpenUriModel("common", "/backPanel/superAdmin/logChangeRevisionRun", OpenUriConfig.class.getSimpleName()));
//        //openUriAuthenticationList.add(new RunConfigurationOpenUriModel("common", "/backPanel/superAdmin/**", OpenUriConfig.class.getSimpleName()));
//    }
//
//    public static List<RunConfigurationOpenUriModel> getOpenUriAuthenticationList() {
//        return openUriAuthenticationList;
//    }
//
//    public static void setOpenUriAuthenticationList(List<RunConfigurationOpenUriModel> aOpenUriAuthenticationHashMap) {
//        openUriAuthenticationList = aOpenUriAuthenticationHashMap;
//    }
//
//    public static List<RunConfigurationOpenUriModel> getOpenUriAuthorizationList() {
//        return openUriAuthorizationList;
//    }
//
//    public static void setOpenUriAuthorizationList(List<RunConfigurationOpenUriModel> aOpenUriAuthorizationHashMap) {
//        openUriAuthorizationList = aOpenUriAuthorizationHashMap;
//    }
//
//    public static String[] getOpenUriAuthenticationArray() {
//
//        logger.info("AAAAAAAAAA: start: getOpenUriAuthenticationArray getOpenUriAuthenticationList().size():" + getOpenUriAuthenticationList().size());
//
//        Set<String> openUriAuthenticationSet = getOpenUriAuthenticationList().stream().map(RunConfigurationOpenUriModel::getUri).collect(Collectors.toSet());
//        String[] openUriAuthenticationArray = openUriAuthenticationSet.stream().toArray(String[]::new);
//
//        logger.info("AAAAAAAAAA: finish");
//        return openUriAuthenticationArray;
//
//        //todo=> [dev1]====>[نسخه قبلی][بازنویسی شده]
////        logger.info("AAAAAAAAAA: start: getOpenUriAuthenticationArray getOpenUriAuthenticationList().size():" + getOpenUriAuthenticationList().size());
////        List<String> openUriAuthenticationList = new ArrayList<>();
////        for (RunConfigurationOpenUriModel openUriModel : getOpenUriAuthenticationList()) {
////            if (!openUriAuthenticationList.contains(openUriModel.getUri())) {
////                openUriAuthenticationList.add(openUriModel.getUri());
////            }
////        }
////        String[] openUriAuthenticationArray = new String[openUriAuthenticationList.size()];
////        openUriAuthenticationList.toArray(openUriAuthenticationArray);
////        logger.info("AAAAAAAAAA: finish");
////        return openUriAuthenticationArray;
//    }
//
//    public static Boolean containsOpenUriAuthorizationList(String uri, Boolean startWith, String subDomain) {
//
//        logger.info("AAAAAAAAAA: start: containsOpenUriAuthorizationList uri:" + uri + " startWith:" + startWith + " subDomain:" + subDomain);
//        Boolean result = getOpenUriAuthorizationList().stream()
//                .anyMatch(openUriModel -> (openUriModel.getUri().toLowerCase().equals(uri.toLowerCase()))
//                        && (Objects.equals(openUriModel.getStartWith(), startWith))
//                        && (openUriModel.getSubDomain().toLowerCase().equals(subDomain.toLowerCase())));
//
//        logger.info("AAAAAAAAAA: start: containsOpenUriAuthorizationList result:" + result);
//        return result;
//
//    }
//
//    public static List<RunConfigurationOpenUriModel> getOpenUriAuthorizationStartWithList() {
//        logger.info("AAAAAAAAAA: start: getOpenUriAuthorizationStartWithList getOpenUriAuthorizationList().size():" + getOpenUriAuthorizationList().size());
//        List<RunConfigurationOpenUriModel> openUriAuthorizationStartWithList
//                = getOpenUriAuthorizationList().stream().filter(RunConfigurationOpenUriModel::getStartWith).collect(Collectors.toList());
//        logger.info("AAAAAAAAAA: finish");
//        return openUriAuthorizationStartWithList;
//
//        //todo=> [dev1]====>[نسخه قبلی متد][بازنویسی شده]
////        logger.info("AAAAAAAAAA: start: getOpenUriAuthorizationStartWithList getOpenUriAuthorizationList().size():" + getOpenUriAuthorizationList().size());
////        List<OpenUriModel> openUriAuthorizationStartWithList = new ArrayList<>();
////        for (RunConfigurationOpenUriModel openUriModel : getOpenUriAuthorizationList()) {
////            if (openUriModel.getStartWith()) {
////                openUriAuthorizationStartWithList.add(openUriModel);
////            }
////        }
////        logger.info("AAAAAAAAAA: finish");
////        return openUriAuthorizationStartWithList;
//    }
//
//    public static void addOpenAuthorizationUri(String uri, Boolean startWith, String subDomain) {
//        logger.info("AAAAAAAAAA Start: uri: " + uri + " startWith:" + startWith + " subDomain:" + subDomain);
//        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
//        String callerClassName = stacktrace[3].getClassName();
//        for (StackTraceElement stacktraceElem : stacktrace) {
//            callerClassName = stacktraceElem.getMethodName();
//            if (callerClassName.contains("postConstructor")) {
//                callerClassName = stacktraceElem.getClassName();
//                break;
//            }
//        }
//
//        openUriAuthorizationList.add(new RunConfigurationOpenUriModel(subDomain, uri.toLowerCase(), startWith, callerClassName));
//        logger.info("AAAAAAAAAA Finish");
//    }
//}
