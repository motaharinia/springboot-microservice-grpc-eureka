import React from 'react';
import {useSubscription} from '@apollo/react-hooks';

// material-ui
import Badge from '@material-ui/core/Badge';
import MailIcon from '@material-ui/icons/Mail';

// custom js
import {useStyles} from '../Styles';
import {BRIEF_MESSEAGES_SUBSCRIPTION} from '../Queries';


export default function BriefMesseages(props) {
    //تعریف متغیر استایل
    const classes = useStyles();
    //تعریف متغیر state فرم
    const [notifyCounter, setNotifyCounter] = React.useState(0);

    //تعریف کوئری ناتیف پیام با شناسه و قراردادن مقدار آن در متغیر داده فرم
    useSubscription(
        BRIEF_MESSEAGES_SUBSCRIPTION,
        {
            variables: {"userId": 2},
            onSubscriptionData: ({subscriptionData: {data}}) => {
                if (data) {
                    setNotifyCounter(data.briefMesseages.notifyCounter);
                }
            }
        }
    );

    return (
        <div className={classes.divBadge}>
            <Badge badgeContent={notifyCounter} color="secondary">
                <MailIcon/>
            </Badge>
        </div>
    );

}
