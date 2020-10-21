//  --------- ContextPath ارتباط و انتقال اطلاعات به سرور با 
// method نوع درخواست
// dataType نوع اطلاعات ارسالی
// url ادرس
// object اطلاعات ارسالی
// loadingPlace که در پارامتر داده می شود انیمیشن لودینگ نمایش داده می شود div در صورتیکه درخواست ارسال شده و منتظر جواب هستید در ادرس
// silent باشد خطای دریافتی بر روی صفحه بصورت پاپ آپ نمایش داده میشود در غیر اینصورت پاپ اپ نمایش داده نمی شود و اظلاعات خطا خروجی داده می شود false نباشد و این مقدار200 اگر وضعیت درخواست
// failureFunction  بگیرد این متد فراخوانی می شود 200 در صورتیکه درخواست وضعیتی غیر از 
// successFunction  بگیرد این متد فراخوانی میشود 200 در صورتیکه درخواست وضعیت
// async  ارسال می گردد که باعث میشود فراخوانی کننده تا دریافت جواب منتظر بماند syncارسال میگردد و در غیر اینصورت درخواست بصورت async بودن آن در خواست به صورتtrueدر صورت 
function funjs_commonSendAjaxRequestWithoutContextPath(method, dataType, url, object, loadingPlace, silent, failureFunction, successFunction, async)
{
    var ajaxStatus = "200";
    if (silent == null) {
        silent = false;
    }
    if (async == null) {
        async = false;
    }
    if (method === null || method === "") {
        method = "post";
    }
    if (dataType === null || dataType === "") {
        dataType = "json";
    }
    var contentType = "application/x-www-form-urlencoded; charset=UTF-8";
    if (dataType === "json") {
        contentType = "application/json; charset=utf-8";
    }
    if (typeof object === 'undefined') {
        object = null;
    }

    var headerVars;

    headerVars = {
        Accept: contentType,
        "Content-Type": contentType,
        "Authorization": funjs_oauth2GetBearerHeader()
    };
    var result = {};

    if (loadingPlace == true) {
        funjs_layoutLoadingShow();

    } else if ($(loadingPlace).is("input")) {
        loadingPlace.addClass("hasLoadingImage");
    } else if ($(loadingPlace).is("div")) {
        funjs_layoutLoadingShowIn(loadingPlace);
    }
    if (method === "post") {
        $.ajax({
            headers: headerVars,
            type: method,
            url: url,
            data: JSON.stringify(object),
            contentType: contentType,
            dataType: dataType,
            async: async,
            success: function (data, status, jqXHR) {
                if (jqXHR.getResponseHeader("token") != null) {
                    varjs.page.token = jqXHR.getResponseHeader("token");
                }
                funjs_commonCheckAjaxResponse(jqXHR);
                if (loadingPlace == true) {
                    funjs_layoutLoadingHide();
                } else if ($(loadingPlace).is("input")) {
                    loadingPlace.removeClass("hasLoadingImage");
                } else if ($(loadingPlace).is("div")) {
//                    funjs_layoutLoadingHide();
                }
                if (successFunction != null) {
                    successFunction(data);
                }
                if (silent == true) {
                    result.data = data;
                    result.status = "200";
                } else {
                    result = data;
                }
                funjs_layoutLoadingHide();
            },
            error: function (data) {
                funjs_commonCheckAjaxResponse(data);
                ajaxStatus = "400";
                var responseData = jQuery.parseJSON(data.responseText);
                if (failureFunction != null) {
                    failureFunction();
                }
                if (silent == true) {
                    result.data = data.responseJSON;
                    result.status = "400";
                } else {
                    result = data;
                    funjs_popupShowError(responseData, loadingPlace);
                }

                funjs_layoutLoadingHide();
            },
            failure: function (errMsg) {
                alert(errMsg);
                funjs_layoutLoadingHide();
            }
        });
    } else {

        if (object != null) {
            $.ajax({
                headers: headerVars,
                type: method,
                url: varjs.page.url.contextPath + url,
                data: object,
                contentType: contentType,
                dataType: dataType,
                async: isAsync,
                success: function (data) {
                    result = data;
                    funjs_layoutLoadingHide();
                },
                error: function (data) {
                    funjs_commonCheckAjaxResponse(data);
                    ajaxStatus = "400";
                    funjs_popupShowAlert("fail", funjs_translateKey("businessexception.title"), data, false);
                    funjs_layoutLoadingHide();
                },
                failure: function (errMsg) {
                    alert(errMsg);
                    funjs_layoutLoadingHide();
                }
            });
        } else {
            $.ajax({
                headers: headerVars,
                type: method,
                url: url,
                data: JSON.stringify(object),
                contentType: contentType,
                dataType: dataType,
                async: async,
                success: function (data) {
                    result = data;
                    if (successFunction != null) {
                        successFunction(data);
                    }
                    funjs_layoutLoadingHide();
                },
                error: function (data) {
                    funjs_commonCheckAjaxResponse(data);
                    ajaxStatus = "400";
                    funjs_popupShowAlert("fail", funjs_translateKey("businessexception.title"), data, false);

                    funjs_layoutLoadingHide();
                },
                failure: function (errMsg) {
                    alert(errMsg);
                    funjs_layoutLoadingHide();
                }
            });
        }
    }
    if (ajaxStatus == "400") {
        result["status"] = "400";
    }
    return result;
}

