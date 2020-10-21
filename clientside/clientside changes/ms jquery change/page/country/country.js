var invalidComboObject = {"options": [["false", funjs_translateKey("acl.active")], ["true", funjs_translateKey("acl.deActive")]]};
var hideComboObject = {"options": [["false", funjs_translateKey("acl.hide")], ["true", funjs_translateKey("acl.unHide")]]};
app.controller('countryController', ['$scope', '$rootScope', '$compile', '$route', '$routeParams', function ($scope, $rootScope, $compile, $route, $routeParams)
    {

        $rootScope.currentTitle = funjs_translateKey("acl.countryMaster");
        $rootScope.breadCrumblevel1 = funjs_translateKey("adminPanel.dashboard");
        $rootScope.breadCrumblevel2 = funjs_translateKey("acl.countryMaster");
        $("#breadCrumblevel2").show();
        $("#breadCrumblevel3").hide();
        $("#breadCrumblevel1 a").attr("href", "#/dashboard");
        $("#breadCrumblevel2 a").attr("href", "#/country/master");
        if (typeof $routeParams.Id != 'undefined') {
            $scope.ids = $routeParams.Id.split(",");
        }
        $scope.masterFunction = function () {


            currentRoute = funjs_commonGetCurrentRoute();

            $scope.rowIdToDelete = -1;
            $scope.pageGrids = {
                "grid1": {
                    "title": funjs_translateKey("country.country.gridTitleCountry"),
                    "gridId": "grid1",
                    "parentGridId": "",
                    "rows": 10,
                    "dataURL": varjs.page.url.contextPath + "/country/submaster/listGrid",
                    "smartRendering": true,
                    "scrollrows": true,
                    "multiSort": true,
                    "multiSelect": true,
                    "showOnInitialization": true,
                    "colNames": funjs_translateArrayKey(["country.country[code]", "country.country[name]", "country.country[preCode]", "country.country[invalid]", "country.country[hidden]"]),
                    "colModel":
                            [
                                {"name": "0", "index": "0", "align": "center", "width": 50, "sortType": "integer", "formatter": "", "hidden": false, search: true, "hiddenlg": false, "stype": "text", "searchoptions": {"stype": "integer", "sopt": ["eq", "ne", "lt", "le", "gt", "ge"], "value": ""}},
                                {"name": "1", "index": "1", "align": "center", "width": 200, "sortType": "string", "formatter": "", "hidden": false, search: true, "hiddenlg": false, "stype": "text", "searchoptions": {"stype": "text", "sopt": ["cn", "eq", "ne", "bw", "bn", "ew", "en", "nc"], "value": ""}},
                                {"name": "2", "index": "2", "align": "center", "width": 120, "sortType": "string", "formatter": funjs_jqgridFormaterDirectionLtr, search: true, "hidden": false, "hiddenlg": false, "stype": "text", "searchoptions": {"stype": "text", "sopt": ["cn", "eq", "ne", "lt", "le", "gt", "ge"], "value": ""}},
                                {"name": "3", "index": "3", "align": "center", "width": 170, "sortType": "string", "formatter": funjs_jqgridFormaterInvalidFormatter, search: true, "hidden": false, "hiddenlg": false, "stype": "select", "searchoptions": {"stype": "select", "sopt": ["eq", "ne"], "value": funjs_comboToCSV(invalidSearchComboObject.options)}},
                                {"name": "4", "index": "4", "align": "center", "width": 190, "sortType": "string", "formatter": funjs_jqgridFormaterHiddenFormatter, search: true, "hidden": false, "hiddenlg": false, "stype": "select", "searchoptions": {"stype": "select", "sopt": ["eq", "ne"], "value": funjs_comboToCSV(hideSearchComboObject.options)}}
                            ]
                    , "navBtns": funjs_gridGetNavBtnList(currentRoute, "grid1")
                    , "parametersMode": "",
                    "parameters": [],
                    "childParamatersMode": "country",
                    "scrollPosition": 0,
                    "selectedRowIndex": null,
                    "selectedRowId": null,
                    "btns_noId": ["country_grid1_create"],
                    "btns_parent": [],
                    "btns_parent_Id": [],
                    "btns_custome": [],
                    "onselectFunction": null,
                    "onselectCheckBoxFunction": null
                },
                "grid2": {
                    "title": funjs_translateKey("region.region.gridTitleRegion"),
                    "gridId": "grid2",
                    "parentGridId": "grid1",
                    "rows": 10,
                    "dataURL": varjs.page.url.contextPath + "/region/submaster/listGrid",
                    "smartRendering": true,
                    "scrollrows": true,
                    "multiSort": true,
                    "multiSelect": true,
                    "showOnInitialization": true,
                    "colNames": funjs_translateArrayKey(["", "region.region[name]", "country.country[invalid]", "country.country[hidden]"]),
                    "colModel":
                            [
                                {"name": "0", "index": "0", "align": "center", "width": 5, "sortType": "integer", "formatter": "", "hidden": true, search: false, "hiddenlg": true, "stype": "text", "searchoptions": {"stype": "integer", "sopt": ["eq", "ne", "lt", "le", "gt", "ge", "bw", "bn", "in", "ni", "ew", "en", "cn", "nc"], "value": ""}},
                                {"name": "1", "index": "1", "align": "center", "width": 100, "sortType": "string", "formatter": "", "hidden": false, search: true, "hiddenlg": false, "stype": "text", "searchoptions": {"stype": "text", "sopt": ["cn", "eq", "ne", "bw", "bn", "ew", "en", "nc"], "value": ""}},
                                {"name": "2", "index": "2", "align": "center", "width": 170, "sortType": "string", "formatter": funjs_jqgridFormaterInvalidFormatter, search: true, "hidden": false, "hiddenlg": false, "stype": "select", "searchoptions": {"stype": "select", "sopt": ["eq", "ne"], "value": funjs_comboToCSV(invalidSearchComboObject.options)}},
                                {"name": "3", "index": "3", "align": "center", "width": 190, "sortType": "string", "formatter": funjs_jqgridFormaterHiddenFormatter, search: true, "hidden": false, "hiddenlg": false, "stype": "select", "searchoptions": {"stype": "select", "sopt": ["eq", "ne"], "value": funjs_comboToCSV(hideSearchComboObject.options)}}
                            ]
                    , "navBtns": funjs_gridGetNavBtnList(currentRoute, "grid2")
                    , "parametersMode": "country",
                    "parameters": [],
                    "childParamatersMode": "region",
                    "scrollPosition": 0,
                    "selectedRowIndex": null,
                    "selectedRowId": null,
                    "btns_noId": [],
                    "btns_parent": ["region_grid2_create"],
                    "btns_parent_Id": [],
                    "btns_custome": [],
                    "onselectFunction": null,
                    "onselectCheckBoxFunction": null
                },
                "grid3": {
                    "title": funjs_translateKey("state.state.gridTitleState"),
                    "gridId": "grid3",
                    "parentGridId": "grid1",
                    "currentParent": "grid1",
                    "rows": 10,
                    "dataURL": varjs.page.url.contextPath + "/state/submaster/listGrid",
                    "smartRendering": true,
                    "scrollrows": true,
                    "multiSort": true,
                    "multiSelect": true,
                    "showOnInitialization": true,
                    "colNames": funjs_translateArrayKey(["state.state[code]", "state.state[name]", "state.state[preCode]", "state.state[invalid]", "state.state[hidden]"]),
                    "colModel":
                            [
                                {"name": "0", "index": "0", "align": "center", "width": 50, "sortType": "integer", "formatter": "", "hidden": false, search: true, "hiddenlg": false, "stype": "text", "searchoptions": {"stype": "integer", "sopt": ["eq", "cn", "ne", "lt", "le", "gt", "ge"], "value": ""}},
                                {"name": "1", "index": "1", "align": "center", "width": 100, "sortType": "string", "formatter": "", "hidden": false, search: true, "hiddenlg": false, "stype": "text", "searchoptions": {"stype": "text", "sopt": ["cn", "eq", "ne", "bw", "bn", "ew", "en", "nc"], "value": ""}},
                                {"name": "2", "index": "2", "align": "center", "width": 120, "sortType": "string", "formatter": "", "hidden": false, search: true, "hiddenlg": false, "stype": "text", "searchoptions": {"stype": "text", "sopt": ["cn", "eq", "ne", "lt", "le", "gt", "ge"], "value": ""}},
                                {"name": "3", "index": "3", "align": "center", "width": 170, "sortType": "string", "formatter": funjs_jqgridFormaterInvalidFormatter, "hidden": false, search: true, "hiddenlg": false, "stype": "select", "searchoptions": {"stype": "select", "sopt": ["eq", "ne"], "value": funjs_comboToCSV(invalidSearchComboObject.options)}},
                                {"name": "4", "index": "4", "align": "center", "width": 190, "sortType": "string", "formatter": funjs_jqgridFormaterHiddenFormatter, "hidden": false, search: true, "hiddenlg": false, "stype": "select", "searchoptions": {"stype": "select", "sopt": ["eq", "ne"], "value": funjs_comboToCSV(hideSearchComboObject.options)}}

                            ]
                    , "navBtns": funjs_gridGetNavBtnList(currentRoute, "grid3")
                    , "parametersMode": "",
                    "parameters": [],
                    "scrollPosition": 0,
                    "selectedRowIndex": null,
                    "selectedRowId": null,
                    "btns_noId": [],
                    "btns_parent_Id": [],
                    "btns_parent": ["state_grid3_create"],
                    "btns_custome": [],
                    "onselectFunction": null,
                    "onselectCheckBoxFunction": null
                },
                "grid4": {
                    "title": funjs_translateKey("eparchy.eparchy.gridTitleEparchy"),
                    "gridId": "grid4",
                    "parentGridId": "grid3",
                    "rows": 10,
                    "dataURL": varjs.page.url.contextPath + "/eparchy/submaster/listGrid",
                    "smartRendering": true,
                    "scrollrows": true,
                    "multiSort": true,
                    "multiSelect": true,
                    "showOnInitialization": true,
                    "colNames": funjs_translateArrayKey(["eparchy.eparchy[code]", "eparchy.eparchy[name]", "eparchy.eparchy[invalid]", "eparchy.eparchy[hidden]"]),
                    "colModel":
                            [
                                {"name": "0", "index": "0", "align": "center", "width": 50, "sortType": "string", "formatter": "", "hidden": false, search: true, "hiddenlg": false, "stype": "text", "searchoptions": {"stype": "integer", "sopt": ["eq", "cn", "ne", "lt", "le", "gt", "ge"], "value": ""}},
                                {"name": "1", "index": "1", "align": "center", "width": 120, "sortType": "string", "formatter": "", "hidden": false, search: true, "hiddenlg": false, "stype": "text", "searchoptions": {"stype": "text", "sopt": ["cn", "eq", "ne", "bw", "bn", "ew", "en", "nc"], "value": ""}},
                                {"name": "2", "index": "2", "align": "center", "width": 170, "sortType": "string", "formatter": funjs_jqgridFormaterInvalidFormatter, "hidden": false, search: true, "hiddenlg": false, "stype": "select", "searchoptions": {"stype": "select", "sopt": ["eq", "ne"], "value": funjs_comboToCSV(invalidSearchComboObject.options)}},
                                {"name": "3", "index": "3", "align": "center", "width": 190, "sortType": "string", "formatter": funjs_jqgridFormaterHiddenFormatter, "hidden": false, search: true, "hiddenlg": false, "stype": "select", "searchoptions": {"stype": "select", "sopt": ["eq", "ne"], "value": funjs_comboToCSV(hideSearchComboObject.options)}}
                            ]
                    , "navBtns": funjs_gridGetNavBtnList(currentRoute, "grid4")
                    , "parametersMode": "state",
                    "parameters": [],
                    "scrollPosition": 0,
                    "childParamatersMode": "eparchy",
                    "selectedRowIndex": null,
                    "selectedRowId": null,
                    "btns_noId": [],
                    "btns_parent_Id": [],
                    "btns_parent": ["eparchy_grid4_create"],
                    "btns_custome": [],
                    "onselectFunction": null,
                    "onselectCheckBoxFunction": null
                },
                "grid5": {
                    "title": funjs_translateKey("city.city.gridTitleCity"),
                    "gridId": "grid5",
                    "parentGridId": "grid4,grid2",
                    "rows": 10,
                    "dataURL": varjs.page.url.contextPath + "/city/submaster/listGrid",
                    "smartRendering": true,
                    "scrollrows": true,
                    "multiSort": true,
                    "multiSelect": true,
                    "showOnInitialization": true,
                    "colNames": funjs_translateArrayKey(["city.city[code]", "city.city[name]", "city.city[invalid]", "city.city[hidden]"]),
                    "colModel":
                            [
                                {"name": "0", "index": "0", "align": "center", "width": 50, "sortType": "string", "formatter": "", "hidden": false, search: true, "hiddenlg": false, "stype": "text", "searchoptions": {"stype": "integer", "sopt": ["eq", "cn", "ne", "lt", "le", "gt", "ge"], "value": ""}},
                                {"name": "1", "index": "1", "align": "center", "width": 100, "sortType": "string", "formatter": "", "hidden": false, search: true, "hiddenlg": false, "stype": "text", "searchoptions": {"stype": "text", "sopt": ["cn", "eq", "ne", "bw", "bn", "ew", "en", "cn", "nc"], "value": ""}},
                                {"name": "2", "index": "2", "align": "center", "width": 170, "sortType": "string", "formatter": funjs_jqgridFormaterInvalidFormatter, "hidden": false, search: true, "hiddenlg": false, "stype": "select", "searchoptions": {"stype": "select", "sopt": ["eq", "ne"], "value": funjs_comboToCSV(invalidSearchComboObject.options)}},
                                {"name": "3", "index": "3", "align": "center", "width": 190, "sortType": "string", "formatter": funjs_jqgridFormaterHiddenFormatter, "hidden": false, search: true, "hiddenlg": false, "stype": "select", "searchoptions": {"stype": "select", "sopt": ["eq", "ne"], "value": funjs_comboToCSV(hideSearchComboObject.options)}}
                            ]
                    , "navBtns": funjs_gridGetNavBtnList(currentRoute, "grid5")
                    , "parametersMode": "eparchy",
                    "parameters": [],
                    "scrollPosition": 0,
                    "selectedRowIndex": null,
                    "selectedRowId": null,
                    "btns_noId": [],
                    "btns_parent_Id": [],
                    "btns_parent": ["city_grid5_create"],
                    "btns_custome": [],
                    "onselectFunction": null,
                    "onselectCheckBoxFunction": null
                },
                "grid7": {
                    "title": funjs_translateKey("district.district.gridTitleDistrict"),
                    "gridId": "grid7",
                    "parentGridId": "grid5",
                    "rows": 10,
                    "dataURL": varjs.page.url.contextPath + "/district/submaster/listGrid",
                    "smartRendering": true,
                    "scrollrows": true,
                    "multiSort": true,
                    "multiSelect": true,
                    "showOnInitialization": true,
                    "colNames": funjs_translateArrayKey(["district.district[code]", "district.district[name]", "district.district[invalid]", "district.district[hidden]"]),
                    "colModel":
                            [
                                {"name": "0", "index": "0", "align": "center", "width": 50, "sortType": "string", "formatter": "", "hidden": false, search: true, "hiddenlg": false, "stype": "text", "searchoptions": {"stype": "integer", "sopt": ["eq", "cn", "ne", "lt", "le", "gt", "ge"], "value": ""}},
                                {"name": "1", "index": "1", "align": "center", "width": 100, "sortType": "string", "formatter": "", "hidden": false, search: true, "hiddenlg": false, "stype": "text", "searchoptions": {"stype": "text", "sopt": ["cn", "eq", "ne", "bw", "bn", "ew", "en", "cn", "nc"], "value": ""}},
                                {"name": "2", "index": "2", "align": "center", "width": 170, "sortType": "string", "formatter": funjs_jqgridFormaterInvalidFormatter, "hidden": false, search: true, "hiddenlg": false, "stype": "select", "searchoptions": {"stype": "select", "sopt": ["eq", "ne"], "value": funjs_comboToCSV(invalidSearchComboObject.options)}},
                                {"name": "3", "index": "3", "align": "center", "width": 180, "sortType": "string", "formatter": funjs_jqgridFormaterHiddenFormatter, "hidden": false, search: true, "hiddenlg": false, "stype": "select", "searchoptions": {"stype": "select", "sopt": ["eq", "ne"], "value": funjs_comboToCSV(hideSearchComboObject.options)}}
                            ]
                    , "navBtns": funjs_gridGetNavBtnList( currentRoute, "grid7")
                    , "parametersMode": "city",
                    "parameters": [],
                    "scrollPosition": 0,
                    "selectedRowIndex": null,
                    "selectedRowId": null,
                    "btns_noId": [],
                    "btns_parent_Id": [],
                    "btns_parent": ["district_grid7_create"],
                    "btns_custome": [],
                    "onselectFunction": null,
                    "onselectCheckBoxFunction": null
                },
//                "grid6": {
//                    "title": funjs_translateKey("area.area.gridTitleArea"),
//                    "gridId": "grid6",
//                    "parentGridId": "grid5",
//                    "rows": 10,
//                    "dataURL": varjs.page.url.contextPath + "/area/submaster/listGrid",
//                    "smartRendering": true,
//                    "scrollrows": true,
//                    "multiSort": true,
//                    "multiSelect": true,
//                    "showOnInitialization": true,
//                    "colNames": funjs_translateArrayKey(["area.area[code]", "area.area[name]", "area.area[invalid]", "area.area[hidden]"]),
//                    "colModel":
//                            [
//                                {"name": "0", "index": "0", "align": "center", "width": 50, "sortType": "string", "formatter": "", "hidden": false, search: true, "hiddenlg": false, "stype": "text", "searchoptions": {"stype": "integer", "sopt": ["eq", "cn", "ne", "lt", "le", "gt", "ge"], "value": ""}},
//                                {"name": "1", "index": "1", "align": "center", "width": 100, "sortType": "string", "formatter": "", "hidden": false, search: true, "hiddenlg": false, "stype": "text", "searchoptions": {"stype": "text", "sopt": ["cn", "eq", "ne", "bw", "bn", "ew", "en", "cn", "nc"], "value": ""}},
//                                {"name": "2", "index": "2", "align": "center", "width": 170, "sortType": "string", "formatter": funjs_jqgridFormaterInvalidFormatter, "hidden": false, search: true, "hiddenlg": false, "stype": "select", "searchoptions": {"stype": "select", "sopt": ["eq", "ne"], "value": funjs_comboToCSV(invalidSearchComboObject.options)}},
//                                {"name": "3", "index": "3", "align": "center", "width": 180, "sortType": "string", "formatter": funjs_jqgridFormaterHiddenFormatter, "hidden": false, search: true, "hiddenlg": false, "stype": "select", "searchoptions": {"stype": "select", "sopt": ["eq", "ne"], "value": funjs_comboToCSV(hideSearchComboObject.options)}}
//                            ]
//                    , "navBtns": funjs_gridGetNavBtnList( currentRoute, "grid6")
//                    , "parametersMode": "area",
//                    "parameters": [],
//                    "scrollPosition": 0,
//                    "selectedRowIndex": null,
//                    "selectedRowId": null,
//                    "btns_noId": [],
//                    "btns_parent_Id": [],
//                    "btns_parent": ["area_grid6_create"],
//                    "btns_custome": [],
//                    "onselectFunction": null,
//                    "onselectCheckBoxFunction": null
//                }
            };
            funjs_gridInit($scope.pageGrids, "grid1,grid2,grid3,grid4,grid5,grid7");
            funjs_gridLoadData($scope.pageGrids, "grid1");
        };
        $scope.goMaster = function () {
            $('#maxBoxHolder').html("");
            $('#boxHolder').html("");
            $("#maxBoxHolder").removeClass("maximized");
            funjs_commonGoToUrl(varjs.page.url.landingPage + '/country/master');
            return false;
        };
        goMasterFunction = $scope.goMaster;
        $scope.countryCreateFunction = function () {

            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.country.create");
            $("#breadCrumblevel3").show();
            var formId = "form-country-create";
            funjs_formPrepare(formId);
            $('#form-country-create').ictValidator(window.ictValidatorOptions)
                    .submit(function () {
                        console.log(enumjs)
                        console.log(microservice)
                        var formObj = funjs_formGetObject($(this).attr("id"));
                        funjs_formPreview(function () {
                            funjs_formDisableBtnConfirm();
                            var ret = funjs_commonSendAjaxRequest("post", "json", "/country/create", formObj, true);

                           console.log(microservice.urlEnum)
                            // var ret = funjs_commonMsRequest(microservice.urlEnum.MSGEO.COUNTRY.CREATE,  formObj, true);


                            if (ret.status == undefined) {
                                $('#alert-modal').modal('hide');
                                $scope.goMaster();
                                funjs_popopShowNotificationFloatBox(funjs_translateKey("country.message"), funjs_translateKey("country.messages.countryRegistered"), "success");

                            } else {
                                funjs_formEnableBtnConfirm();
                            }
                            return false;
                        }, []);
                    });
        };
        $scope.countryUpdateFunction = function () {

            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.country.update");
            $("#breadCrumblevel3").show();

            var formId = "form-country-update";
            funjs_formPrepare(formId);
            funjs_formLoadData(formId, {"idList": [$scope.ids[0]], "param": ""});
            $('#form-country-update').ictValidator(window.ictValidatorOptions)
                    .submit(function () {
                        var formObj = funjs_formGetObject($(this).attr("id"));
                        funjs_formPreview(function () {
                            funjs_formDisableBtnConfirm();
                            var ret = funjs_commonSendAjaxRequest("post", "json", "/country/update", formObj, true);
                            if (ret.status == undefined) {
                                $('#alert-modal').modal('hide');
                                $scope.goMaster();
                                funjs_popopShowNotificationFloatBox(funjs_translateKey("country.message"), funjs_translateKey("country.messages.countryUpdate"), "success");

                            } else {
                                funjs_formEnableBtnConfirm();
                            }
                            return false;
                        }, []);
                    });
        };
        $scope.countryViewFunction = function () {

            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.country.view");
            $("#breadCrumblevel3").show();
            var formId = "form-country-view";
            funjs_formPrepare(formId);
            var data = funjs_formLoadData(formId, {"idList": [$scope.ids[0]], "param": ""});
            $("#invalidType").html(funjs_jqgridFormaterInvalidFormatter(data.invalid));
            $("#hideType").html(funjs_jqgridFormaterHiddenFormatter(data.hidden));
        };
        $scope.regionCreateFunction = function () {


            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.region.create");
            $("#breadCrumblevel3").show();
            var id = $routeParams.Id.split(",")[0];
            $("#countryId").val(id);
            var formId = "form-region-create";
            funjs_treeInit("city-tree", "/city/submaster/tree/" + id, ["html_data", "ui", "cookies", "types", 'checkbox'], "", {three_state: true, whole_node: false, tie_selection: false});
            var jsonData = funjs_commonSendAjaxRequest("post", "json", "/country/submaster/find", {"idList": [id], "param": "pm1"}, false);
            $("#countryName").html(jsonData[0].name);
            funjs_formPrepare(formId);
            $('#form-region-create').ictValidator(window.ictValidatorOptions)
                    .submit(function () {
                        var formObj = funjs_formGetObject($(this).attr("id"));
                        var selectList = [];
                        var cityList = [];
                        selectList = funjs_treeGetChecked("city-tree");
                        for (var i = 0; i < selectList.length; i++) {
                            if (selectList[i].charAt(0) == "c") {
                                cityList.push(parseInt(selectList[i].replace('ci_', '')));
                            }
                        }

                        if (cityList == null || cityList.length == 0) {

                            funjs_popopShowNotificationFloatBox(funjs_translateKey("country.message.error"), funjs_translateKey("country.messages.stateListCanNotEmpty"), "error");
                        } else {
                            formObj["cityList"] = cityList;
                            formObj.country_id = parseInt(id);
                            funjs_formPreview(function () {
                                funjs_formDisableBtnConfirm();
                                var ret = funjs_commonSendAjaxRequest("post", "json", "/region/create", formObj, true);
                                if (ret.status == undefined) {
                                    $('#alert-modal').modal('hide');
                                    $scope.goMaster();
                                    funjs_popopShowNotificationFloatBox(funjs_translateKey("country.message"), funjs_translateKey("country.messages.regionRegistered"), "success");

                                } else {
                                    funjs_formEnableBtnConfirm();
                                }

                                return false;
                            }, []);
                        }
                    });
        };
        $scope.regionUpdateFunction = function () {
            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.region.update");
            $("#breadCrumblevel3").show();
            var id = $routeParams.Id.split(",")[0];
            var formId = "form-region-update";
            funjs_formPrepare(formId);
            var dataCity = [];
            var data = funjs_formLoadData(formId, {"idList": [$scope.ids[0]], "param": ""});
            funjs_treeInit("city-tree", "/city/submaster/tree/" + data.country_id, ["html_data", "ui", "cookies", "types", 'checkbox'], "", {three_state: true, whole_node: false, tie_selection: false});
            for (var i = 0; i < data.cityList.length; i++) {

                dataCity.push("ci_" + data.cityList[i]);
            }
            $("#city-tree").bind("ready.jstree", function (event, data) {
                funjs_treeSetChecked("city-tree", dataCity);
                funjs_treeCloseNodesUnChecked("city-tree", dataCity, data);
            });
            $("#countryName").html(data.country_name);
            $('#form-region-update').ictValidator(window.ictValidatorOptions)
                    .submit(function () {
                        var formObj = funjs_formGetObject($(this).attr("id"));
                        var selectList = [];
                        var cityList = [];
                        selectList = funjs_treeGetChecked("city-tree");
                        for (var i = 0; i < selectList.length; i++) {
                            if (selectList[i].charAt(0) == "c") {
                                cityList.push(parseInt(selectList[i].replace('ci_', '')));
                            }
                        }
                        if (cityList == null || cityList.length == 0) {

                            funjs_popopShowNotificationFloatBox(funjs_translateKey("country.message.error"), funjs_translateKey("country.messages.stateListCanNotEmpty"), "error");
                        } else {

                            formObj["cityList"] = cityList;
                            formObj.country_id = parseInt(data.country_id);
                            formObj.id = parseInt(id);
                            funjs_formPreview(function () {
                                funjs_formDisableBtnConfirm();
                                var ret = funjs_commonSendAjaxRequest("post", "json", "/region/update", formObj, true);
                                if (ret.status == undefined) {
                                    $('#alert-modal').modal('hide');
                                    $scope.goMaster();
                                    funjs_popopShowNotificationFloatBox(funjs_translateKey("country.message"), funjs_translateKey("country.messages.regionUpdate"), "success");
                                } else {
                                    funjs_formEnableBtnConfirm();
                                }

                                return false;
                            }, []);
                        }
                    });
        };
        $scope.regionViewFunction = function () {

            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.region.view");
            $("#breadCrumblevel3").show();
            var formId = "form-region-view";
            funjs_formPrepare(formId);
            var dataCity = [];
            var data = funjs_formLoadData(formId, {"idList": [$scope.ids[0]], "param": ""});
            funjs_treeInit("city-tree", "/city/submaster/tree/" + data.country_id, ["html_data", "ui", "cookies", "types", 'checkbox'], "", {three_state: true, whole_node: false, tie_selection: false});
            for (var i = 0; i < data.cityList.length; i++) {
                dataCity.push("ci_" + data.cityList[i]);
            }
            $("#city-tree").bind("ready.jstree", function (event, data) {
                funjs_treeSetChecked("city-tree", dataCity);
                funjs_treeHiddenNodesUnChecked("city-tree");
            });
            $("#city-tree_topBtns").hide();
            $("#form-tree").append("<div class='blackHover'></div>");
            $("#invalidType").html(funjs_jqgridFormaterInvalidFormatter(data.invalid));
            $("#hideType").html(funjs_jqgridFormaterHiddenFormatter(data.hidden));
        };
        $scope.stateCreateFunction = function () {

            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.state.create");
            $("#breadCrumblevel3").show();
            var id = $routeParams.Id.split(",")[0];
            $("#countryId").val(id);
            var formId = "form-state-create";
            var jsonData = funjs_commonSendAjaxRequest("post", "json", "/country/submaster/find", {"idList": [id], "param": "pm1"}, false);
            $("#countryName").html(jsonData[0].name);
            funjs_formPrepare(formId);
            $('#form-state-create').ictValidator(window.ictValidatorOptions)
                    .submit(function () {
                        var formObj = funjs_formGetObject($(this).attr("id"));
                        formObj.country_id = parseInt(id);
                        funjs_formPreview(function () {
                            funjs_formDisableBtnConfirm();
                            var ret = funjs_commonSendAjaxRequest("post", "json", "/state/create", formObj, true);
                            if (ret.status == undefined) {
                                $('#alert-modal').modal('hide');
                                $scope.goMaster();
                                funjs_popopShowNotificationFloatBox(funjs_translateKey("country.message"), funjs_translateKey("country.messages.stateRegistered"), "success");
                            } else {
                                funjs_formEnableBtnConfirm();
                            }

                            return false;
                        }, []);
                    });
        };
        $scope.stateUpdateFunction = function () {
            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.state.update");
            $("#breadCrumblevel3").show();
            var id = $routeParams.Id.split(",")[0];
            var formId = "form-state-update";
            funjs_formPrepare(formId);
            var data = funjs_formLoadData(formId, {"idList": [$scope.ids[0]], "param": ""});
            var jsonData = funjs_commonSendAjaxRequest("post", "json", "/country/submaster/find", {"idList": [data.country_id], "param": "pm1"}, false);
            $("#countryName").html(jsonData[0].name);
            $('#form-state-update').ictValidator(window.ictValidatorOptions)
                    .submit(function () {
                        var formObj = funjs_formGetObject($(this).attr("id"));
                        formObj.country_id = parseInt(id);
                        funjs_formPreview(function () {
                            funjs_formDisableBtnConfirm();
                            var ret = funjs_commonSendAjaxRequest("post", "json", "/state/update", formObj, true);
                            if (ret.status == undefined) {
                                $('#alert-modal').modal('hide');
                                $scope.goMaster();
                                funjs_popopShowNotificationFloatBox(funjs_translateKey("country.message"), funjs_translateKey("country.messages.stateUpdate"), "success");
                            } else {
                                funjs_formEnableBtnConfirm();
                            }

                            return false;
                        }, []);
                    });
        };
        $scope.stateViewFunction = function () {


            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.state.view");
            $("#breadCrumblevel3").show();
            var formId = "form-state-view";
            funjs_formPrepare(formId);
            var data = funjs_formLoadData(formId, {"idList": [$scope.ids[0]], "param": ""});
            $("#invalidType").html(funjs_jqgridFormaterInvalidFormatter(data.invalid));
            $("#hideType").html(funjs_jqgridFormaterHiddenFormatter(data.hidden));
        };
        $scope.eparchyCreateFunction = function () {
            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.eparchy.create");
            $("#breadCrumblevel3").show();
            var id = $routeParams.Id.split(",")[0];
            $("#stateId").val(id);
            var formId = "form-eparchy-create";
            var jsonData = funjs_commonSendAjaxRequest("post", "json", "/state/submaster/find", {"idList": [id], "param": "pm1"}, false);
            $("#stateName").html(jsonData[0].name);
            funjs_formPrepare(formId);
            $('#form-eparchy-create').ictValidator(window.ictValidatorOptions)
                    .submit(function () {
                        var formObj = funjs_formGetObject($(this).attr("id"));
                        funjs_formPreview(function () {
                            funjs_formDisableBtnConfirm();
                            var ret = funjs_commonSendAjaxRequest("post", "json", "/eparchy/create", formObj, true);
                            if (ret.status == undefined) {
                                $('#alert-modal').modal('hide');
                                $scope.goMaster();
                                funjs_popopShowNotificationFloatBox(funjs_translateKey("country.message"), funjs_translateKey("country.messages.eparchyRegistered"), "success");
                            } else {
                                funjs_formEnableBtnConfirm();
                            }

                            return false;
                        }, []);
                    });
        };
        $scope.eparchyUpdateFunction = function () {
            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.eparchy.update");
            $("#breadCrumblevel3").show();
            var id = $routeParams.Id.split(",")[0];
            var formId = "form-eparchy-update";
            funjs_formPrepare(formId);
            var data = funjs_formLoadData(formId, {"idList": [id], "param": ""});
            var jsonData = funjs_commonSendAjaxRequest("post", "json", "/state/submaster/find", {"idList": [data.state_id], "param": "pm1"}, false);
            $("#stateName").html(jsonData[0].name);
            $('#form-eparchy-update').ictValidator(window.ictValidatorOptions)
                    .submit(function () {
                        var formObj = funjs_formGetObject($(this).attr("id"));
                        funjs_formPreview(function () {
                            funjs_formDisableBtnConfirm();
                            var ret = funjs_commonSendAjaxRequest("post", "json", "/eparchy/update", formObj, true);
                            if (ret.status == undefined) {
                                $('#alert-modal').modal('hide');
                                $scope.goMaster();
                                funjs_popopShowNotificationFloatBox(funjs_translateKey("country.message"), funjs_translateKey("country.messages.eparchyUpdate"), "success");
                            } else {
                                funjs_formEnableBtnConfirm();
                            }

                            return false;
                        }, []);
                    });
        };
        $scope.eparchyViewFunction = function () {

            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.eparchy.view");
            $("#breadCrumblevel3").show();
            var formId = "form-eparchy-view";
            funjs_formPrepare(formId);
            var data = funjs_formLoadData(formId, {"idList": [$scope.ids[0]], "param": ""});
            $("#invalidType").html(funjs_jqgridFormaterInvalidFormatter(data.invalid));
            $("#hideType").html(funjs_jqgridFormaterHiddenFormatter(data.hidden));
        };
        $scope.cityCreateFunction = function () {
            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.city.create");
            $("#breadCrumblevel3").show();
            var id = $routeParams.Id.split(",")[0];
            $("#eparchyId").val(id);
            var formId = "form-city-create";
            var jsonData = funjs_commonSendAjaxRequest("post", "json", "/eparchy/submaster/find", {"idList": [id], "param": "pm1"}, false);
            $("#eparchyName").html(jsonData[0].name);
            funjs_formPrepare(formId);
            $('#form-city-create').ictValidator(window.ictValidatorOptions)
                    .submit(function () {
                        var formObj = funjs_formGetObject($(this).attr("id"));
                        funjs_formPreview(function () {
                            funjs_formDisableBtnConfirm();
                            var ret = funjs_commonSendAjaxRequest("post", "json", "/city/create", formObj, true);
                            if (ret.status == undefined) {
                                $('#alert-modal').modal('hide');
                                $scope.goMaster();
                                funjs_popopShowNotificationFloatBox(funjs_translateKey("country.message"), funjs_translateKey("country.messages.cityRegistered"), "success");
                            } else {
                                funjs_formEnableBtnConfirm();
                            }

                            return false;
                        }, []);
                    });
        };
        $scope.cityUpdateFunction = function () {
            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.city.update");
            $("#breadCrumblevel3").show();
            var id = $routeParams.Id.split(",")[0];
            var formId = "form-city-update";
            funjs_formPrepare(formId);
            var data = funjs_formLoadData(formId, {"idList": [id], "param": ""});
            var jsonData = funjs_commonSendAjaxRequest("post", "json", "/eparchy/submaster/find", {"idList": [data.eparchy_id], "param": "pm1"}, false);
            $("#eparchyName").html(jsonData[0].name);
            $('#form-city-update').ictValidator(window.ictValidatorOptions)
                    .submit(function () {
                        var formObj = funjs_formGetObject($(this).attr("id"));
                        funjs_formPreview(function () {
                            funjs_formDisableBtnConfirm();
                            var ret = funjs_commonSendAjaxRequest("post", "json", "/city/update", formObj, true);
                            if (ret.status == undefined) {
                                $('#alert-modal').modal('hide');
                                $scope.goMaster();
                                funjs_popopShowNotificationFloatBox(funjs_translateKey("country.message"), funjs_translateKey("country.messages.cityUpdate"), "success");
                            } else {
                                funjs_formEnableBtnConfirm();
                            }
                            return false;
                        }, []);
                    });
        };
        $scope.cityViewFunction = function () {

            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.city.view");
            $("#breadCrumblevel3").show();
            var formId = "form-city-view";
            funjs_formPrepare(formId);
            var data = funjs_formLoadData(formId, {"idList": [$scope.ids[0]], "param": ""});
            $("#invalidType").html(funjs_jqgridFormaterInvalidFormatter(data.invalid));
            $("#hideType").html(funjs_jqgridFormaterHiddenFormatter(data.hidden));
        };
        $scope.districtCreateFunction = function() {
            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.district.create");
            $("#breadCrumblevel3").show();
            var id = $routeParams.Id.split(",")[0];
            $("#cityId").val(id);
            var formId = "form-district-create";
            var jsonData = funjs_commonSendAjaxRequest("post", "json", "/city/submaster/find", {"idList": [id], "param": "pm1"}, false);
            $("#cityName").html(jsonData[0].name);
            funjs_formPrepare(formId);
            $('#form-district-create').ictValidator(window.ictValidatorOptions)
                    .submit(function() {
                        var formObj = funjs_formGetObject($(this).attr("id"));
                        var ret = funjs_commonSendAjaxRequest("post", "json", "/district/create", formObj, true);
                        if (ret.status == undefined) {
                            $scope.goMaster();
                        }

                        return false;
                    });
        };
        $scope.districtUpdateFunction = function() {
            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.district.update");
            $("#breadCrumblevel3").show();
            var id = $routeParams.Id.split(",")[0];
            var formId = "form-district-update";
            funjs_formPrepare(formId);
            var data = funjs_formLoadData(formId, {"idList": [id], "param": ""});
            var jsonData = funjs_commonSendAjaxRequest("post", "json", "/city/submaster/find", {"idList": [data.city_id], "param": "pm1"}, false);
            $("#cityName").html(jsonData[0].name);
            $('#form-district-update').ictValidator(window.ictValidatorOptions)
                    .submit(function() {
                        var formObj = funjs_formGetObject($(this).attr("id"));
                        var ret = funjs_commonSendAjaxRequest("post", "json", "/district/update", formObj, true);
                        if (ret.status == undefined) {
                            $scope.goMaster();
                        }
                        return false;
                    });
        };
        $scope.districtViewFunction = function() {

            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.district.view");
            $("#breadCrumblevel3").show();
            var formId = "form-district-view";
            funjs_formPrepare(formId);
            var data = funjs_formLoadData(formId, {"idList": [$scope.ids[0]], "param": ""});
            $("#invalidType").html(funjs_jqgridFormaterInvalidFormatter(data.invalid));
            $("#hideType").html(funjs_jqgridFormaterHiddenFormatter(data.hidden));
        };
//        $scope.areaCreateFunction = function() {
//            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.area.create");
//            $("#breadCrumblevel3").show();
//            var id = $routeParams.Id.split(",")[0];
//            $("#cityId").val(id);
//            var formId = "form-area-create";
//            var jsonData = funjs_commonSendAjaxRequest("post", "json", "/city/submaster/find", {"idList": [id], "param": "pm1"}, false);
//            $("#cityName").html(jsonData[0].name);
//            funjs_formPrepare(formId);
//            $('#form-area-create').ictValidator(window.ictValidatorOptions)
//                    .submit(function() {
//                        var formObj = funjs_formGetObject($(this).attr("id"));
//                        var ret = funjs_commonSendAjaxRequest("post", "json", "/area/create", formObj, true);
//                        if (ret.status == undefined) {
//                            $scope.goMaster();
//                        }
//
//                        return false;
//                    });
//        };
//        $scope.areaUpdateFunction = function() {
//            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.area.update");
//            $("#breadCrumblevel3").show();
//            var id = $routeParams.Id.split(",")[0];
//            var formId = "form-area-update";
//            funjs_formPrepare(formId);
//            var data = funjs_formLoadData(formId, {"idList": [id], "param": ""});
//            var jsonData = funjs_commonSendAjaxRequest("post", "json", "/city/submaster/find", {"idList": [data.city_id], "param": "pm1"}, false);
//            $("#cityName").html(jsonData[0].name);
//            $('#form-area-update').ictValidator(window.ictValidatorOptions)
//                    .submit(function() {
//                        var formObj = funjs_formGetObject($(this).attr("id"));
//                        var ret = funjs_commonSendAjaxRequest("post", "json", "/area/update", formObj, true);
//                        if (ret.status == undefined) {
//                            $scope.goMaster();
//                        }
//
//                        return false;
//                    });
//        };
//        $scope.areaViewFunction = function() {
//
//            $rootScope.breadCrumblevel3 = funjs_translateKey("acl.area.view");
//            $("#breadCrumblevel3").show();
//            var formId = "form-area-view";
//            funjs_formPrepare(formId);
//            var data = funjs_formLoadData(formId, {"idList": [$scope.ids[0]], "param": ""});
//            $("#invalidType").html(funjs_jqgridFormaterInvalidFormatter(data.invalid));
//            $("#hideType").html(funjs_jqgridFormaterHiddenFormatter(data.hidden));
//        };



        $scope.deleteFunction = function () {

            var url = window.location.href;
            $scope.selectedIds = $scope.ids;
            $scope.entity = url.split("#/")[1].split("/delete")[0];
            $("#breadCrumblevel3").show();
            var formId = "form-delete";
            funjs_formPrepare(formId);
            var result = funjs_commonSendAjaxRequest("post", "json", "/" + $scope.entity + "/submaster/find", {"idList": $scope.ids, "param": "pm1"}, true);
            $rootScope.breadCrumblevel3 = funjs_translateKey("acl." + $scope.entity + ".delete");
            funjs_tableShowErrorWithoutResult("list", $scope.entity, result, true, false);
            $scope.removeFromList = function (id) {
                $scope.selectedIds = funjs_commonDropValueFromArray($scope.selectedIds, id + "");
                $("#rowId-" + id).remove();
            };
            $scope.functionremovselectlist = function () {
                var tid;
                $('.listCheckbox:checked').each(function () {
                    tid = $(this).parent().parent().attr("id").split("-")[1];
                    $scope.removeFromList(tid);
                });
            };
            $scope.deleteConfirm = function () {

                var actionResult = funjs_commonSendAjaxRequest("post", "json", "/" + $scope.entity + "/delete", {"idList": $scope.selectedIds}, true, true);
//                console.log(actionResult);
                if (actionResult.status == "200") {
                    funjs_tableShowErrorResult("list", []);
                } else {
                    funjs_tableShowErrorResult("list", actionResult.data.customExceptionDetailModelList);
                }


                $("#btnConfirm").hide();
                $("#sureConfirm").hide();
                $("#btnCancel").text(funjs_translateKey("general.form.btnReturn"));
            };
        };
        $scope.invalidFunction = function () {
            var url = window.location.href;
            $scope.selectedIds = $scope.ids;
            $scope.entity = url.split("#/")[1].split("/invalid")[0];
            $("#breadCrumblevel3").show();
            var formId = "form-invalid";
            funjs_formPrepare(formId);
            var result = funjs_commonSendAjaxRequest("post", "json", "/" + $scope.entity + "/submaster/find", {"idList": $scope.ids, "param": "pm1"}, true);
            $rootScope.breadCrumblevel3 = funjs_translateKey("acl." + $scope.entity + ".invalid");
            funjs_tableShowErrorWithoutResult("list", $scope.entity, result, true, false);
            $scope.removeFromList = function (id) {
                $scope.selectedIds = funjs_commonDropValueFromArray($scope.selectedIds, id + "");
                $("#rowId-" + id).remove();
            };
            $scope.functionremovselectlist = function () {
                var tid;
                $('.listCheckbox:checked').each(function () {
                    tid = $(this).parent().parent().attr("id").split("-")[1];
                    $scope.removeFromList(tid);
                });
            };
            $('#form-invalid').ictValidator(window.ictValidatorOptions)
                    .submit(function () {
                        var actionResult = funjs_commonSendAjaxRequest("post", "json", "/" + $scope.entity + "/invalid", {"idList": $scope.ids, "invalid": $("#invalidCombo").val()}, true, true);
                        if (actionResult.status == "200") {
                            funjs_tableShowErrorResult("list", []);
                        } else {
                            funjs_tableShowErrorResult("list", actionResult.data.customExceptionDetailModelList);
                        }
                        $("#btnConfirm").hide();
                        $("#sureConfirm").hide();
                        $("#btnCancel").text(funjs_translateKey("general.form.btnReturn"));
                    });
        };
        $scope.hideFunction = function () {
            var url = window.location.href;
            $scope.selectedIds = $scope.ids;
            $scope.entity = url.split("#/")[1].split("/hide")[0];
            $("#breadCrumblevel3").show();
            var formId = "form-hide";
            funjs_formPrepare(formId);
            var result = funjs_commonSendAjaxRequest("post", "json", "/" + $scope.entity + "/submaster/find", {"idList": $scope.ids, "param": "pm1"}, true);
            $rootScope.breadCrumblevel3 = funjs_translateKey("acl." + $scope.entity + ".hide");
            funjs_tableShowErrorWithoutResult("list", $scope.entity, result, true, false);
            $scope.removeFromList = function (id) {
                $scope.selectedIds = funjs_commonDropValueFromArray($scope.selectedIds, id + "");
                $("#rowId-" + id).remove();
            };
            $scope.functionremovselectlist = function () {
                var tid;
                $('.listCheckbox:checked').each(function () {
                    tid = $(this).parent().parent().attr("id").split("-")[1];
                    $scope.removeFromList(tid);
                });
            };
            $('#form-hide').ictValidator(window.ictValidatorOptions)
                    .submit(function () {
                        var actionResult = funjs_commonSendAjaxRequest("post", "json", "/" + $scope.entity + "/hide", {"idList": $scope.ids, "hide": $("#hideCombo").val()}, true, true);
                        if (actionResult.status == "200") {
                            funjs_tableShowErrorResult("list", []);
                        } else
                        {
                            funjs_tableShowErrorResult("list", actionResult.data.customExceptionDetailModelList);
                        }
                        $("#btnConfirm").hide();
                        $("#sureConfirm").hide();
                        $("#btnCancel").text(funjs_translateKey("general.form.btnReturn"));
                    });
        }
        ;
    }]);
