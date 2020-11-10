import React from 'react';

// material-ui
import AppBar from "@material-ui/core/AppBar";
import Grid from "@material-ui/core/Grid";
import Fab from "@material-ui/core/Fab";
import CloseIcon from "@material-ui/icons/Close";

// custom js
import {useStyles} from '../Styles'
import BriefMesseages from "./BriefMesseages";

export default function Header(props) {
    const classes = useStyles();
    let viewButton = classes.closeButton;
    if (props.viewCloseButton === false) {
        viewButton = classes.hideClass;
    }

    // کلید ضربدر
    const CloseButton = () => {
        window.location.href = "/adminUserList"
    };

    return (
        <div>
            <AppBar position="fixed" className={classes.appBar}>
                <Grid container spacing={1}>
                    <Grid item xs={1}>
                    </Grid>
                    <Grid item xs={2}>
                        <h2>{props.pageTitle}</h2>
                    </Grid>
                    <Grid item xs={7}>
                    </Grid>
                    <Grid item xs={1}>
                        <BriefMesseages/>
                    </Grid>
                    <Grid item xs={1} className={viewButton}>
                        <Fab size="medium" color="secondary" aria-label="بازگشت">
                            <CloseIcon onClick={CloseButton}/>
                        </Fab>
                    </Grid>
                </Grid>
            </AppBar>
        </div>
    );

}