//  --------- ContextPath ارتباط و انتقال اطلاعات به سرور بدون
// method نوع درخواست
// dataType نوع اطلاعات ارسالی
// url ادرس
// object اطلاعات ارسالی
// loadingPlace که در پارامتر داده می شود انیمیشن لودینگ نمایش داده می شود div در صورتیکه درخواست ارسال شده و منتظر جواب هستید در ادرس
// silent باشد خطای دریافتی بر روی صفحه بصورت پاپ آپ نمایش داده میشود در غیر اینصورت پاپ اپ نمایش داده نمی شود و اظلاعات خطا خروجی داده می شود false نباشد و این مقدار200 اگر وضعیت درخواست
// failureFunction  بگیرد این متد فراخوانی می شود 200 در صورتیکه درخواست وضعیتی غیر از
// loadingProgressBar  باشد true برای نمایش نمودار لودینگ اگر مقدرا
// popupErrorShow     برای نمایش خطا در پاپ اپ
function funjs_commonSendAjaxRequest(method, dataType, url, object, loadingPlace, silent, failureFunction, loadingProgressBar, popupErrorShow)
{
    if (typeof loadingProgressBar === 'undefined') {
        loadingProgressBar = false;
    }

    if (typeof object === 'undefined') {
        object = null;
    }

    var isAsync = false;
    var successFunction = null;
    var ajaxStatus = "200";
    if (silent == null) {
        silent = false;
        isAsync = false;
    } else {
        if (typeof (silent) !== "boolean") {
            isAsync = true;
            successFunction = silent;
            silent = true;
        }
    }
//    console.log("isAsync:" + isAsync)
//    console.log("successFunction1:" + successFunction);

    if ((typeof loadingPlace === 'undefined') || (loadingPlace == null) || (typeof loadingPlace !== 'boolean' && loadingPlace == "")) {
        loadingPlace = true;
    }
    if (method === null || method === "") {
        method = "post";
    }
    if (dataType === null || dataType === "") {
        dataType = "json";
    }
    var contentType = "application/x-www-form-urlencoded; charset=UTF-8";
    if (dataType === "json") {
        contentType = "application/json; charset=utf-8";
    }
    var headerVars;
    headerVars = {
        Accept: contentType,
        "Content-Type": contentType,
        "Authorization": funjs_oauth2GetBearerHeader()
    };
    var result = {};
    if (loadingPlace !== false) {
        if (loadingPlace == true) {
            funjs_layoutLoadingShow(loadingProgressBar);
        } else if (loadingPlace.is("input") || loadingPlace.is("select")) {
            loadingPlace.addClass("hasLoadingImage");
        } else if ($(loadingPlace).is("div")) {
            funjs_layoutLoadingShowIn($(loadingPlace), loadingProgressBar);
        }
    }

    if (method === "post") {
        $.ajax({
            headers: headerVars,
            type: method,
            url: varjs.page.url.contextPath + url,
            data: JSON.stringify(object),
            contentType: contentType,
            dataType: dataType,
            async: isAsync,
            success: function (data, status, jqXHR) {

                if (loadingPlace !== false) {
                    if (loadingPlace == true) {
//                        funjs_layoutLoadingHide();
                    } else if (loadingPlace.is("input") || loadingPlace.is("select")) {
                        loadingPlace.removeClass("hasLoadingImage");
                    } else if ($(loadingPlace).is("div")) {
//                        funjs_layoutLoadingHide();
                    }
                }

                if (jqXHR.getResponseHeader("token") != null) {
                    varjs.page.token = jqXHR.getResponseHeader("token");
                }

                if (silent == true) {
                    result.data = data;
                    result.status = "200";
                } else {
                    result = data;
                }

//                console.log("**success loadingPlace:" + loadingPlace + " url:" + url + " silent:" + silent + " successFunction:" + (successFunction != null) + " result:" + result);

                if (successFunction != null) {
                    successFunction.apply(null, [result]);
                }
                if ((loadingPlace) && (!loadingProgressBar)) {
                    funjs_layoutLoadingHide();

                }
            },
            error: function (data) {
                if (loadingPlace !== false) {
                    if (loadingPlace == true) {
                        funjs_layoutLoadingHide();
                    } else if (loadingPlace.is("input") || loadingPlace.is("select")) {
                        loadingPlace.removeClass("hasLoadingImage");
                    } else if ($(loadingPlace).is("div")) {
                        funjs_layoutLoadingHide();
                    }
                }
                funjs_commonCheckAjaxResponse(data);
                ajaxStatus = "400";
                var responseData = jQuery.parseJSON(data.responseText);



                if (silent == true) {
                    result.data = data.responseJSON;
                    result.status = "400";

                } else {
                    result = data;
                    funjs_popupShowError(responseData, loadingPlace);
                }

                if (failureFunction != null) {
                    failureFunction.apply(null, [result]);
                }


            },
            failure: function (errMsg) {


                if (loadingPlace !== false) {
                    if (loadingPlace == true) {
                        funjs_layoutLoadingHide();
                    } else if (loadingPlace.is("input") || loadingPlace.is("select")) {
                        loadingPlace.removeClass("hasLoadingImage");
                    } else if ($(loadingPlace).is("div")) {
                        funjs_layoutLoadingHide();
                    }
                }
                alert(errMsg);
            }
        });
    } else {

        if (object != null) {
            $.ajax({
                headers: headerVars,
                type: method,
                url: varjs.page.url.contextPath + url,
                data: object,
                contentType: contentType,
                dataType: dataType,
                async: isAsync,
                success: function (data) {

                    result = data;
                    funjs_layoutLoadingHide();
                },
                error: function (data) {
                    funjs_commonCheckAjaxResponse(data);
                    ajaxStatus = "400";
                    funjs_popupShowAlert("fail", funjs_translateKey("businessexception.title"), data, false);
                    funjs_layoutLoadingHide();
                },
                failure: function (errMsg) {
                    alert(errMsg);
                    funjs_layoutLoadingHide();
                }
            });
        } else {
            $.ajax({
                headers: headerVars,
                type: method,
                url: varjs.page.url.contextPath + url,
                contentType: contentType,
                dataType: dataType,
                async: isAsync,
                success: function (data) {

                    result = data;
                    funjs_layoutLoadingHide();
                },
                error: function (data) {
                    result = data;
                    funjs_commonCheckAjaxResponse(data);
                    ajaxStatus = "400";
                    if (popupErrorShow) {
                    funjs_popupShowAlert("fail", funjs_translateKey("businessexception.title"), data, false);
                    }
                    funjs_layoutLoadingHide();
                },
                failure: function (errMsg) {
//                    alert(errMsg);
                    funjs_layoutLoadingHide();
                }
            });
        }

    }
    if (ajaxStatus == "400") {
        result["status"] = "400";
    }
    return result;
}



