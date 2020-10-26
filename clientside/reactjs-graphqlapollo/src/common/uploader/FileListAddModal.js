import React, {useState} from "react";

import CloseIcon from "@material-ui/icons/Close";
import Modal from "@material-ui/core/Modal";
import Button from "@material-ui/core/Button";

import {useStyles} from "../Styles";
import {Uploader} from "./Uploader"


export default function FileListAddModal(props) {

    //تعریف متغیر استایل
    const classes = useStyles();

    let initialState = {
        "objectSelectedList": [],
        "confirmClass": "",
    };

    //تعریف متغیر state آپلودر
    const [fileListAddData, setFileListAddData] = useState(initialState);


    const uploaderStatusChange = (state, id, status) => {
        let confirmClass = "";
        if (status === Uploader.statusEnum.UPLOADING) {
            confirmClass = " disabled";
        }
        setFileListAddData({"objectSelectedList": fileListAddData.objectSelectedList, "confirmClass": confirmClass},
            function () {
                if (props.uploaderStatusChange !== undefined) {
                    props.uploaderStatusChange(state, id, status);
                }

            });
    };


    const onAdd = () => {
        console.log("objectSelectedList", fileListAddData.objectSelectedList)
        if (fileListAddData.objectSelectedList !== undefined && Object.keys(fileListAddData.objectSelectedList).length  !== 0 ) {
            if (props.onAdd !== undefined) {
                props.onAdd(fileListAddData.objectSelectedList);
                setOpen(false);
            }
        }
    };


    //تعریف متغیر state نمایش دادن یا ندادن پاپ آپ
    const [open, setOpen] = React.useState(true);

    const handleClose = () => {
        setOpen(false);
    };

    let body = (
        <div className={classes.modalBodyUploader}>
            <CloseIcon onClick={handleClose} className={classes.closeButtonError}/>
            <h3 id="simple-modal-title">{"آپلودر"}</h3>
            <Uploader
                type={props.fileListData.type}
                subSystem={props.fileListData.subSystem}
                entity={props.fileListData.entity}
                fileKindFolder={props.fileListData.fileKindFolder}
                validationExtensionList={props.fileListData.validationExtensionList}
                validationSizeLimit={props.fileListData.validationSizeLimit}
                validationItemLimit={props.fileListData.validationItemLimit}
                onChange={uploaderStatusChange}/>

            <Button onClick={onAdd} type="submit" variant="contained" color="primary">
                {"تایید"}
            </Button>
        </div>
    );


    if (props.openModal === true) {

        return (
            <div>
                <Modal
                    className={classes.modal}
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="simple-modal-title"
                    aria-describedby="simple-modal-description"
                >
                    {body}
                </Modal>
            </div>
        );

    } else {
        return (<div> </div>)
    }

}



