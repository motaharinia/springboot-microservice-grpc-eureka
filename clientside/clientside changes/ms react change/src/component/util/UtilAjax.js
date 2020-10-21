import React, {Component} from 'react';
import {Config, UtilModal, UtilCommon, UtilOAuth, RepoVar,Microservice, CustomError} from '../ComponentIndex';
import $ from 'jquery';

class UtilAjax extends React.Component {

    static requestTypeEnum = {
        //enumKey:data
        "GET": "get",
        "POST_HTML": "postHtml",
        "POST_JSON": "postJson",
        "OPTION": "option",
        "PUT": "put"
    };

    static responseDataTypeEnum = {
        //enumKey:data
        "HTML": "html",
        "JSON": "json",
        "XML": "xml",
        "SCRIPT": "script",
        "JSONP": "jsonp",
        "TEXT": "text",
        "BINARY": "binary"
    };

    static mimeTypeEnum = {
        //enumKey:data
        "XML": "application/xml",
        "JSON": "application/json",
        "TEXT": "text/plain",
        "HTML": "text/html",
        "IMAGE": "image/png"
    };

    static sendRequest(thisObject, requestType, responseDataType, responseAcceptMimeType, url, data, isAsync, isWithCredentials, beforeSendXhrHeaderObject, loadingPlaceObject, isSilent, failureFunction, successFunction) {
        UtilCommon.checkUndefinedNullEmpty(requestType, true, true, true);
        UtilCommon.checkUndefinedNullEmpty(responseDataType, true, true, true);
        UtilCommon.checkUndefinedNullEmpty(responseAcceptMimeType, true, true, true);
        UtilCommon.checkUndefinedNullEmpty(url, true, true, true);
        UtilCommon.checkUndefinedNullEmpty(data, false, false, true);
        UtilCommon.checkUndefinedNullEmpty(isAsync, true, false, true);
        UtilCommon.checkUndefinedNullEmpty(isWithCredentials, true, false, true);
        UtilCommon.checkUndefinedNullEmpty(beforeSendXhrHeaderObject, false, false, true);
        UtilCommon.checkUndefinedNullEmpty(loadingPlaceObject, false, false, true);
        UtilCommon.checkUndefinedNullEmpty(isSilent, true, false, true);
        UtilCommon.checkUndefinedNullEmpty(failureFunction, false, false, true);
        UtilCommon.checkUndefinedNullEmpty(successFunction, false, false, true);

        UtilCommon.checkEnumKey(this.requestTypeEnum, requestType, true);
        UtilCommon.checkEnumKey(this.responseDataTypeEnum, responseDataType, true);
        UtilCommon.checkEnumKey(this.mimeTypeEnum, responseAcceptMimeType, true);

        // if(!this.hostReachable()){
        //   alert("Connection lost!!");
        //   return false;
        // }

        var requestContentType = "application/x-www-form-urlencoded; charset=UTF-8";
        if (responseDataType === this.responseDataTypeEnum.JSON) {
            requestContentType = "application/json";
        }

        var headersObject = {}
        if (requestType !== this.requestTypeEnum.GET) {
            headersObject = {
                "Accept": responseAcceptMimeType,
                "Content-Type": requestContentType
            }
            if (!isWithCredentials) {
                headersObject["Authorization"] = UtilOAuth.getBearerHeader();
            }
        } else {
            url = UtilOAuth.addTokenToUrl(url);
        }

        if (requestType === this.requestTypeEnum.POST_JSON) {
            data = JSON.stringify(data)
        }

        if ((requestType === this.requestTypeEnum.POST_JSON) || (requestType === this.requestTypeEnum.POST_HTML)) {
            requestType = "POST";
        }
        var result = {};
        $.ajax({
            beforeSend: function (xhr) {
                if (beforeSendXhrHeaderObject != null) {
                    Object.keys(beforeSendXhrHeaderObject).forEach(function (key, index) {
                        xhr.setRequestHeader(key, beforeSendXhrHeaderObject[key]);
                    });
                    xhr.setRequestHeader("Content-Type", requestContentType);
                }
                xhr.withCredentials = isWithCredentials;
            },
            headers: headersObject,
            type: requestType,
            url: url,
            // مدت زمان به میلی ثانیه که در صورت جواب نگرفتن در این مدت صفحه سایت درحال به روز رسانی نمایش داده می شود
            timeout: 100000,
            cache: true,
            data: data,
            // processData:false,
            contentType: requestContentType,
            dataType: responseDataType,
            async: isAsync,
            success: function (data, textStatus, jqXHR) {
                result = {
                    "data": data,
                    "textStatus": textStatus,
                    "jqXHR": jqXHR,
                    "status": 200
                };
                if (successFunction !== null) {
                    successFunction.apply(thisObject, [result]);
                }
                return result;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                switch (jqXHR.status) {
                    case 0:
                        // alert(url);
                        // alert(Config.apiToken)
                        // console.log("UtilAjax.error jqXHR.status===0 jqXHR :", jqXHR);
                        // console.log("UtilAjax.error jqXHR.getAllResponseHeaders() :", jqXHR.getAllResponseHeaders());
                        // console.log("UtilAjax.error textStatus :", textStatus);
                        // console.log("UtilAjax.error errorThrown :", errorThrown);
                        if (url.indexOf(Config.apiToken) === -1) {
                            var returnUrl = RepoVar.get(RepoVar.keyEnum.PAGE).url.urlFull;
                            UtilCommon.goToUrl("/serverCheckReachable/#/?returnUrl=" + returnUrl);
                            break;
                        }
                    case 400:
                        result = {
                            "errorThrown": errorThrown,
                            "textStatus": textStatus,
                            "jqXHR": jqXHR,
                            "status": jqXHR.status,
                            "data": jqXHR.responseJSON,
                            "url": url
                        };
                        if (failureFunction !== null) {
                            failureFunction.apply(thisObject, [result]);
                        }
                        var errorObject = CustomError.getErrorObject(result)
                        if (errorObject.errorDetail !== undefined && errorObject.errorDetail !== null && errorObject.errorDetail.search("ORA-08177") > -1) {
                            if (errorObject.errorMode === "DEVELOPMENT") {
                                UtilModal.open("خطای دریافتی شماره : " + errorObject.errorId, "این صفحه قبلا فراخوانی شده است (فراخوانی بیش از حد)", errorObject.errorDetail, true, "");
                            } else {
                                UtilModal.open("خطای دریافتی شماره : " + errorObject.errorId, "این صفحه قبلا فراخوانی شده است (فراخوانی بیش از حد)", "", true, "");
                            }
                        } else {
                            if (isSilent) {
                                return result;
                            } else {
                                if (errorObject.errorMode === "DEVELOPMENT") {
                                    UtilModal.open("خطای دریافتی شماره : " + errorObject.errorId, thisObject.props.t.translate(errorObject.errorMessage), errorObject.errorDetail, true, "");
                                } else {
                                    UtilModal.open("خطای دریافتی شماره : " + errorObject.errorId, thisObject.props.t.translate(errorObject.errorMessage), "", true, "");
                                }
                            }
                        }
                        break;
                    case 401:
                        if (url.indexOf(Config.apiToken) === -1) {
                            UtilModal.openNotify("error", "خطای عدم لاگین", "شما برای فراخوانی آدرس \n" + url + "\n نیاز به لاگین دارید", false, 2000);
                            setTimeout(function () {
                                UtilCommon.goToUrl("/");
                            }, 2000);
                        } else {
                            result = {
                                "errorThrown": errorThrown,
                                "textStatus": textStatus,
                                "jqXHR": jqXHR,
                                "status": jqXHR.status,
                                "data": jqXHR.responseJSON,
                                "url": url
                            };
                            return result;
                        }

                        break;
                    case 403:
                        if (url.indexOf(Config.apiToken) === -1) {
                            UtilModal.openNotify("error", "خطای عدم دسترسی به آدرس", "شما به آدرس \n" + url + "\n دسترسی ندارید", false, 2000);
                            setTimeout(function () {
                                UtilCommon.goToUrl("/");
                            }, 2000);
                        } else {
                            result = {
                                "errorThrown": errorThrown,
                                "textStatus": textStatus,
                                "jqXHR": jqXHR,
                                "status": jqXHR.status,
                                "data": jqXHR.responseJSON,
                                "url": url
                            };
                            return result;
                        }
                        // UtilModal.openNotify("error", "خطای عدم دسترسی به آدرس", <div style={{ "wordWrap": "break-word" }}>{"شما به آدرس " + url + " دسترسی ندارید"} </div>, false, 2000);
                        break;
                    case 500:
                        var errorObject = CustomError.getErrorObject(result);
                        UtilModal.openNotify("error", "خطای سرور شماره " + errorObject.errorId, "شما در فراخوانی آدرس \n" + url + "\n خطای سرور دارید", false, 2000);
                        break;
                    default:
                        alert("کد خطای خاص : " + jqXHR.status);
                }

            },
            failure: function (errMsg) {
                alert("UtilAjax.sendRequest:failure");
                alert(errMsg);
            }
        });
        return result;
    }



