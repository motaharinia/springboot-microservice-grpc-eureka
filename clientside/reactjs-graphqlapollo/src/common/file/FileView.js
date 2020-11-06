import React, {useState, useEffect} from "react";

import Downloader from "js-file-downloader";

import Grid from "@material-ui/core/Grid";
import CloudDownload from '@material-ui/icons/CloudDownload';
import Visibility from '@material-ui/icons/Visibility';
import Delete from '@material-ui/icons/Delete';

import {statusEnum} from './FileInit'
import {useStyles} from "../Styles";

const margin1px = {
    margin: "1px",
};


function FileView(props) {

    //تعریف متغیر استایل
    const classes = useStyles();

    let initialState = {
        "objectList": props.objectList,
        "urlBase": props.urlBase,
        "thumbnailWidth": "80px",
        "thumbnailHeight": "auto",
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
        thumbnailWidth,
        thumbnailHeight,
        hasDownload,
        hasView,
        hasDelete,
    } = fileData;

    var fileViewHtmlList = [];
    console.log("objectList",objectList)
    fileViewHtmlList = objectList.map((fileViewModel, index) => {
        if (fileViewModel.statusEnum !== statusEnum.DELETED) {
            let isImage = false;
            let hasAction = false;
            let htmlDownload = <div> </div>;
            let htmlView = <div>  </div>;
            let htmlDelete = <div> </div>;
            let url = "";
            let title = "نام فایل : " + fileViewModel.fullName + "\n حجم فایل:" + getSizeTitle(fileViewModel.size);
            if (fileViewModel.hashedPath !== undefined && fileViewModel.hashedPath !== null && fileViewModel.hashedPath !== "") {
                url = urlBase + fileViewModel.hashedPath + "/";
                // title += "\n آخرین تغییر:" + fileViewModel.lastModifiedDate.year + "/" + fileViewModel.lastModifiedDate.month + "/" + fileViewModel.lastModifiedDate.day;
                if (hasDownload) {
                    hasAction = true;
                    htmlDownload =
                        <div index={index} onClick={onDownload} color="primary" aria-label="دانلود">
                            <CloudDownload/>
                        </div>
                }
                if (hasView) {
                    hasAction = true;
                    htmlView =
                        <div index={index} onClick={onView} color="primary" aria-label="مشاهده">
                            <Visibility/>
                        </div>
                }
console.log("fileViewModel",fileViewModel)
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
                        <Delete/>
                    </div>
            };


            if (isImage) {
                if (hasAction) {
                    return (<React.Fragment key={Math.random()}>
                        <Grid item xs={3} className={classes.boxImgFileView}>
                            <div className="divParentImg">
                                <Grid container spacing={1} style={margin1px}>
                                    <img alt="" title={title} index={index} onClick={onClick} src={url}
                                         width={thumbnailWidth} height={thumbnailHeight}
                                         style={{"cursor": "pointer"}}/>
                                </Grid>
                            </div>
                            <Grid container spacing={1} className={classes.boxButtonFileview}>
                                <Grid item xs={3}>
                                    {htmlDownload}
                                </Grid>
                                <Grid item xs={1}>
                                </Grid>
                                <Grid item xs={3}>
                                    {htmlView}
                                </Grid>
                                <Grid item xs={1}>
                                </Grid>
                                <Grid item xs={3}>
                                    {htmlDelete}
                                </Grid>
                            </Grid>
                        </Grid>
                    </React.Fragment>);
                } else {
                    return (<React.Fragment key={Math.random()}>
                        <Grid item xs={3} className={classes.boxImgFileView}>
                            <Grid container spacing={1} style={margin1px}>
                                <img alt="" title={title} index={index} onClick={onClick} src={url}
                                     width={thumbnailWidth} height={thumbnailHeight}
                                     style={{"cursor": "pointer"}}/>
                            </Grid>
                        </Grid>
                    </React.Fragment>);
                }
            } else {
                if (hasAction) {
                    return (<React.Fragment key={Math.random()}>
                        <Grid item xs={3} className={classes.boxImgFileView}>
                            <div className="divParentImg">
                                <Grid container spacing={1} style={margin1px}>
                                    <div title={title} index={index} onClick={onClick}
                                         className={"fi fi-" + fileViewModel.extension} style={{
                                        "width": thumbnailWidth,
                                        "height": "88px",
                                    }}>
                                        <div className="fi-content" style={{
                                            "textAlign": "center",
                                            "fontSize": "25px",
                                        }}>{fileViewModel.extension}</div>
                                    </div>
                                </Grid>
                            </div>
                            <Grid container spacing={1} className={classes.boxButtonFileview}>
                                <Grid item xs={3}>
                                    {htmlDownload}
                                </Grid>
                                <Grid item xs={1}>
                                </Grid>
                                <Grid item xs={3}>
                                    {htmlView}
                                </Grid>
                                <Grid item xs={1}>
                                </Grid>
                                <Grid item xs={3}>
                                    {htmlDelete}
                                </Grid>
                            </Grid>
                        </Grid>
                    </React.Fragment>);
                } else {
                    return (<React.Fragment key={Math.random()}>
                        <Grid item xs={3} className={classes.boxImgFileView}>
                            <Grid container spacing={1} style={margin1px}>
                                <div title={title} index={index} onClick={onClick}
                                     className={"fi fi-" + fileViewModel.extension} style={{
                                    "width": thumbnailWidth,
                                    "height": "88px",
                                }}>
                                    <div className="fi-content" style={{
                                        "textAlign": "center",
                                        "fontSize": "25px",
                                    }}>{fileViewModel.extension.toUpperCase()}</div>
                                </div>
                            </Grid>
                        </Grid>
                    </React.Fragment>);
                }
            }
        }
    }, this);
    return (<React.Fragment>
        <Grid container spacing={1}>
            {fileViewHtmlList}
        </Grid>
    </React.Fragment>);


}

export {
    FileView
};
