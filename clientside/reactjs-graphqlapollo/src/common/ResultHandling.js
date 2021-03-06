import React from 'react';

// material-ui
import Modal from '@material-ui/core/Modal';
import CloseIcon from "@material-ui/icons/Close";

// custom js
import {useStyles} from './Styles'


export default function ResultHandling(props) {
    //تعریف متغیر استایل
    const classes = useStyles();
    //تعریف متغیر state نمایش دادن یا ندادن پاپ آپ
    const [open, setOpen] = React.useState(props.open);
    let flagOpen = false;
    const handleClose = () => {
        if(flagOpen){
            window.location.href = "/adminUserList"
        }else{
            setOpen(flagOpen);
        }

    };
    // نوع عملیات
    let operation = "";
    // eslint-disable-next-line default-case
    switch (props.result.crudType) {
        case "CREATE":
            operation = "ثبت";
            break;
        case "UPDATE":
            operation = "ویرایش";
            break;
        case "DELETE":
            operation = "حذف";
            break;
    }
    // استایل پیام ها براساس نوع آن
    let messageStyle = classes.modalBodyDefault;
    // عنوان پیام
    let titleModal = "پیام";
    // پیام
    let message = "";
    // شرح پیام
    let messageDetail="";
    // بررسی پر بودن خطا ها
    if (props.result.error === "") {
        // بررسی پر بودن داده ها
        if (props.result.data !== "") {
            messageStyle = classes.modalBodySuccess;
            message = `${operation}    با موفقیت انجام شد`;
            flagOpen = true;
        }
    } else {
        messageStyle = classes.modalBodyError;
        titleModal = `${operation}   با خطای زیر مواجه شد`;
        // اطلاعات خطا ها
        let dataError = props.result;
        flagOpen = true;
        if (props.result.error !== undefined) {
            dataError = props.result.error;
            flagOpen = false;
        }
        // بررسی پر بودن networkError
        if (dataError.networkError !== undefined && dataError.networkError !== null) {
            message = `پیام خطا : ${dataError.networkError}`;
            flagOpen = true;
            if(dataError.networkError.result !== undefined && dataError.networkError.result !== null){
                messageDetail=dataError.networkError.result.trace;
            }
        }else{
            // بررسی پر بودن graphQLErrors
            if (dataError.graphQLErrors !== undefined && dataError.graphQLErrors !== null) {
                message = `پیام خطا : ${dataError.graphQLErrors[0].message}`;
            }
        }


    }

    if (message === "") {
        return (<div> </div>)
    }
    let body = (<div className={messageStyle}>
        <CloseIcon onClick={handleClose} className={classes.closeButtonError}/>
        <h3 id="simple-modal-title">{titleModal}</h3>
        <p id="simple-modal-description">
            {message}
            <textarea rows="10" cols="65"  defaultValue={messageDetail}  style={{'direction': 'ltr','pointerEvents': 'none'}}></textarea>

        </p>
    </div>);


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

}
