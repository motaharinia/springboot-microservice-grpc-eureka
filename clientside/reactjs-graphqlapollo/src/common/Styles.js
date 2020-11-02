import {makeStyles} from '@material-ui/core/styles';


const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
        fontFamily: "IRANSans !important",
        boxShadow: "0px 0px 3px #aaa",
        padding: "1%",
        '& > *': {
            margin: theme.spacing(1),
        },
    },
    appBar: {
        zIndex: theme.zIndex.drawer + 1,
        position: "relative"
    },
    closeButton: {
        textAlign: 'center',
        lineHeight: '78px',
    },
    divBadge: {
        lineHeight: '77px',
    },
    hideClass: {
        display: "none !important",
    },

    modal: {
        fontFamily: "IRANSans !important",
        display: 'flex',
        padding: theme.spacing(1),
        alignItems: 'center',
        justifyContent: 'center',
        background: "#fff",
    },
    modalBodyDefault: {
        fontFamily: "IRANSans !important",
        width: 500,
        background: "#fff",
        borderRight: "9px solid #ff5919",
        color: "#2c3e50",
        padding: theme.spacing(2, 4, 3),
    },
    modalBodyError: {
        fontFamily: "IRANSans !important",
        width: 500,
        background: "#ff9999",
        borderRight: "9px solid #ff0000",
        color: "#2c3e50",
        padding: theme.spacing(2, 4, 3),
    },
    modalBodySuccess: {
        fontFamily: "IRANSans !important",
        width: 500,
        background: "#fff",
        borderRight: "9px solid green",
        color: "#2c3e50",
        padding: theme.spacing(2, 4, 3),
    },

    modalBodyUploader: {
        fontFamily: "IRANSans !important",
        width: 1000,
        background: "#fff",
        color: "#2c3e50",
        padding: theme.spacing(2, 4, 3),
    },

    closeButtonError: {
        float: "left",
    },
    boxImgFileView: {
        border: "1px solid #c4c4c4",
        marginTop: "2%"
    },

    boxButtonFileview: {
        backgroundColor: "#3f51b5",
        color: "white"
    }

}));


export {useStyles};