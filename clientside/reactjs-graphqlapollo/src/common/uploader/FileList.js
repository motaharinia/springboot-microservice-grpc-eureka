import React, {useState} from "react";

import Button from "@material-ui/core/Button";

import {Uploader} from "./Uploader"
import FileListAddModal from "./FileListAddModal";
import CustomFileView from "./FileView"


export default function FileList(props) {

    const changeActionEnum = {
        "ADD": "ADD", //فایل جدید آپلود شده است
        "DELETE": "DELETE",  //فایل حذف شده است
    };

    let initialState = {
        "error": null,
        "dataIsLoaded": true,
        "objectList": props.objectList,
        "urlBase": props.urlBase,
        "hasDownload": props.hasDownload,
        "hasView": props.hasView,
        "hasDelete": props.hasDelete,
        "type": props.type,
        "subSystem": props.subSystem,
        "entity": props.entity,
        "fileKindFolder": props.fileKindFolder,
        "validationExtensionList": props.validationExtensionList,
        "validationSizeLimit": props.validationSizeLimit,
        "validationItemLimit": props.validationItemLimit
    };

    //تعریف متغیر state آپلودر
    const [fileListData, setFileListData] = useState(initialState);

    const onChange = (changeObjectList, changeActionEnum) => {
        if (props.onChange !== undefined) {
            props.onChange(JSON.parse(JSON.stringify(fileListData)), changeObjectList, changeActionEnum);
        }
    };

    //تعریف متغیر state نمایش دادن یا ندادن پاپ آپ
    const [openModal, setOpenModal] = React.useState(false);

    const onAddOpen = () => {
        let newList = [];
        fileListData.objectList.map((i) => {
            if (i.statusEnum !== "DELETED") {
                newList.push(i);
            }
        });

        if (fileListData.validationItemLimit > newList.length) {
            setOpenModal(true);
        } else {
            // UtilModal.open("خطا", " شما فقط " + validationItemLimit + " فایل می توانید آپلود نمائید ", "", true, "");
        }
    };

    const onAdd = (objectSelectedList) => {
        let objectList = fileListData.objectList;
        if (objectSelectedList === undefined || objectSelectedList === null) {
            objectSelectedList = [];
        }
        if (objectList === undefined || objectList === null) {
            objectList = [];
        }
        let changeObjectList = [];
        objectSelectedList.forEach(function (object, index) {
            if (object.status === Uploader.statusEnum.UPLOAD_SUCCESSFUL) {
                let fileViewModel = {
                    extension: object.originalName.split(".")[1],
                    fullName: object.originalName,
                    hashedPath: "",
                    lastModifiedDate: {year: "", month: "", day: ""},
                    mimetype: "",
                    name: object.originalName.split(".")[0],
                    size: object.size,
                    fileKey: object.uuid,
                    statusEnum: CustomFileView.statusEnum.ADDED
                };
                objectList.push(fileViewModel);
                changeObjectList.push(fileViewModel);
            }
        });

        setFileListData({
            "objectList": objectList
        }, function () {
            onChange(changeObjectList, changeActionEnum.ADD);
        });

        setOpenModal(false);
    };

    const onDelete = (state, fileViewModel) => {
        let changeObjectList = [];
        changeObjectList.push(fileViewModel);
        setFileListData({
            "objectList": state.objectList
        }, function () {
            onChange(changeObjectList, changeActionEnum.DELETE);
        });
    };

    if (fileListData.error) {
        return <div>{fileListData.error}</div>
    } else {
        return (<React.Fragment>
            <div className="boxFileView row">
                <Button onClick={onAddOpen} type="submit" variant="outlined">
                    {"بارگذاری فایل"}
                </Button>
                <div className="col-sm-10 col-md-10 boxScrollImgsFileView">
                    <CustomFileView key={Math.random()} objectList={fileListData.objectList}
                                    urlBase={fileListData.urlBase}
                                    hasDownload={fileListData.hasDownload} hasView={fileListData.hasView}
                                    hasDelete={fileListData.hasDelete}
                                    onChange={onDelete}/>
                </div>
            </div>
            <FileListAddModal openModal={openModal} fileListData={fileListData} onAdd={onAdd} key={Math.random()}/>
        </React.Fragment>);
    }
}










