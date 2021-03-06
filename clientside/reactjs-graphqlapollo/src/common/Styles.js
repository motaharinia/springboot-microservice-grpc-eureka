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
    divParentImg: {
        height: "116px"
    },
    boxImgFileView: {
        marginTop: "2%"
    },
    imgFileView: {
        cursor: "pointer",
        display: "block",
        marginLeft: "auto",
        marginRight: "auto",
        width: "50%"
    },
    boxTitleFileview: {
        backgroundColor: "#3f51b5",
        color: "white",
        overflow: "hidden",
        height: "41px",
        width: "100%",
        textOverflow: "clip",
        padding: "2%",
        textAlign: "center",
        borderRadius: "5px 5px 0 0"
    },

    boxButtonFileview: {
        backgroundColor: "#3f51b5",
        color: "white",
        margin: "0",
        borderRadius: "0 0 5px 5px",
        paddingRight: "5%",
        width: "100%"
    },
    deleteIcon: {
        fontSize: "30px",
        cursor: "pointer",
        '&:hover': {
            color: "#ff3b3b",
            transition: ".5s ease"
        }
    },
    downloadIcon: {
        fontSize: "30px",
        cursor: "pointer",
        '&:hover': {
            color: "#17bb77",
            transition: ".5s ease"
        }
    },
    VisibilityIcon: {
        fontSize: "30px",
        cursor: "pointer",
        '&:hover': {
            color: "#17bb77",
            transition: ".5s ease"
        }
    },

    devParentIconContentSize: {
        width: "79%",
        height: "94px",
        top: "12px"
    },
    iconContentSize: {
        fontSize: "30px",
        textAlign: "center"
    }

}));


export {useStyles};