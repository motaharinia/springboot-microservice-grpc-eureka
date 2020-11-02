import React, {useEffect, useState} from "react";

import Downloader from "js-file-downloader";

import Grid from "@material-ui/core/Grid";
import Fab from "@material-ui/core/Fab";
import CloudDownload from '@material-ui/icons/CloudDownload';
import Visibility from '@material-ui/icons/Visibility';
import Delete from '@material-ui/icons/Delete';

import {statusEnum} from './UploaderData'
import {useStyles} from "../Styles";

const margin1px = {
    margin: "1px",
};


function UploaderFileView(props) {

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


    const onChange = e => {
        let objectList = fileData.objectList;
        let index = e.currentTarget.getAttribute("index");
        if (objectList[index] !== undefined) {
            objectList[index]["statusEnum"] = statusEnum.DELETED;
            fileData["objectList"] = objectList;
            fileData["objectListIndex"] = index;

            setFileData({
                ...fileData
            });
        }
    };

    useEffect(() => {
        if (fileData.objectListIndex !== "") {
            props.onChange(fileData, fileData.objectList[fileData.objectListIndex]);
        }
    }, [fileData]);

    const onClick=(e)=> {
        const { objectList } = fileData;
        // var index = e.currentTarget.getAttribute("index");
        // var fileViewModel = objectList[index];
        // if (fileViewModel !== undefined) {
        //     if (this.props.onClick) {
        //         this.props.onClick(fileViewModel);
        //     }
        // }
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
        customClass,
        objectList,
        urlBase,
        thumbnailWidth,
        thumbnailHeight,
        hasDownload,
        hasView,
        hasDelete,
    } = fileData;

    let className = "form-control";
    if (customClass !== undefined) {
        className += " " + customClass;
    }

    // const widthMy = {
    // 	width: thumbnailWidth - 14
    // };

    var fileViewHtmlList = [];
    fileViewHtmlList = objectList.map(function (fileViewModel, index) {
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
                title += "\n آخرین تغییر:" + " تازه بارگذاری شده است";
            }
            if (hasDelete) {
                hasAction = true;
                htmlDelete =
                    <div index={index} onClick={onChange} color="primary" aria-label="مشاهده">
                        <Delete/>
                    </div>
            }


            if (isImage) {
                if (hasAction) {
                    return (<React.Fragment key={Math.random()}>
                        <Grid item xs={3} className={classes.boxImgFileView}>
                            <div className="divParentImg">
                                <Grid container spacing={1} style={margin1px}>
                                    <img title={title} index={index} onClick={onClick} src={url}
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
                                <img title={title} index={index} onClick={onClick} src={url}
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
    UploaderFileView
};
