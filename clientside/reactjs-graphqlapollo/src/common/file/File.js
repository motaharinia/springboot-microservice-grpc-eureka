import React, {useEffect, useRef, useState} from "react";
import Gallery from "react-fine-uploader";
import "react-fine-uploader/gallery/gallery.css";


import Button from "@material-ui/core/Button";
import Modal from "@material-ui/core/Modal";
import CloseIcon from "@material-ui/icons/Close";


import {typeEnum, statusEnum, getNewUploader} from './FileInit'
import {FileView} from './FileView'
import {useStyles} from "../Styles";


export default function File(props) {


    //تعریف متغیر استایل
    const classes = useStyles();


    //مقداردهی اولیه استیت
    let initialStateFileData = {
        "modalIsOpened": false,
        "urlBase": props.urlBase,
        "customClass": props.customClass,
        "customStyle": props.customStyle,
        "type": props.type,
        "subSystem": props.subSystem,
        "entity": props.entity,
        "fileKindFolder": props.fileKindFolder,
        "hasUploader": props.hasUploader,
        "hasDownload": props.hasDownload,
        "hasView": props.hasView,
        "hasDelete": props.hasDelete,
        "validationExtensionList": props.validationExtensionList,
        "validationSizeLimit": props.validationSizeLimit,
        "validationItemLimit": props.validationItemLimit,
        "id": props.id,
        "name": props.name,
        "title": props.title,
        "label": props.label,
        "objectList": props.objectList,
        "uploader": null,
        "objectSelectedList": [],
        "submitFileDataObject": {}
    };

    //event listener callback cannot access the latest state. we use useRef to do it.
    const [fileData, _setFileData] = useState(initialStateFileData);
    const fileDataRef = useRef(fileData);
    const setFileData = (data) => {
        fileDataRef.current = data;
        _setFileData(data);
    };


    // ایجاد انجین آپلودر در اولین باز شدن فرم
    useEffect(() => {
        //انجین آپلودر اولیه
        let uploader = getNewUploader(props.subSystem, props.entity, props.type, props.validationExtensionList, props.validationSizeLimit, props.validationItemLimit, [], onStatusChange)
        //تعریف متغیر state آپلودر
        fileData["uploader"] = uploader;
        setFileData({
            ...fileData
        });
    }, []);


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
            let fileData = fileDataRef.current;
            const {uploader} = fileData;
            let selectedList = JSON.parse(JSON.stringify(fileData.objectSelectedList));
            if (uploader !== null) {
                let uploadComplete = false;
                console.log("uploader.methods.getUploads():", uploader.methods.getUploads())
                uploader.methods.getUploads().map(function (obj, index) {
                        if ((obj.id === id) && (obj.status === statusEnum.UPLOAD_SUCCESSFUL)) {
                            let fileObject = JSON.parse(JSON.stringify(obj));
                            fileObject["fileData"] = uploader.methods.getFile(id);
                            selectedList.push(fileObject);
                            uploadComplete = true;
                        }
                    }
                );
                if (uploadComplete) {
                    setFileData({
                        ...fileData,
                        "objectSelectedList": selectedList,
                        "modalIsOpened": true
                    });
                }
            }
        }
    ;


//متد باز کننده مدال انتخاب فایل جدید
    const onModalOpen = () => {
        //به دست آوردن تعداد فایلهای آپلود شده فعلی
        let uploadedCount = 0;
        if (fileData.objectList !== undefined) {
            fileData.objectList.map((i) => {
                if (i.statusEnum !== "DELETED") {
                    uploadedCount++
                }
            });
        }

        if (props.validationItemLimit > uploadedCount) {
            setFileData({
                ...fileData,
                "objectSelectedList": [],
                "modalIsOpened": true
            });
        } else {
            alert("خطا", " شما فقط " + props.validationItemLimit + " فایل می توانید آپلود نمائید ")
        }
    };

    const onModalClose = () => {
        setFileData({
            ...fileData,
            "modalIsOpened": false
        });
    };