//  --------- ContextPath ارتباط و انتقال اطلاعات به سرور بدون

// urlEnum ادرس
// object اطلاعات ارسالی
// loadingPlace که در پارامتر داده می شود انیمیشن لودینگ نمایش داده می شود div در صورتیکه درخواست ارسال شده و منتظر جواب هستید در ادرس
// silent باشد خطای دریافتی بر روی صفحه بصورت پاپ آپ نمایش داده میشود در غیر اینصورت پاپ اپ نمایش داده نمی شود و اظلاعات خطا خروجی داده می شود false نباشد و این مقدار200 اگر وضعیت درخواست
// failureFunction  بگیرد این متد فراخوانی می شود 200 در صورتیکه درخواست وضعیتی غیر از
// loadingProgressBar  باشد true برای نمایش نمودار لودینگ اگر مقدرا
// popupErrorShow     برای نمایش خطا در پاپ اپ
function funjs_commonMsRequest(urlEnum, object, loadingPlace, silent, failureFunction, loadingProgressBar, popupErrorShow)
{

    console.log("urlEnum",urlEnum)

    if (typeof loadingProgressBar === 'undefined') {
        loadingProgressBar = false;
    }

    if (typeof object === 'undefined') {
        object = null;
    }

    var isAsync = false;
    var successFunction = null;
    var ajaxStatus = "200";
    if (silent == null) {
        silent = false;
        isAsync = false;
    } else {
        if (typeof (silent) !== "boolean") {
            isAsync = true;
            successFunction = silent;
            silent = true;
        }
    }
//    console.log("isAsync:" + isAsync)
//    console.log("successFunction1:" + successFunction);

    if ((typeof loadingPlace === 'undefined') || (loadingPlace == null) || (typeof loadingPlace !== 'boolean' && loadingPlace == "")) {
        loadingPlace = true;
    }
    if (urlEnum.method === null || urlEnum.method === "") {
        urlEnum.method = "post";
    }
    if (urlEnum.responseDataType === null || urlEnum.responseDataType === "") {
        urlEnum.responseDataType = "json";
    }
    var contentType = "application/x-www-form-urlencoded; charset=UTF-8";
    if (urlEnum.responseDataType === "json") {
        contentType = "application/json; charset=utf-8";
    }
    var headerVars;
    headerVars = {
        Accept: contentType,
        "Content-Type": contentType,
        "Authorization": funjs_oauth2GetBearerHeader()
    };
    var result = {};
    if (loadingPlace !== false) {
        if (loadingPlace == true) {
            funjs_layoutLoadingShow(loadingProgressBar);
        } else if (loadingPlace.is("input") || loadingPlace.is("select")) {
            loadingPlace.addClass("hasLoadingImage");
        } else if ($(loadingPlace).is("div")) {
            funjs_layoutLoadingShowIn($(loadingPlace), loadingProgressBar);
        }
    }

    if (urlEnum.method === "post") {
        $.ajax({
            headers: headerVars,
            type: urlEnum.method,
            url: urlEnum.href,
            data: JSON.stringify(object),
            contentType: contentType,
            dataType: urlEnum.responseDataType,
            async: isAsync,
            success: function (data, status, jqXHR) {

                if (loadingPlace !== false) {
                    if (loadingPlace == true) {
//                        funjs_layoutLoadingHide();
                    } else if (loadingPlace.is("input") || loadingPlace.is("select")) {
                        loadingPlace.removeClass("hasLoadingImage");
                    } else if ($(loadingPlace).is("div")) {
//                        funjs_layoutLoadingHide();
                    }
                }

                if (jqXHR.getResponseHeader("token") != null) {
                    varjs.page.token = jqXHR.getResponseHeader("token");
                }

                if (silent == true) {
                    result.data = data;
                    result.status = "200";
                } else {
                    result = data;
                }

//                console.log("**success loadingPlace:" + loadingPlace + " url:" + url + " silent:" + silent + " successFunction:" + (successFunction != null) + " result:" + result);

                if (successFunction != null) {
                    successFunction.apply(null, [result]);
                }
                if ((loadingPlace) && (!loadingProgressBar)) {
                    funjs_layoutLoadingHide();

                }
            },
            error: function (data) {
                if (loadingPlace !== false) {
                    if (loadingPlace == true) {
                        funjs_layoutLoadingHide();
                    } else if (loadingPlace.is("input") || loadingPlace.is("select")) {
                        loadingPlace.removeClass("hasLoadingImage");
                    } else if ($(loadingPlace).is("div")) {
                        funjs_layoutLoadingHide();
                    }
                }
                funjs_commonCheckAjaxResponse(data);
                ajaxStatus = "400";
                var responseData = jQuery.parseJSON(data.responseText);



                if (silent == true) {
                    result.data = data.responseJSON;
                    result.status = "400";

                } else {
                    result = data;
                    funjs_popupShowError(responseData, loadingPlace);
                }

                if (failureFunction != null) {
                    failureFunction.apply(null, [result]);
                }


            },
            failure: function (errMsg) {


                if (loadingPlace !== false) {
                    if (loadingPlace == true) {
                        funjs_layoutLoadingHide();
                    } else if (loadingPlace.is("input") || loadingPlace.is("select")) {
                        loadingPlace.removeClass("hasLoadingImage");
                    } else if ($(loadingPlace).is("div")) {
                        funjs_layoutLoadingHide();
                    }
                }
                alert(errMsg);
            }
        });
    } else {

        if (object != null) {
            $.ajax({
                headers: headerVars,
                type: urlEnum.method,
                url: urlEnum.href,
                data: object,
                contentType: contentType,
                dataType: urlEnum.responseDataType,
                async: isAsync,
                success: function (data) {

                    result = data;
                    funjs_layoutLoadingHide();
                },
                error: function (data) {
                    funjs_commonCheckAjaxResponse(data);
                    ajaxStatus = "400";
                    funjs_popupShowAlert("fail", funjs_translateKey("businessexception.title"), data, false);
                    funjs_layoutLoadingHide();
                },
                failure: function (errMsg) {
                    alert(errMsg);
                    funjs_layoutLoadingHide();
                }
            });
        } else {
            $.ajax({
                headers: headerVars,
                type: urlEnum.method,
                url: urlEnum.href,
                contentType: contentType,
                dataType: urlEnum.responseDataType,
                async: isAsync,
                success: function (data) {

                    result = data;
                    funjs_layoutLoadingHide();
                },
                error: function (data) {
                    result = data;
                    funjs_commonCheckAjaxResponse(data);
                    ajaxStatus = "400";
                    if (popupErrorShow) {
                        funjs_popupShowAlert("fail", funjs_translateKey("businessexception.title"), data, false);
                    }
                    funjs_layoutLoadingHide();
                },
                failure: function (errMsg) {
//                    alert(errMsg);
                    funjs_layoutLoadingHide();
                }
            });
        }

    }
    if (ajaxStatus == "400") {
        result["status"] = "400";
    }
    return result;
}



