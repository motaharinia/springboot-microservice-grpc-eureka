import React, {useEffect, useState} from "react";

import Downloader from "js-file-downloader";

import Grid from "@material-ui/core/Grid";
import CloudDownload from '@material-ui/icons/CloudDownload';
import Eye from '@material-ui/icons/PanoramaFishEye';
import Remove from '@material-ui/icons/Remove';

import {statusEnum} from './UploaderData'
import Fab from "@material-ui/core/Fab";
import EditIcon from "@material-ui/icons/Edit";

const margin1px = {
    margin: "1px",
};


function UploaderFileView(props) {

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
        let objectList = this.state.objectList;
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
            let title = "نام فایل : " + fileViewModel.fullName + "\n حجم فایل:" + this.getSizeTitle(fileViewModel.size);
            if (fileViewModel.hashedPath !== undefined && fileViewModel.hashedPath !== null && fileViewModel.hashedPath !== "") {
                url = urlBase + fileViewModel.hashedPath + "/";
                title += "\n آخرین تغییر:" + fileViewModel.lastModifiedDate.year + "/" + fileViewModel.lastModifiedDate.month + "/" + fileViewModel.lastModifiedDate.day;
                if (hasDownload) {
                    hasAction = true;
                    htmlDownload =
                        <Fab index={index} onClick={onDownload} color="primary" aria-label="دانلود">
                            <CloudDownload/>
                        </Fab>
                }
                if (hasView) {
                    hasAction = true;
                    htmlView =
                        <Fab index={index} onClick={onView} color="primary" aria-label="مشاهده">
                            <Eye/>
                        </Fab>
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
                    <Fab index={index} onClick={onChange} color="primary" aria-label="مشاهده">
                        <Remove/>
                    </Fab>
            }


            if (isImage) {
                if (hasAction) {
                    return (<React.Fragment key={Math.random()}>
                        <div className="boxImgFileView">
                            <div className="divParentImg">
                                <Grid container spacing={1} style={margin1px}>
                                    <img title={title} index={index} onClick={this.onClick} src={url}
                                         width={thumbnailWidth} height={thumbnailHeight}
                                         style={{"cursor": "pointer"}}/>
                                </Grid>
                            </div>
                            <Grid container spacing={1} className="boxButtonFileview">
                                <Grid item xs={2}>
                                    {htmlDownload}
                                </Grid>
                                <Grid item xs={1}>
                                </Grid>
                                <Grid item xs={2}>
                                    {htmlView}
                                </Grid>
                                <Grid item xs={4}>
                                </Grid>
                                <Grid item xs={1}>
                                    {htmlDelete}
                                </Grid>
                            </Grid>
                        </div>
                    </React.Fragment>);
                } else {
                    return (<React.Fragment key={Math.random()}>
                        <div className="boxImgFileView">
                            <Grid container spacing={1} style={margin1px}>
                                <img title={title} index={index} onClick={this.onClick} src={url}
                                     width={thumbnailWidth} height={thumbnailHeight}
                                     style={{"cursor": "pointer"}}/>
                            </Grid>
                        </div>
                    </React.Fragment>);
                }
            } else {
                if (hasAction) {
                    return (<React.Fragment key={Math.random()}>
                        <div className="boxImgFileView">
                            <div className="divParentImg">
                                <Grid container spacing={1} style={margin1px}>
                                    <div title={title} index={index} onClick={this.onClick}
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
                            </div>
                            <Grid container spacing={1} className="boxButtonFileview">
                                <Grid item xs={2}>
                                    {htmlDownload}
                                </Grid>
                                <Grid item xs={1}>
                                </Grid>
                                <Grid item xs={2}>
                                    {htmlView}
                                </Grid>
                                <Grid item xs={4}>
                                </Grid>
                                <Grid item xs={1}>
                                    {htmlDelete}
                                </Grid>
                            </Grid>
                        </div>
                    </React.Fragment>);
                } else {
                    return (<React.Fragment key={Math.random()}>
                        <div className="boxImgFileView">
                            <Grid container spacing={1} style={margin1px}>
                                <div title={title} index={index} onClick={this.onClick}
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
                        </div>
                    </React.Fragment>);
                }
            }
        }
    }, this);
    return (<React.Fragment>
        <Container fluid={false} style={{}}>
            <Grid container spacing={1}>
                {fileViewHtmlList}
            </Grid>
        </Container>
    </React.Fragment>);


}

export {
    UploaderFileView
};
