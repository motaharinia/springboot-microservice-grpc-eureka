import FineUploaderTraditional from "fine-uploader-wrappers";


const changeActionEnum = {
    "ADD": "ADD", //فایل جدید آپلود شده است
    "DELETE": "DELETE",  //فایل حذف شده است
};


const typeEnum = {
    "SINGLE": "SINGLE", //آپلودر تک فایل. در این حالت نیاز به پر کردن پراپس ولیدیشن آیتم لیمیت نیست.
    "MULTI": "MULTI", //آپلودر چندتایی. در این حالت با پراپس ولیدیشن آیتم لیمیت میتوان تعداد را هم محدود کرد
};

const subSystemEnum = {
    "COMMON": "COMMON",
    "ESHOP": "eshop"
};

const entityEnum = {
    "MEMBER": "member",
    "SOCIAL_GROUP": "socialGroup",
    "MEMBER_SHIP_REQUEST": "membershipRequest",
    "PRODUCT_REQUEST": "productRequest",
    "CART": "cart",
    "USER_GIFT_REQUEST": "userGiftRequest",
    "USER_CREDIT_REQUEST_SELF": "userCreditRequest",
    "USER_CREDIT_REQUEST_DONATE": "userCreditRequest",
    "PURCHASE_ORDER_CARRIER_GROUP": "purchaseOrderCarrierGroup",

};

const fileKindFolderEnum = {
    "SOCIAL_GROUP_LOGO": "logo",
    "MEMBER_SHIP_REQUEST_PERSONALITY": "personality",
    "ATTACHMENT": "attachment",
    "PRODUCT_REQUEST_COVER": "cover",
    "PRODUCT_REQUEST_QRBARCODE": "qrbarcode",
    "PRODUCT_REQUEST_IMAGE3DFILE": "image3dfile",
    "USER_GIFT_REQUEST_IMAGE": "image",
    "USER_CREDIT_REQUEST_SELF_IMAGE": "image",
    "USER_CREDIT_REQUEST_DONATE_IMAGE": "image",
    "DELIVERY_CONFIRM_FORM": "deliveryconfirmform"

};



const statusEnum = {
    "ADDED": "ADDED", //فایل جدید آپلود شده است
    "EXISTED": "EXISTED", //فایل از قبل وجود داشته و بدون تغییر مانده است
    "SUBMITTING": "submitting", //وقتی که یک فایل برای اضافه شدن به آپلودر انتخاب میشود
    "SUBMITTED": "submitted", //وقتی که یک فایل به آپلودر اضافه شود
    "REJECTED": "rejected", //
    "QUEUED": "queued", //
    "CANCELED": "canceled", //وقتی که یک فایل در آپلودر کنسل شود
    "PAUSED": "paused", //وقتی یک آپلود موقتا متوقف شود
    "UPLOADING": "uploading", //وقتی یک فایل در حال آپلود است
    "UPLOAD_FINALIZING": "upload finalizing", //در حال اتمام آپلود
    "UPLOAD_RETRYING": "retrying upload", //سعی مجدد برای آپلود
    "UPLOAD_SUCCESSFUL": "upload successful", //آپلود یک فایل با موفقیت تمام شده است
    "UPLOAD_FAILED": "upload failed", //آپلود یک فایل با موفقیت انجام نشده است
    "DELETE_FAILED": "delete failed", //حذف فایل آپلود شده با موفقیت انجام نشده است
    "DELETING": "deleting", //در حال حذف فایل آپلود شده
    "DELETED": "DELETED", //حذف فایل آپلود شده با موفقیت انجام شده است
};


const actionEnum = {
    "SUBMIT": "SUBMIT", //وقتی که یک فایل برای اضافه شدن به آپلودر انتخاب میشود
    "SUBMITTED": "SUBMITTED", //وقتی که یک فایل به آپلودر اضافه شود
    "SUBMIT_DELETE": "SUBMIT_DELETE", //وقتی که یک فایل برای حذف از آپلودر انتخاب شود
    "RESUME": "RESUME", //وقتی که ادامه آپلود یک فایل در آپلودر انجام شود
    "MANUAL_RETRY": "MANUAL_RETRY", //وقتی که ادامه آپلود یک فایل در آپلودر به صورت دستی توسط کاربر انجام شود
    "AUTO_RETRY": "AUTO_RETRY", //وقتی که ادامه آپلود یک فایل در آپلودر به صورت اتوماتیک انجام شود
    "CANCEL": "CANCEL", //وقتی که یک فایل در آپلودر کنسل شود
    "COMPLETE": "COMPLETE", //وقتی که یکی از آپلود فایلها کامل گردد
};



