import React, {useState, useEffect} from "react";

import Downloader from "js-file-downloader";

import Grid from "@material-ui/core/Grid";
import CloudDownload from '@material-ui/icons/CloudDownload';
import Visibility from '@material-ui/icons/Visibility';
import Delete from '@material-ui/icons/Delete';

import {statusEnum} from './FileInit'
import {useStyles} from "../Styles";


export default function FileView(props) {

    //تعریف متغیر استایل
    const classes = useStyles();

    let initialState = {
        "objectList": props.objectList,
        "urlBase": props.urlBase,
        "hasDownload": props.hasDownload,
        "hasView": props.hasView,
        "hasDelete": props.hasDelete,
        "objectListIndex": ""
    };

    //تعریف متغیر state آپلودر
    const [fileData, setFileData] = useState(initialState);


    const onDelete = e => {
        let index = e.currentTarget.getAttribute("index");
        let objectList = JSON.parse(JSON.stringify(fileData["objectList"]));
        if (objectList[index] !== undefined) {
            objectList[index]["statusEnum"] = statusEnum.DELETED;
            setFileData({
                ...fileData,
                "objectList": objectList,
                "objectListIndex": index
            });
        }
    };

    useEffect(() => {
        if (fileData.objectListIndex !== "") {
            props.onChange(JSON.parse(JSON.stringify(fileData.objectList)), fileData.objectList[fileData.objectListIndex]);
        }
    }, [fileData.objectList]);

    const onClick = () => {

    };


    const onView = e => {
        const {objectList, urlBase} = fileData;
        let index = e.currentTarget.getAttribute("index");
        let fileViewModel = objectList[index];
        if (fileViewModel !== undefined) {
            window.open(urlBase + fileViewModel.hashedPath + "/");
        }
    };


    const onDownload = e => {
        const {objectList, urlBase} = fileData;
        let index = e.currentTarget.getAttribute("index");
        let fileViewModel = objectList[index];
        if (fileViewModel !== undefined) {
            let url = urlBase + fileViewModel.hashedPath + "/";
            new Downloader({
                url: url,
                filename: fileViewModel.fullName
            }).then(function () {
                // Called when download ended
            }).catch(function (error) {
                // Called when an error occurred
            });
        }
    };

    const getSizeTitle = sizeInByte => {
        let sizeTitle = "";
        if (sizeInByte < 1000000) {
            sizeTitle = parseInt(sizeInByte / 1024) + " کیلوبایت";
        } else {
            sizeTitle = parseInt(sizeInByte / 1024) + " مگابایت";
        }
        return sizeTitle;
    };
    const {
        objectList,
        urlBase,
        hasDownload,
        hasView,
        hasDelete,
    } = fileData;

    var fileViewHtmlList = [];
    if (objectList !== undefined && objectList.length !== 0) {
        fileViewHtmlList = objectList.map((fileViewModel, index) => {
            if (fileViewModel.statusEnum !== statusEnum.DELETED) {
                let isImage = false;
                let hasAction = false;
                let htmlDownload = <div></div>;
                let htmlView = <div></div>;
                let htmlDelete = <div></div>;
                let url = "";
                let title = "نام فایل : " + fileViewModel.fullName + "\n حجم فایل:" + getSizeTitle(fileViewModel.size);
                if (fileViewModel.hashedPath !== undefined && fileViewModel.hashedPath !== null && fileViewModel.hashedPath !== "") {
                    url = urlBase + fileViewModel.hashedPath + "/";
                    // title += "\n آخرین تغییر:" + fileViewModel.lastModifiedDate.year + "/" + fileViewModel.lastModifiedDate.month + "/" + fileViewModel.lastModifiedDate.day;
                    if (hasDownload) {
                        hasAction = true;
                        htmlDownload =
                            <div index={index} onClick={onDownload} color="primary" aria-label="دانلود">
                                <CloudDownload className={classes.downloadIcon}/>
                            </div>
                    }
                    if (hasView) {
                        hasAction = true;
                        htmlView =
                            <div index={index} onClick={onView} color="primary" aria-label="مشاهده">
                                <Visibility className={classes.VisibilityIcon}/>
                            </div>
                    }
                    switch (fileViewModel.extension) {
                        case "png":
                        case "jpg":
                        case "JPG":
                        case "bmp":
                        case "jpeg":
                        case "gif":
                            isImage = true;
                            break;
                        default:
                            isImage = false;
                    }
                } else {
                    title += "آخرین تغییر:  تازه بارگذاری شده است";
                }
                if (hasDelete) {
                    hasAction = true;
                    htmlDelete =
                        <div index={index} onClick={onDelete} color="primary" aria-label="حذف">
                            <Delete className={classes.deleteIcon}/>
                        </div>
                }

                if (isImage) {
                    if (hasAction) {
                        return (<React.Fragment key={Math.random()}>
                            <Grid item xs={12} md={4} className={classes.boxImgFileView}>
                                <Grid item xs={12} className={classes.boxTitleFileview}>
                                    <span>{title}</span>
                                </Grid>
                                <Grid item xs={12} className={classes.divParentImg}>
                                    <img alt="" index={index} onClick={onClick} src={url}
                                         className={classes.imgFileView} title={title}/>
                                </Grid>
                                <Grid container spacing={1} item xs={12} className={classes.boxButtonFileview}>
                                    <Grid item xs={4}>
                                        {htmlDownload}
                                    </Grid>
                                    <Grid item xs={4}>
                                        {htmlView}
                                    </Grid>
                                    <Grid item xs={4}>
                                        {htmlDelete}
                                    </Grid>
                                </Grid>
                            </Grid>
                        </React.Fragment>);
                    } else {
                        return (<React.Fragment key={Math.random()}>
                            <Grid item xs={3} className={classes.boxImgFileView}>
                                <Grid container spacing={1}>
                                    <span className={classes.boxTitleImg}>{title}</span>
                                    <img alt="" index={index} onClick={onClick} src={url}
                                         className={classes.imgFileView}
                                         title={title}/>
                                </Grid>
                            </Grid>
                        </React.Fragment>);
                    }
                } else {
                    if (hasAction) {

                        return (<React.Fragment key={Math.random()}>
                            <Grid item xs={12} md={4} className={classes.boxImgFileView}>
                                <Grid item xs={12} className={classes.boxTitleFileview}>
                                    <span>{title}</span>
                                </Grid>
                                <Grid item xs={12} className={classes.divParentImg}>
                                    <Grid container spacing={1}>
                                        <div title={title} index={index} onClick={onClick}
                                             className={"fi fi-" + fileViewModel.extension + " " + classes.devParentIconContentSize}>

                                            <div
                                                className={"fi-content" + " " + classes.iconContentSize}>{fileViewModel.extension.toUpperCase()}</div>
                                        </div>
                                    </Grid>
                                </Grid>
                                <Grid container spacing={1} item xs={12} className={classes.boxButtonFileview}>
                                    <Grid item xs={4}>
                                        {htmlDownload}
                                    </Grid>
                                    <Grid item xs={4}>
                                        {htmlView}
                                    </Grid>
                                    <Grid item xs={4}>
                                        {htmlDelete}
                                    </Grid>
                                </Grid>
                            </Grid>
                        </React.Fragment>);
                    } else {
                        return (<React.Fragment key={Math.random()}>
                            <Grid item xs={3} className={classes.boxImgFileView}>
                                <Grid container spacing={1}>
                                    <div title={title} index={index} onClick={onClick}
                                         className={"fi fi-" + fileViewModel.extension + " " + classes.devParentIconContentSize}>
                                        <div
                                            className={"fi-content" + " " + classes.iconContentSize}>{fileViewModel.extension.toUpperCase()}</div>
                                    </div>
                                </Grid>
                            </Grid>
                        </React.Fragment>);
                    }
                }
            }
        });

        return (<React.Fragment>
            <Grid container spacing={1}>
                {fileViewHtmlList}
            </Grid>
        </React.Fragment>);

    } else {
        return (
            <React.Fragment>
            <Grid container spacing={1}>
            </Grid>
        </React.Fragment>);
    }

}
