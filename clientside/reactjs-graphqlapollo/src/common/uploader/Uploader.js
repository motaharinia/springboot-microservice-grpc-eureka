import React, {useState, useEffect} from "react";
import FineUploaderTraditional from "fine-uploader-wrappers";
import Gallery from "react-fine-uploader";
import "react-fine-uploader/gallery/gallery.css";


import Button from "@material-ui/core/Button";
import Modal from "@material-ui/core/Modal";
import CloseIcon from "@material-ui/icons/Close";


import {changeActionEnum, typeEnum, statusEnum, entityEnum, fileKindFolderEnum} from './UploaderData'
import {useStyles} from "../Styles";


function Uploader(props) {
    //تعریف متغیر استایل
    const classes = useStyles();

    //وضعیتها
    //"SUBMIT": وقتی که یک فایل برای اضافه شدن به آپلودر انتخاب میشود
    //"SUBMITTED": وقتی که یک فایل به آپلودر اضافه شود
    //"SUBMIT_DELETE": وقتی که یک فایل برای حذف از آپلودر انتخاب شود
    //"RESUME": وقتی که ادامه آپلود یک فایل در آپلودر انجام شود
    //"MANUAL_RETRY": وقتی که ادامه آپلود یک فایل در آپلودر به صورت دستی توسط کاربر انجام شود
    //"AUTO_RETRY": وقتی که ادامه آپلود یک فایل در آپلودر به صورت اتوماتیک انجام شود
    //"CANCEL": وقتی که یک فایل در آپلودر کنسل شود
    //"COMPLETE": وقتی که یکی از آپلود فایلها کامل گردد
    const onStatusChange = (id, oldStatus, newStatus, openModal) => {
        const {uploader} = fileData;
        uploader.methods.getUploads().map(function (obj, index) {
            let fileObject = JSON.parse(JSON.stringify(obj));
            if (fileObject.id === id) {
                fileObject["fileData"] = uploader.methods.getFile(id);
            }
            if (fileObject.status !== statusEnum.DELETED) {
                fileData["objectSelectedList"].push(fileObject)
            }
        });
        if(openModal !== undefined && openModal !== null){
            fileData["openModal"] = openModal;
        }
        fileData["objectList"] = oldStatus;
        setFileData({
            ...fileData
        });
    };

    const onSubmit = (id, fileName, fileContainer) => {
        setUploader(fileData);
        return true;
    };


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

    //تعریف متغیر state آپلودر
    const [uploader, setUploader] = useState(linitialStateUploader);

    let initialStateFileData = {
        "openModal": false,
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
        "objectList":[],
        "objectSelectedList": [],
        "submitFileDataObject": {}
    };
    //تعریف متغیر state آپلودر
    const [fileData, setFileData] = useState(initialStateFileData);

    useEffect(() => {
        props.onChange(fileData);
    }, [fileData]);


    const onOpenModal = () => {
        let newList = [];
        if (props.objectList !== undefined) {
            props.objectList.map((i) => {
                if (i.statusEnum !== "DELETED") {
                    newList.push(i);
                }
            });
        }
        if (props.validationItemLimit > newList.length) {
            fileData["openModal"] = true;
            setFileData({
                    ...fileData
                });
        } else {
            alert("خطا", " شما فقط " + props.validationItemLimit + " فایل می توانید آپلود نمائید ")
        }
    };


    const onAdd = () => {
        if (fileData.objectSelectedList !== undefined && Object.keys(fileData.objectSelectedList).length !== 0) {
            let objectList = fileData.objectList;
            let changeObjectList = [];
            fileData.objectSelectedList.forEach(function (object, index) {
                if (object.status == statusEnum.UPLOAD_SUCCESSFUL) {
                    let fileViewModel = {
                        extension: object.originalName.split(".")[1],
                        fullName: object.originalName,
                        hashedPath: "",
                        lastModifiedDate: {year: "", month: "", day: ""},
                        mimetype: "",
                        name: object.originalName.split(".")[0],
                        size: object.size,
                        key: object.uuid,
                        statusEnum: statusEnum.ADDED
                    };
                    changeObjectList.push(fileViewModel);
                }
            });
            onStatusChange(fileData, changeObjectList, changeActionEnum.ADD, false);
        }
    };


    const fileInputChildren = <span>انتخاب فایل...</span>;
    const dropZoneContent = <span>میتوانید فایل مورد نظر خود را در اینجا drag نمایید</span>;
    const dropZoneStyle = {border: "1px dotted", height: "200px", width: "100%"};

    const viewUploader = () => {
        switch (fileData.type) {
            case typeEnum.SINGLE:
                return (<Gallery fileInput-children={fileInputChildren} dropzone-content={dropZoneContent}
                                 dropzone-style={dropZoneStyle}
                                 uploader={fileData.uploader}/>);
            case typeEnum.MULTI:
                return (<Gallery fileInput-children={fileInputChildren} dropzone-content={dropZoneContent}
                                 dropzone-style={dropZoneStyle}
                                 uploader={fileData.uploader}/>);
            default:
                return (<span>Invalid type : {fileData.type}</span>);
        }
    }

    const handleClose = () => {
        fileData["openModal"] = false;
        setFileData(
            ...fileData
        )
    };

    let body = (
        <div className={classes.modalBodyUploader}>
            <CloseIcon onClick={handleClose} className={classes.closeButtonError}/>
            <h3 id="simple-modal-title">{"آپلودر"}</h3>
            {viewUploader()}
            <Button onClick={onAdd} type="submit" variant="contained" color="primary">
                {"تایید"}
            </Button>
        </div>
    );


    if (fileData.openModal) {

        return (
            <div>
                <Modal
                    className={classes.modal}
                    open={fileData.openModal}
                    onClose={handleClose}
                    aria-labelledby="simple-modal-title"
                    aria-describedby="simple-modal-description"
                >
                    {body}
                </Modal>
            </div>
        );

    } else {
        return (
            <React.Fragment>
                <Button onClick={onOpenModal} type="submit" variant="outlined">
                    {"بارگذاری فایل"}
                </Button>
            </React.Fragment>
        )

    }

}


export {Uploader}