const getNewUploader = (subSystem,entity,type,validationExtensionList,validationSizeLimit,validationItemLimit,fileNameList,onStatusChange) => {

    let validationObject = {};
    if (validationExtensionList !== undefined) {
        validationObject["allowedExtensions"] = validationExtensionList;
    }
    if (validationSizeLimit !== undefined) {
        validationObject["sizeLimit"] = validationSizeLimit;
    }
    if (type === typeEnum.SINGLE) {
        validationObject["itemLimit"] = 1;
    } else {
        if (validationItemLimit !== undefined) {
            validationObject["itemLimit"] = validationItemLimit;
        }
    }

    let linitialStateUploader = new FineUploaderTraditional({
        options: {
            chunking: {
                enabled: true,
                mandatory: true,
                concurrent: {
                    enabled: false
                },
                partSize: 2 * 1024 * 1024  //2mb
            },
            deleteFile: {
                method: "post",
                enabled: true,
                endpoint: "/fso/deleteUploadedFile"
            },
            request: {
                customHeaders: {
                    "Authorization": "Basic 999jaGFkbWluOkNieWxjZTY3"
                },
                endpoint: "http://localhost:8082/fso/upload/" + subSystem + "/" + entity + "/fine"
            },
            cors: {
                autoUpload: false,
                //all requests are expected to be cross-domain requests
                expected: true,
                //if you want cookies to be sent along with the request
                sendCredentials: true,
                maxConnections: 1
            },
            resume: {
                enabled: true
            },
            retry: {
                enableAuto: false,
                maxAutoAttempts: 2,
            },
            validation: validationObject,
            text: {
                sizeSymbols: ["کیلوبایت", "مگابایت", "گیگابایت", "ترابایت", "PB", "EB"]
            },
            messages: {
                emptyError: "فایل خالی است",
                maxHeightImageError: "ارتفاع تصویر زیاد است",
                maxWidthImageError: "عرض تصویر زیاد است",
                minHeightImageError: "ارتفاع تصویر کم است",
                minWidthImageError: "عرض تصویر کم است",
                minSizeError: "حجم فایل کم است. حداقل حجم فایل {minSizeLimit} باید باشد",
                noFilesError: "هیچ فایلی آپلود نشده است",
                onLeave: "فایل در حال آپلود است. با ترک آپلودر آپلود کنسل میشود",
                retryFailTooManyItemsError: "تلاش مجدد آپلود بی نتیجه بود. تعداد فایل آپلود به حداکثر خود رسیده است",
                sizeError: "حجم فایل زیاد است. حداکثر حجم فایل {sizeLimit} میتواند باشد",
                tooManyItemsError: "محدودیت در تعداد آپلود فایل. تعداد فایل مجاز برای آپلود {itemLimit} است",
                typeError: "نوع فایل آپلود غیر مجاز است. پسوندهای فایل مجاز قابل آپلود {extensions} میباشد",
                unsupportedBrowserIos8Safari: "خطا در آپلود. لطفا به جای مرورگر ios8safari از ios8chrome استفاده نمایید"
            },
            callbacks: {
                onUploadChunk: function (id, name, chunkData) {

                },
                onValidate: function (fileData) {
                    return fileNameList.indexOf(fileData.name) < 0;
                },
                onError: function (id, name, errorReason, xhr) {
                    // UtilModal.open("خطا", errorReason, "", true, "");
                },
                onStatusChange: onStatusChange,
            }
        }
    });
return linitialStateUploader;
};




export {changeActionEnum,typeEnum, statusEnum, actionEnum, subSystemEnum, entityEnum, fileKindFolderEnum, getNewUploader}