// برای جک و بررسی درخواست به سرور
function funjs_commonCheckAjaxResponse(data) {
    if (data.status == 401) {
        var url = window.location.href;
        if (url.split("/login").length == 1) {
            funjs_cookieRemove("bpToken");
            window.location.href = varjs.page.loginUrl + ((url.split("#").length > 1) ? ("#" + url.split("#")[1]) : "");
        }

    }
}
// گرفتن اطلاعات داخل کوکی
function funjs_commonCookieGet(name)
{
    var re = new RegExp(name + "=([^;]+)");
    var value = re.exec(document.cookie);
    return (value != null) ? unescape(value[1]) : null;
}
// اضافه کردن اطلاعات مورد نظر در کوکی
function funjs_commonCookieSet(cookieName, cookieValue)
{
    var d = new Date();
    d.setTime(d.getTime() + (365 * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cookieName + "=" + cookieValue + "; " + expires + "domain=" + window.location.host + ";path=/";
}

function funjs_commonDetectClientBrowser()
{
    var N = navigator.appName, ua = navigator.userAgent, tem;
    var M = ua.match(/(opera|chrome|safari|firefox|msie)\/?\s*(\.?\d+(\.\d+)*)/i);
    if (M && (tem = ua.match(/version\/([\.\d]+)/i)) != null)
        M[2] = tem[1];
    M = M ? [M[1], M[2]] : [N, navigator.appVersion, '-?'];
    return M;
}
// برای کلید چاپ صفحات مشاهده اطلاعات بصورت صفحه پرینت
function funjs_commonCallPrint(parentPanel) {
    if (typeof (parentPanel) === 'undefined') {
        parentPanel = '.box-body';
    }
    var prtContent = $(parentPanel);
    var obj = funjs_formGetObject($(".panel-body .form-horizontal").attr("id"));
    var WinPrint = window.open('', '', 'width=800,height=650,scrollbars=1,menuBar=1');
    var headContent = document.getElementsByTagName('head')[0].innerHTML;
    var url = prtContent[0].baseURI;
    var crudName = url.split("/")[4];

    var str = '<html><head>' + headContent + "</head><body onload='funjs_removeUnUseableAreas()'><label style='margin-right:10px;font-size:15px;margin-top:19px;'  data-localize='acl." + crudName + ".view'></label>" + prtContent[0].innerHTML + "<input id='ptrbtn' style='margin-right:83px;padding: 7px;font-size: 12px;' type='button' value='پرینت' onclick='funjs_commonPrintpage()'/></body></html>";

    WinPrint.document.write(str);
    WinPrint.document.close();
    WinPrint.focus();
}
// کلید کنسل صفحات پرینت
function funjs_removeUnUseableAreas() {

    $(".submitBtn").remove();
    $(".nextBtnForm").remove();
    $(".cancelBtn").remove();
    $(".printBtn").remove();

    $(".btn").remove();
    $(".sf-steps").hide();
    $(".sf-content").show();
}
// کلید پرینت صفحات چاپ
function funjs_commonPrintpage() {
    document.title = "";
    $("#ptrbtn").remove();
    window.print();
}
// گرفتن تاریخ روز جاری
function funjs_commonGetNowDate() {
    var today = new Date();
    var todayMonth = today.getMonth() + 1;
    var todayDay = today.getDate();
    var todayYear = today.getFullYear();
    var dateLtr = JalaliDateIct.gregorianToJalali(todayYear, todayMonth, todayDay);
    var datertl = dateLtr.split("-")[0] + "/" + dateLtr.split("-")[1] + "/" + dateLtr.split("-")[2];
    return datertl;
}
// تغییر ادرس صفحه وب
function funjs_commonGoToUrl(url) {
    window.location.href = url;
}
// باز کردن ادرس جدید در تب جدیدی از مرورگر
function funjs_commonGoToUrlInNewTab(url) {
    var win = window.open(url, '_blank');
    win.focus();
}
// حدف مقدار مورد نظر در ارایه
function funjs_commonDropValueFromArray(array, item) {
    var i = array.indexOf(item);
    if (i != -1) {
        array.splice(i, 1);
    }
    return array;
}
// چک کردن وجود داشتن مقدار مورد نظر در ارایه مورد نظر
function funjs_commonCheckArrayContainValue(array, value) {

    array.sort(function (a, b) {
        return a - b
    });
    var i = array.indexOf(value);
    if (i != -1) {
        return true;
    }
    return false;
}
// نمایش خطا در جدول
function stringifyBusinessErrorMessage(errors) {
    var messages = "<table class='table table-bordered col-md-8 col-md-offset-2'>";
    $.each(errors, function (key, val) {
        messages += "<tr>";
        if (key != "error") {
            messages += "<td id='key'>" + funjs_translateKey(key) + "</td>"
        }
        messages += "<td>" + funjs_translateKey(val) + "</td>";
        messages += "</tr>";
    });
    messages += "</table>";
    return messages;
}
// بررسی فارسی بودن مقدار مورد نظر برای تنظیم راست و چپ بودن برای نمایش
function funjs_commonIsRtlText(val) {
    var code = val.charCodeAt(0);
    if (code > 33 && code < 48) {
        return false;
    }
    return true;
}
// گرفتن ادرس جاری صفحه
function funjs_commonGetCurrentRoute() {
    var url = window.location.href;
    var s = url.split("/");
    var r = "";
    for (var i = 3; i < 6; i++) {
        r += s[i];
        if (i != 5) {
            r += "/";
        }
    }
    return r;
}
String.prototype.hashCode = function () {
    var hash = 0;
    if (this.length == 0)
        return hash;
    for (i = 0; i < this.length; i++) {
        char = this.charCodeAt(i);
        hash = ((hash << 5) - hash) + char;
        hash = hash & hash; // Convert to 32bit integer
    }
    return hash;
};
// csv  تبدیل ارایه به
function funjs_commonArrayToCsv(array) {
    var ret = "";
    if (array.length > 0) {
        ret = array[0];
    }
    for (var i = 1; i < array.length; i++) {
        ret += "," + array[i];
    }
    return ret;
};
// ثابت کردن ادرس برای صفحه مورد نظر
function funjs_commonFixUrl(url, withContextPath, withToken) {
    if (withContextPath) {
        if (withToken) {
            if (url.split("?").length > 1) {
                return varjs.page.url.contextPath + url + "&access_token=" + funjs_oauth2GetToken().access_token;
            }
            return varjs.page.url.contextPath + url + "?access_token=" + funjs_oauth2GetToken().access_token;
        }
        return varjs.page.url.contextPath + url;
    }
    if (withToken) {
        if (url.split("?").length > 1) {
            return url + "&access_token=" + funjs_oauth2GetToken().access_token;
        }
        return url + "?access_token=" + funjs_oauth2GetToken().access_token;
    }
    return url;
}
// خروج از حساب کاربری
function funjs_commonLogout() {
    var ret = funjs_oauth2Logout();
    if (ret) {
        window.location.href = varjs.page.loginUrl;
    }
}
//ها tag برای پر کردن فیلد های 
// inputId ایدی فیلد
// csvVal  csv مقدار به صورت
function funjs_taginputSetValues(inputId, csvVal) {
    $("#" + inputId).val(csvVal);
    var tagElement = $("#" + inputId).next(".taginput-wrapper");
    var values = csvVal.split(",");
    for (var i = 0; i < values.length; i++) {
        tagElement.prepend('<span class="taginput-tag btn btn-default btn-sm"><span>' + values[i] + '</span> <span class="glyphicon glyphicon-remove"></span></span>');
    }
}
// ترتیب کردن مقادیر ارایه
function funjs_commonSortArray(arr) {

    var i, j, t, c;
    for (i = arr.length - 2; i >= 0; i--) {
        c = 0;
        for (j = 0; j <= i; j++)
            if (arr[j] > arr[j + 1]) {
                t = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = t;
//                c++;
            }
//        if (c == 0)
//            break;
    }
    return arr;

}
// ترتیب کردن مقادیر ارایه براساس ایتم مورد نظر
function funjs_commonSort2DIntegerArray(arr, index) {
    var i, j, t;
    for (i = arr.length - 2; i >= 0; i--) {
        for (j = 0; j <= i; j++) {
            if (parseInt(arr[j][index]) >= parseInt(arr[j + 1][index])) {

                t = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = t;
            }
        }
    }
    return arr;
}
// اضافه کردن کاما به قیمت
function funjs_commonPriceApplyCommaSeparated(val) {
    if (val == null || val == "") {
        return 0;
    }
    while (/(\d+)(\d{3})/.test(val.toString())) {
        val = val.toString().replace(/(\d+)(\d{3})/, '$1' + ',' + '$2');
    }
    return val;
}
// حذف کردن کاما از قیمت
function funjs_commonPriceRemoveCommaSeprated(val) {
    if (val == null || val == "") {
        return 0;
    }
    var value;
    if (val.toString().length < 4) {
        value = parseFloat(val);
    } else {
        value = parseFloat(val.replace(/,/g, ''));
    }
    return value;
}
// شروع نمودار لودینگ
// url ادرس
// inprogressMethod مدل لودینگ
// afterMethod متد و اتفاق های بعد از لودینگ
var stopTimeout = false;
function funjs_commonProgressBarStart(url, inprogressMethod, afterMethod) {
    stopTimeout = false;
    funjs_commonProgressBarShow(url, inprogressMethod, afterMethod);
}
// پایان دادن به نمودار لودینگ
function funjs_commonProgressBarStop() {
    stopTimeout = true;
    funjs_layoutLoadingHide();
}
// نمایش نمودار لودینگ
// url ادرس
// inprogressMethod مدل لودینگ
// afterMethod متد و اتفاق های بعد از لودینگ
function funjs_commonProgressBarShow(url, inprogressMethod, afterMethod) {
    if (!stopTimeout) {
//        console.log("funjs_commonProgressBarShow:" + url);
        timoutRef = setTimeout(function () {
            funjs_commonSendAjaxRequest("post", "json", url, {}, false, function (result) {
                progressBarModel = result.data;
//                console.log(progressBarModel);
//                console.log("**funjs_commonProgressBarShow: progressTitle:" + progressBarModel.progressTitle + " progressPercentage:" + progressBarModel.progressPercentage + " progressBarModel:" + progressBarModel);
                inprogressMethod.apply(null, [progressBarModel]);
                if (progressBarModel.logExcelImport_resultStatus == "SUCCESS" || progressBarModel.logExcelImport_resultStatus == "FAILED") {
                    funjs_commonProgressBarStop();
                    if (afterMethod != null) {
                        afterMethod.apply(null, [progressBarModel]);
                    }
                } else {
                    funjs_commonProgressBarShow(url, inprogressMethod, afterMethod);
                }
            }, null, true);
        }, 1000);
    }
}
// کپی کردن مقدار یک ابجکت در ابجکت جدید بدون تغییر در ابجکت اصلی بعد از تغییرات روی ابجکت جدید 
function funjs_commonCopyObject(obj) {
    return JSON.parse(JSON.stringify(obj));
}