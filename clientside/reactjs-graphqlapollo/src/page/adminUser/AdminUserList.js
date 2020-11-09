import React, {useEffect, useState} from 'react';
import {useQuery} from '@apollo/react-hooks';

import Grid from '@material-ui/core/Grid';
import {DataGrid} from '@material-ui/data-grid';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import EditIcon from '@material-ui/icons/Edit';
import Visibility from '@material-ui/icons/Visibility';
import DeleteIcon from '@material-ui/icons/Delete'
import CircularProgress from "@material-ui/core/CircularProgress";

import {ADMIN_USER_READ_GRID} from './AdminUserQueries.js'
import {useStyles} from './AdminUserStyles'
import Header from "../../common/header/Header";
import ResultHandling from "../../common/ResultHandling";


const columns = [
    {field: 'id', hide: true},
    {field: 'firstName', headerName: 'نام', width: 200},
    {field: 'lastName', headerName: 'نام خانوادگی', width: 250},
    {field: 'date', headerName: 'تاریخ', width: 150},
    {field: 'address', headerName: 'نشانی', width: 250},
];


function rowsGrid(data) {
    if (data !== undefined) {
        let rowsGrid = [];
        let searchDataRowModelList = data.searchDataRowModelList;
        // eslint-disable-next-line array-callback-return
        searchDataRowModelList.map(rowItem => {
            rowsGrid.push({
                id: rowItem.id,
                firstName: rowItem.rowCellArray[1],
                lastName: rowItem.rowCellArray[2],
                date: rowItem.rowCellArray[3],
                address: rowItem.rowCellArray[4]
            });
        });
        return rowsGrid;
    } else {
        return [];
    }
}

let rowId = "";

function onSelectRowGrid(dataRow) {
    rowId = dataRow.data.id;
}

function onClickButtonUpdate() {
    if (rowId !== "") {
        window.location.href = "/adminUserUpdate/" + rowId;
    } else {
        alert(" سطری انتخاب نشده است")
    }
}

function onClickButtonView() {
    if (rowId !== "") {
        window.location.href = "/adminUserView/" + rowId;
    } else {
        alert(" سطری انتخاب نشده است")
    }
}


function onClickButtonDelete() {
    if (rowId !== "") {
        window.location.href = "/adminUserDelete/" + rowId;
    } else {
        alert(" سطری انتخاب نشده است")
    }
}

function AdminUserList() {
    const classes = useStyles();

    //تعریف متغیر state فرم
    const [gridData, setGridData] = useState([]);
    let searchFilterModel = {
        page: 0,
        rows: 100
    };
    //تعریف کوئری خوانش با شناسه و قراردادن مقدار آن در متغیر داده فرم
    const {loading, error, data} = useQuery(ADMIN_USER_READ_GRID, {
        variables: {searchFilterModel:searchFilterModel}
    });

    //فراخوانی داده از سرور فقط برای یک بار و جلوگیری از رفرش تو در توی صفحه و ذخیره داده سرور در متغیر  state
    useEffect(() => {
        if (!loading && !error) {
            let readGridByModel = rowsGrid(data.readGridByModel);
            setGridData(readGridByModel);
        }
    }, [loading, error, data]);

    //در صورت عدم لود داده لودینگ نمایش داده شود
    if (loading === undefined || loading) {
        return (<div><CircularProgress/></div>)
    }
    //در صورت بروز خطا ، پیام آن نمایش داده شود
    if (error) {
        error["crudType"] = "m";
        return (<div><ResultHandling result={error} open={true} key={Math.random()}/></div>)
    }


    return (
        <div>
            <Header viewCloseButton={false} pageTitle="لیست ادمین ها"/>
            <Grid>
                <Grid item xs={12} className={classes.gridHeight}>
                    <DataGrid onRowSelected={onSelectRowGrid} rows={gridData} columns={columns}
                              loading={gridData.length === 0}/>
                </Grid>
            </Grid>
            <Grid>
                <Grid item xs={12}>
                    <div className={classes.root}>
                        <Fab onClick={() => {
                            window.location.href = "/adminUserCreate"
                        }} color="primary" aria-label="ثبت ادمین" className={classes.marginButton}>
                            <AddIcon/>
                        </Fab>
                        <Fab onClick={onClickButtonUpdate} color="primary" aria-label="ویرایش اطلاعات ادمین">
                            <EditIcon/>
                        </Fab>
                        <Fab onClick={onClickButtonView} color="primary" aria-label="مشاهده اطلاعات ادمین">
                            <Visibility/>
                        </Fab>
                        <Fab onClick={onClickButtonDelete} color="secondary" aria-label="حذف ادمین">
                            <DeleteIcon/>
                        </Fab>
                    </div>
                </Grid>
            </Grid>
        </div>
    );
}

export default AdminUserList;