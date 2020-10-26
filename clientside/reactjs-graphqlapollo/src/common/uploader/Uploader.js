import React, {useState} from "react";
import FineUploaderTraditional from "fine-uploader-wrappers";
import Gallery from "react-fine-uploader";
import "react-fine-uploader/gallery/gallery.css";

const typeEnum = {
    "SINGLE": "SINGLE", //آپلودر تک فایل. در این حالت نیاز به پر کردن پراپس ولیدیشن آیتم لیمیت نیست.
    "MULTI": "MULTI", //آپلودر چندتایی. در این حالت با پراپس ولیدیشن آیتم لیمیت میتوان تعداد را هم محدود کرد
};

const subSystemEnum = {
    "COMMON": "common",
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

function Uploader(props) {


    const statusEnum = {
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
        "DELETED": "deleted", //حذف فایل آپلود شده با موفقیت انجام شده است
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



    //وضعیتها
    //"SUBMIT": وقتی که یک فایل برای اضافه شدن به آپلودر انتخاب میشود
    //"SUBMITTED": وقتی که یک فایل به آپلودر اضافه شود
    //"SUBMIT_DELETE": وقتی که یک فایل برای حذف از آپلودر انتخاب شود
    //"RESUME": وقتی که ادامه آپلود یک فایل در آپلودر انجام شود
    //"MANUAL_RETRY": وقتی که ادامه آپلود یک فایل در آپلودر به صورت دستی توسط کاربر انجام شود
    //"AUTO_RETRY": وقتی که ادامه آپلود یک فایل در آپلودر به صورت اتوماتیک انجام شود
    //"CANCEL": وقتی که یک فایل در آپلودر کنسل شود
    //"COMPLETE": وقتی که یکی از آپلود فایلها کامل گردد
    const onStatusChange = (id, oldStatus, newStatus, v1, v2) => {
        const {uploader} = uploaderData;
        uploader.methods.getUploads().map(function (obj, index) {
            let fileObject = JSON.parse(JSON.stringify(obj));
            if (fileObject.id === id) {
                fileObject["fileData"] = uploader.methods.getFile(id);
            }
            if (fileObject.status !== statusEnum.DELETED) {
                uploaderData["objectSelectedList"].push(fileObject)
            }
        });
        setUploaderData({
            ...uploaderData

        }, function () {
            if (props.onChange !== undefined) {
                props.onChange(JSON.parse(JSON.stringify(uploaderData)), id, newStatus);
            }
        });
    };

    const onSubmit = (id, fileName, fileContainer) => {
        const {uploader} = uploaderData;
        return true;
    };

    let uploader = {};
    if (props.type !== undefined && props.subSystem !== undefined && props.entity !== undefined && props.fileKindFolder !== undefined) {

        let validationObject = {};
        if (props.validationExtensionList !== undefined) {
            validationObject["allowedExtensions"] = props.validationExtensionList;
        }
        if (props.validationSizeLimit !== undefined) {
            validationObject["sizeLimit"] = props.validationSizeLimit;
        }
        if (props.type === typeEnum.SINGLE) {
            validationObject["itemLimit"] = 1;
        } else {
            if (props.validationItemLimit !== undefined) {
                validationObject["itemLimit"] = props.validationItemLimit;
            }
        }

        // validationObject["acceptFiles"]="image/png, image/jpeg";
        let fileNameList = [];
        uploader = new FineUploaderTraditional({
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
                    method: "POST",
                    enabled: true,
                    endpoint: "/fso/deleteUploadedFile"
                },
                request: {
                    customHeaders: {
                        "Authorization": "Basic 999jaGFkbWluOkNieWxjZTY3"
                    },
                    endpoint: "http://localhost:8082/fso/upload/" + props.subSystem + "/" + props.entity + "/fine"
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
                    onSubmitted: function (id, fileName) {
                        fileNameList.push(fileName);
                    },
                    onValidate: function (fileData) {
                        return fileNameList.indexOf(fileData.name) < 0;
                    },
                    onError: function (id, name, errorReason, xhr) {
                        // UtilModal.open("خطا", errorReason, "", true, "");
                    },
                    onSubmit: onSubmit,
                    onStatusChange: onStatusChange,
                }
            }
        });
        // uploader.setCustomHeaders({ Authorization: "Basic 999jaGFkbWluOkNieWxjZTY3" })
    }


    let initialState = {
        "error": null,
        "dataIsLoaded": true,
        "customClass": props.customClass,
        "customStyle": props.customStyle,
        "type": props.type,
        "subSystem": props.subSystem,
        "entity": props.entity,
        "fileKindFolder": props.fileKindFolder,
        "validationExtensionList": props.validationExtensionList,
        "validationSizeLimit": props.validationSizeLimit,
        "validationItemLimit": props.validationItemLimit,
        "id": props.id,
        "name": props.name,
        "title": props.title,
        "label": props.label,
        "uploader": uploader,
        "objectSelectedList": [],
        "submitFileDataObject": {},
    };

    //تعریف متغیر state آپلودر
    const [uploaderData, setUploaderData] = useState(initialState);


    const fileInputChildren = <span>انتخاب فایل...</span>;
    const dropZoneContent = <span>میتوانید فایل مورد نظر خود را در اینجا drag نمایید</span>;
    const dropZoneStyle = {border: "1px dotted", height: "200px", width: "100%"};


    console.log("uploaderData.type",uploaderData)

    switch (uploaderData.type) {
        case typeEnum.SINGLE:
            return (<Gallery fileInput-children={fileInputChildren} dropzone-content={dropZoneContent}
                             dropzone-style={dropZoneStyle}
                             uploader={uploaderData.uploader}/>);
        case typeEnum.MULTI:
            return (<Gallery fileInput-children={fileInputChildren} dropzone-content={dropZoneContent}
                             dropzone-style={dropZoneStyle}
                             uploader={uploaderData.uploader}/>);
        default:
            return (<span>Invalid type : {uploaderData.type}</span>);
    }

}


export {Uploader, typeEnum, subSystemEnum, entityEnum, fileKindFolderEnum}