    static msRequest(thisObject,urlEnum, data, isAsync, isWithCredentials, beforeSendXhrHeaderObject, loadingPlaceObject, isSilent, failureFunction, successFunction) {


        UtilCommon.checkUndefinedNullEmpty(urlEnum.href, true, true, true);
        UtilCommon.checkUndefinedNullEmpty(urlEnum.method, true, true, true);
        UtilCommon.checkUndefinedNullEmpty(urlEnum.responseDataType, true, true, true);
        UtilCommon.checkUndefinedNullEmpty(urlEnum.mimeType, true, true, true);
        UtilCommon.checkUndefinedNullEmpty(data, false, false, true);
        UtilCommon.checkUndefinedNullEmpty(isAsync, true, false, true);
        UtilCommon.checkUndefinedNullEmpty(isWithCredentials, true, false, true);
        UtilCommon.checkUndefinedNullEmpty(beforeSendXhrHeaderObject, false, false, true);
        UtilCommon.checkUndefinedNullEmpty(loadingPlaceObject, false, false, true);
        UtilCommon.checkUndefinedNullEmpty(isSilent, true, false, true);
        UtilCommon.checkUndefinedNullEmpty(failureFunction, false, false, true);
        UtilCommon.checkUndefinedNullEmpty(successFunction, false, false, true);


        UtilCommon.checkEnumKey(Microservice.requestTypeEnum, urlEnum.method, true);
        UtilCommon.checkEnumKey(Microservice.responseDataTypeEnum, urlEnum.responseDataType, true);
        UtilCommon.checkEnumKey(Microservice.mimeTypeEnum, urlEnum.mimeType, true);

        // if(!this.hostReachable()){
        //   alert("Connection lost!!");
        //   return false;
        // }


        var requestContentType = "application/x-www-form-urlencoded; charset=UTF-8";
        if (urlEnum.responseDataType === Microservice.responseDataTypeEnum.JSON) {
            urlEnum.responseDataType = "application/json";
        }

        console.log(urlEnum);
        var url = urlEnum.href;

        var headersObject = {}
        if (urlEnum.method !== Microservice.requestTypeEnum.GET) {
            headersObject = {
                "Accept": urlEnum.mimeType,
                "Content-Type": requestContentType
            }
            if (!isWithCredentials) {
                headersObject["Authorization"] = UtilOAuth.getBearerHeader();
            }

        } else {
            url = UtilOAuth.addTokenToUrl(urlEnum.href);
        }

        if (urlEnum.method === Microservice.requestTypeEnum.POST_JSON) {
            data = JSON.stringify(data)
        }

        if ((urlEnum.method === Microservice.requestTypeEnum.POST_JSON) || (urlEnum.method === Microservice.requestTypeEnum.POST_HTML)) {
            urlEnum.method = "POST";
        }
        var result = {};
        $.ajax({
            beforeSend: function (xhr) {
                if (beforeSendXhrHeaderObject != null) {
                    Object.keys(beforeSendXhrHeaderObject).forEach(function (key, index) {
                        xhr.setRequestHeader(key, beforeSendXhrHeaderObject[key]);
                    });
                    xhr.setRequestHeader("Content-Type", requestContentType);
                }
                xhr.withCredentials = isWithCredentials;
            },
            headers: headersObject,
            type: urlEnum.method,
            url: url,
            // مدت زمان به میلی ثانیه که در صورت جواب نگرفتن در این مدت صفحه سایت درحال به روز رسانی نمایش داده می شود
            timeout: 100000,
            cache: true,
            data: data,
            // processData:false,
            contentType: requestContentType,
            dataType: urlEnum.responseDataType,
            async: isAsync,
            success: function (data, textStatus, jqXHR) {
                result = {
                    "data": data,
                    "textStatus": textStatus,
                    "jqXHR": jqXHR,
                    "status": 200
                };
                if (successFunction !== null) {
                    successFunction.apply(thisObject, [result]);
                }
                return result;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                switch (jqXHR.status) {
                    case 0:
                        // alert(url);
                        // alert(Config.apiToken)
                        // console.log("UtilAjax.error jqXHR.status===0 jqXHR :", jqXHR);
                        // console.log("UtilAjax.error jqXHR.getAllResponseHeaders() :", jqXHR.getAllResponseHeaders());
                        // console.log("UtilAjax.error textStatus :", textStatus);
                        // console.log("UtilAjax.error errorThrown :", errorThrown);
                        if (url.indexOf(Config.apiToken) === -1) {
                            var returnUrl = RepoVar.get(RepoVar.keyEnum.PAGE).url.urlFull;
                            UtilCommon.goToUrl("/serverCheckReachable/#/?returnUrl=" + returnUrl);
                            break;
                        }
                    case 400:
                        result = {
                            "errorThrown": errorThrown,
                            "textStatus": textStatus,
                            "jqXHR": jqXHR,
                            "status": jqXHR.status,
                            "data": jqXHR.responseJSON,
                            "url": url
                        };
                        if (failureFunction !== null) {
                            failureFunction.apply(thisObject, [result]);
                        }
                        var errorObject = CustomError.getErrorObject(result)
                        if (errorObject.errorDetail !== undefined && errorObject.errorDetail !== null && errorObject.errorDetail.search("ORA-08177") > -1) {
                            if (errorObject.errorMode === "DEVELOPMENT") {
                                UtilModal.open("خطای دریافتی شماره : " + errorObject.errorId, "این صفحه قبلا فراخوانی شده است (فراخوانی بیش از حد)", errorObject.errorDetail, true, "");
                            } else {
                                UtilModal.open("خطای دریافتی شماره : " + errorObject.errorId, "این صفحه قبلا فراخوانی شده است (فراخوانی بیش از حد)", "", true, "");
                            }
                        } else {
                            if (isSilent) {
                                return result;
                            } else {
                                if (errorObject.errorMode === "DEVELOPMENT") {
                                    UtilModal.open("خطای دریافتی شماره : " + errorObject.errorId, thisObject.props.t.translate(errorObject.errorMessage), errorObject.errorDetail, true, "");
                                } else {
                                    UtilModal.open("خطای دریافتی شماره : " + errorObject.errorId, thisObject.props.t.translate(errorObject.errorMessage), "", true, "");
                                }
                            }
                        }
                        break;
                    case 401:
                        if (url.indexOf(Config.apiToken) === -1) {
                            UtilModal.openNotify("error", "خطای عدم لاگین", "شما برای فراخوانی آدرس \n" + url + "\n نیاز به لاگین دارید", false, 2000);
                            setTimeout(function () {
                                UtilCommon.goToUrl("/");
                            }, 2000);
                        } else {
                            result = {
                                "errorThrown": errorThrown,
                                "textStatus": textStatus,
                                "jqXHR": jqXHR,
                                "status": jqXHR.status,
                                "data": jqXHR.responseJSON,
                                "url": url
                            };
                            return result;
                        }

                        break;
                    case 403:
                        if (url.indexOf(Config.apiToken) === -1) {
                            UtilModal.openNotify("error", "خطای عدم دسترسی به آدرس", "شما به آدرس \n" + url + "\n دسترسی ندارید", false, 2000);
                            setTimeout(function () {
                                UtilCommon.goToUrl("/");
                            }, 2000);
                        } else {
                            result = {
                                "errorThrown": errorThrown,
                                "textStatus": textStatus,
                                "jqXHR": jqXHR,
                                "status": jqXHR.status,
                                "data": jqXHR.responseJSON,
                                "url": url
                            };
                            return result;
                        }
                        // UtilModal.openNotify("error", "خطای عدم دسترسی به آدرس", <div style={{ "wordWrap": "break-word" }}>{"شما به آدرس " + url + " دسترسی ندارید"} </div>, false, 2000);
                        break;
                    case 500:
                        var errorObject = CustomError.getErrorObject(result);
                        UtilModal.openNotify("error", "خطای سرور شماره " + errorObject.errorId, "شما در فراخوانی آدرس \n" + url + "\n خطای سرور دارید", false, 2000);
                        break;
                    default:
                        alert("کد خطای خاص : " + jqXHR.status);
                }

            },
            failure: function (errMsg) {
                alert("UtilAjax.sendRequest:failure");
                alert(errMsg);
            }
        });
        return result;
    }



    static hostReachable() {
        // Handle IE and more capable browsers
        var xhr = new (window.ActiveXObject || XMLHttpRequest)("Microsoft.XMLHTTP");
        // Open new request as a HEAD to the root hostname with a random param to bust the cache
        xhr.open("HEAD", "//" + window.location.hostname + "/?rand=" + Math.floor((1 + Math.random()) * 0x10000), false);
        // Issue request and handle response
        try {
            xhr.send();
            return (xhr.status >= 200 && (xhr.status < 300 || xhr.status === 304));
        } catch (error) {
            return false;
        }
    }

}

export {
    UtilAjax
};