//متد بسته شدن مدال و اضافه کننده فایلهای انتخاب شده به لیست انتخابی
    const onModalAddButton = () => {
        console.log("fileData.objectSelectedList", fileData.objectSelectedList)
        let uploadComplete = false;
        let objectList = JSON.parse(JSON.stringify(fileData["objectList"]));
        if (fileData.objectSelectedList !== undefined && fileData.objectSelectedList.length !== 0) {
            fileData.objectSelectedList.map(object => {
                if (object.status === statusEnum.UPLOAD_SUCCESSFUL) {
                    let fileViewModel = {
                        key: object.uuid,
                        statusEnum: statusEnum.ADDED
                    };
                    objectList.push(fileViewModel);
                    uploadComplete = true;
                }
            });
        }
        if (uploadComplete) {
            setFileData({
                ...fileData,
                "objectList": objectList,
                "modalIsOpened": false
            });
        }

    };

    useEffect(() => {
        if (fileData.objectList !== undefined && fileData.objectList.length !== 0) {
            if (props.onChange !== undefined) {
                props.onChange(fileData.objectList);
            }
        }
    }, [fileData.objectList]);
//******************************


// uploader.setCustomHeaders({ Authorization: "Basic 999jaGFkbWluOkNieWxjZTY3" })


    const onDelete = (objectList, deletedObject) => {
        console.log("onDelete objectList", objectList)
        setFileData({
            ...fileData,
            "objectList": objectList
        });
    };

    const fileInputChildren = <span>انتخاب فایل...</span>;
    const dropZoneContent = <span>میتوانید فایل مورد نظر خود را در اینجا drag نمایید</span>;
    const dropZoneStyle = {border: "1px dotted", height: "200px", width: "100%"};

    const viewUploader = () => {
        if (fileData.uploader !== null) {
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
    };


    let body = (
        <div className={classes.modalBodyUploader}>
            <CloseIcon onClick={onModalClose} className={classes.closeButtonError}/>
            <h3 id="simple-modal-title">{"آپلودر"}</h3>
            {viewUploader()}
            <Button onClick={onModalAddButton} type="submit" variant="contained" color="primary">
                {"تایید"}
            </Button>
        </div>
    );


    if (fileData.modalIsOpened) {
        return (
            <div>
                <Modal
                    className={classes.modal}
                    open={fileData.modalIsOpened}
                    onClose={onModalClose}
                    disableBackdropClick={true}
                    aria-labelledby="simple-modal-title"
                    aria-describedby="simple-modal-description"
                >
                    {body}
                </Modal>
            </div>
        );

    } else {
        if (fileData.hasUploader === true) {
            if (fileData.objectList !== undefined && Object.keys(fileData.objectList).length !== 0) {
                return (
                    <React.Fragment>
                        <Button onClick={onModalOpen} type="submit" variant="outlined">
                            {"بارگذاری فایل"}
                        </Button>
                        <FileView key={Math.random()}
                                  objectList={fileData.objectList} urlBase={fileData.urlBase}
                                  hasDownload={fileData.hasDownload} hasView={fileData.hasView}
                                  hasDelete={fileData.hasDelete}
                                  onChange={onDelete}/>
                    </React.Fragment>
                )
            } else {
                return (
                    <React.Fragment>
                        <Button onClick={onModalOpen} type="submit" variant="outlined">
                            {"بارگذاری فایل"}
                        </Button>
                    </React.Fragment>
                )
            }
        } else {
            return (
                <React.Fragment>
                    <FileView key={Math.random()}
                              objectList={fileData.objectList} urlBase={fileData.urlBase}
                              hasDownload={fileData.hasDownload} hasView={fileData.hasView}
                              hasDelete={fileData.hasDelete}
                              onChange={onDelete}/>
                </React.Fragment>
            )
        }
    }
}




