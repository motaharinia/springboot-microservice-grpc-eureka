import React, {Component} from "react";
import {Config, RepoVar, UtilCommon, UtilAjax, UtilCookie, Microservice} from "../ComponentIndex";
import {CartStep} from "../../www/page/front/module/cart/CartStep";

class UtilOAuth extends React.Component {
	constructor(props) {
		super(props);
		this.set = this.set.bind(this);
		this.getFormattedDate = this.getFormattedDate.bind(this);
	}

	//نوع خطاهای لاگین
	static loginExceptionEnum = {
		//کلمه کاربری یا رمز عبور درست وارد نشده و هنوز تعداد لاگینهای اشتباه به بیش از 3 بار نرسیده است
		"USERNAME_OR_PASSWORD_INVALID": "loginException.usernameOrPasswordInvalid",
		//تعداد لاگین های اشتباه بیشتر از سه بار شده است و کپچا اصلا وارد نشده است
		"CAPTCHA_REQUIRED": "loginException.captchaRequired",
		//تعداد لاگین های اشتباه بیشتر از سه بار شده است و کپچا به درستی وارد نشده است
		"CAPTCHA_INVALID": "loginException.captchaInvalid",
	};

	static login(username, password, captchaKey, captchaValue, redirectUrl) {
		// console.log("redirectUrl", redirectUrl);
		UtilCommon.checkUndefinedNullEmpty(username, true, true, true);
		UtilCommon.checkUndefinedNullEmpty(password, true, true, true);
		UtilCommon.checkUndefinedNullEmpty(captchaKey, false, false, true);
		UtilCommon.checkUndefinedNullEmpty(captchaValue, false, false, true);

		var clientId = "web-client";
		var clientSecret = "pin";
		var url = Config.apiToken;
		var data = {
			"grant_type": "password",
			"username": username,
			"password": password
		};
		var beforeSendXhrHeaderObject = {
			"Authorization": "Basic " + btoa(clientId + ":" + clientSecret),
			"captchaKey": captchaKey,
			"captchaValue": captchaValue
		};

		// var result = UtilAjax.sendRequest(this, UtilAjax.requestTypeEnum.POST_HTML, UtilAjax.responseDataTypeEnum.HTML, UtilAjax.mimeTypeEnum.JSON, RepoVar.get(RepoVar.keyEnum.SERVERAPI).apiDomainPort + url, data, false, true, beforeSendXhrHeaderObject, null, true, null, null);

		var result = UtilAjax.msRequest(this,Microservice.urlEnum.MSLOGIN.OAUTH.LOGIN, data, false, true, beforeSendXhrHeaderObject, null, true, null, null);


		// console.log("UtilOauth.login result", result);
		if (result.status === 200) {
			// var jwt = result.data;
			// // var expireDate = new Date(new Date().getTime() + (1000 * jwt.exp));
			// UtilCookie.jsonSet(RepoVar.get(RepoVar.keyEnum.PAGE).tokenCookieName, jwt);
			// this.fillLoggedInModel();
			// CartStep.cartAfterLoginSync();
			// window.location.reload();
			// // console.log("redirectUrl",redirectUrl)
			// // if (redirectUrl !== undefined && redirectUrl !== null && redirectUrl !== "") {
			// // 	UtilCommon.goToUrl(redirectUrl);
			// // }else{
			// // 	console.log("1111111111111111111111111111")
			// // }

			var token = JSON.parse(result.data);
			// console.log("token", token);
			var tokenExpireDate = new Date(new Date().getTime() + (1000 * token.expires_in));
			// console.log("formatted expire date", this.getFormattedDate(tokenExpireDate));
			var tokenCookie = {
				"token": token,
				"tokenExpireDate": tokenExpireDate
			};
			// console.log("tokenCookie", tokenCookie);
			UtilCookie.jsonSet(RepoVar.get(RepoVar.keyEnum.PAGE).tokenCookieName, tokenCookie);
			this.fillLoggedInModel();
			CartStep.cartAfterLoginSync();
			if (redirectUrl !== undefined && redirectUrl !== null && redirectUrl !== "") {
				UtilCommon.goToUrl(redirectUrl);
			} else {
				window.location.reload();
			}


		} else {
			UtilCookie.remove(RepoVar.get(RepoVar.keyEnum.PAGE).tokenCookieName);

		}
		return result;

	}

	static getGridActionList() {
		var gridActionList = RepoVar.get(RepoVar.keyEnum.LOGGEDINMODEL).userPositionRoleUnitCurrent.gridBtnList;
		// var loggedInUserPositionRoleUnitList = RepoVar.get(RepoVar.keyEnum.LOGGEDINMODEL).loggedInUserPositionRoleUnitList;
		var customerGridActionList = {
			"/#/profile/userGiftMaster": [
				{
					"href": "/#/profile/userGiftSetOwner",
					"icon": "fas fa-money-bill-alt",
					"id": "profile_grid1_userGiftSetOwner",
					"parentGrid": "grid1",
					"title": "acl.profile.userGiftSetOwner"
				}
			],
			"/#/profile/logSystemNotifyMaster": [
				{
					"href": "/#/profile/logSystemNotifyView",
					"icon": "fas fa-list",
					"id": "profile_grid1_logSystemNotifyView",
					"parentGrid": "grid1",
					"title": "acl.profile.logSystemNotifyView"
				}
			],
			"/#/profile/userContactMaster": [
				{
					"href": "/#/profile/userContactCreate",
					"icon": "fas fa-plus-circle",
					"id": "profile_grid1_userContactCreate",
					"parentGrid": "grid1",
					"title": "acl.profile.userContactCreate"
				}, {
					"href": "/#/profile/userContactUpdate",
					"icon": "fas fa-pencil-alt",
					"id": "profile_grid1_userContactUpdate",
					"parentGrid": "grid1",
					"title": "acl.profile.userContactUpdate"
				}, {
					"href": "/#/profile/userContactDelete",
					"icon": "fas fa-trash",
					"id": "profile_grid1_userContactDelete",
					"parentGrid": "grid1",
					"title": "acl.profile.userContactDelete"
				}, {
					"href": "/#/profile/userContactDefault",
					"icon": "fas fa-check-square",
					"id": "profile_grid1_userContactDefault",
					"parentGrid": "grid1",
					"title": "acl.profile.userContactDefault"
				}
			],
			"/#/profile/purchaseOrderMaster": [
				{
					"href": "/#/profile/purchaseOrderView",
					"icon": "fa fa-list",
					"id": "purchaseOrder_grid1_purchaseOrderView",
					"parentGrid": "grid1",
					"title": "acl.purchaseOrder.view"
				}, {
					"href": "/#/profile/userContactUpdate",
					"icon": "fas fa-file-alt",
					"id": "purchaseOrder_grid1_purchaseOrderFactor",
					"parentGrid": "grid1",
					"title": "acl.purchaseOrder.factor"
				}, {
					"href": "/#/profile/purchaseOrderCarrierGroupView",
					"icon": "fa fa-list",
					"id": "purchaseOrderCarrierGroup_grid2_view",
					"parentGrid": "grid2",
					"title": "acl.purchaseOrderCarrierGroup.view"
				}, {
					"href": "/#/profile/purchaseOrderCarrierGroupDeliveryConfirm",
					"icon": "fas fa-clipboard-check",
					"id": "purchaseOrderCarrierGroup_grid2_deliveryConfirm",
					"parentGrid": "grid2",
					"title": "acl.purchaseOrderCarrierGroup.deliveryConfirm"
				},
			]
		};
		gridActionList = Object.assign({}, gridActionList, customerGridActionList);
		// for (var id in loggedInUserPositionRoleUnitList) {
		//   var userPositionRoleUnit = loggedInUserPositionRoleUnitList[id];
		//   if (userPositionRoleUnit.loggedInRole.type === "FRONT_CUSTOMER") {
		//     gridActionList = Object.assign({}, gridActionList, userPositionRoleUnit.gridBtnList);
		//   }
		// }
		return gridActionList;
	}

	static fillLoggedInModel() {
		// var url = "/general/getLoggedInModel";
		// var result = UtilAjax.sendRequest(this, UtilAjax.requestTypeEnum.POST_JSON, UtilAjax.responseDataTypeEnum.JSON, UtilAjax.mimeTypeEnum.JSON, RepoVar.get(RepoVar.keyEnum.SERVERAPI).apiUrl + url, {}, false, false, null, null, true, null, null);
		// if (result.data !== undefined && result.data !== null) {
		// 	var softwareVersion = {
		// 		"softwareVersionDesc": result.data.softwareVersionDesc,
		// 		"softwareVersionNo": result.data.softwareVersionNo
		// 	};
		// }
		// RepoVar.set(RepoVar.keyEnum.SOFTWAREVERSION, softwareVersion);
		// if (result.status === 200) {
		// 	result.data["userPositionRoleUnitList"] = [];
		// 	RepoVar.set(RepoVar.keyEnum.LOGGEDINMODEL, result.data);
		// 	RepoVar.set(RepoVar.keyEnum.GRIDACTIONLIST, this.getGridActionList());
		// } else {
		// 	RepoVar.set(RepoVar.keyEnum.LOGGEDINMODEL, null);
		// 	UtilCookie.remove(RepoVar.get(RepoVar.keyEnum.PAGE).tokenCookieName);
		// }
		// console.log(RepoVar.get(RepoVar.keyEnum.LOGGEDINMODEL));
	}

	static parseJwt(token) {
		var base64Url = token.split(".")[1];
		var base64 = base64Url.replace("-", "+").replace("_", "/");
		return JSON.parse(window.atob(base64));
	}

	static refreshToken() {
		// var jwt = UtilCookie.jsonGet(RepoVar.get(RepoVar.keyEnum.PAGE).tokenCookieName);
		// if (jwt != null) {
		// 	var clientId = "web-client";
		// 	var clientSecret = "pin";
		// 	var url = "/oauth/token?grant_type=refresh_token&refresh_token=" + jwt.refresh_token;
		// 	var data = {}
		// 	var beforeSendXhrHeaderObject = {
		// 		"Authorization": "Basic " + btoa(clientId + ":" + clientSecret)
		// 	}

		// 	var result = UtilAjax.sendRequest(this, UtilAjax.requestTypeEnum.POST_HTML, UtilAjax.responseDataTypeEnum.HTML, UtilAjax.mimeTypeEnum.JSON, RepoVar.get(RepoVar.keyEnum.SERVERAPI).apiDomainPort + url, data, false, true, beforeSendXhrHeaderObject, null, true, null, null);
		// 	if (result.status === 200) {
		// 		jwt = result.data;
		// 		UtilCookie.jsonSet(RepoVar.get(RepoVar.keyEnum.PAGE).tokenCookieName, jwt);
		// 		this.fillLoggedInModel();
		// 	} else {
		// 		UtilCookie.remove(RepoVar.get(RepoVar.keyEnum.PAGE).tokenCookieName);
		// 	}
		// } else {
		// 	alert("token is null");
		// }


		var tokenCookie = UtilCookie.jsonGet(RepoVar.get(RepoVar.keyEnum.PAGE).tokenCookieName);
		var token = null;
		if (tokenCookie !== undefined && tokenCookie != null && tokenCookie.token != undefined && tokenCookie.token != null && tokenCookie.token.refresh_token !== undefined && tokenCookie.token.refresh_token !== null) {
			var clientId = "web-client";
			var clientSecret = "pin";
			var url = Config.apiToken + "?grant_type=refresh_token&refresh_token=" + tokenCookie.token.refresh_token;
			var data = {};
			var beforeSendXhrHeaderObject = {
				"Authorization": "Basic " + btoa(clientId + ":" + clientSecret)
			};

			var result = UtilAjax.sendRequest(this, UtilAjax.requestTypeEnum.POST_HTML, UtilAjax.responseDataTypeEnum.HTML, UtilAjax.mimeTypeEnum.JSON, RepoVar.get(RepoVar.keyEnum.SERVERAPI).apiDomainPort + url, data, false, true, beforeSendXhrHeaderObject, null, true, null, null);
			// console.log("UtilOauth.refreshToken result", result);
			if (result.status === 200) {
				var token = JSON.parse(result.data);
				// console.log("token", token);
				var tokenExpireDate = new Date(new Date().getTime() + (1000 * token.expires_in));
				// console.log("formatted expire date", this.getFormattedDate(tokenExpireDate));
				var tokenCookie = {
					"token": token,
					"tokenExpireDate": tokenExpireDate
				};
				// console.log("tokenCookie", tokenCookie);
				UtilCookie.jsonSet(RepoVar.get(RepoVar.keyEnum.PAGE).tokenCookieName, tokenCookie);
				this.fillLoggedInModel();
			} else {
				UtilCookie.remove(RepoVar.get(RepoVar.keyEnum.PAGE).tokenCookieName);
			}
		}
	}

	static getToken() {
		// var jwt = UtilCookie.jsonGet(RepoVar.get(RepoVar.keyEnum.PAGE).tokenCookieName);
		// if (jwt !== undefined && jwt != null && jwt.access_token !== undefined && jwt.access_token !== null) {
		// 	var jwtPayload = this.parseJwt(jwt.access_token)
		// 	var jwtExpDate = new Date(1000 * jwtPayload.exp);
		// 	var nowDate = new Date();
		// 	if (nowDate > jwtExpDate) {
		// 		this.refreshToken();
		// 		jwt = UtilCookie.jsonGet(RepoVar.get(RepoVar.keyEnum.PAGE).tokenCookieName);
		// 		if (jwt != null) {
		// 			jwtPayload = this.parseJwt(jwt.access_token)
		// 			jwtExpDate = new Date(1000 * jwtPayload.exp);
		// 		}
		// 	}
		// }
		// return jwt;


		var tokenCookie = UtilCookie.jsonGet(RepoVar.get(RepoVar.keyEnum.PAGE).tokenCookieName);
		var token = null;
		// console.log("tokenCookie",tokenCookie);

		if (tokenCookie !== undefined && tokenCookie != null && tokenCookie.token != undefined && tokenCookie.token != null && tokenCookie.token.access_token !== undefined && tokenCookie.token.access_token !== null) {
			//var jwtPayload = this.parseJwt(jwt.access_token)
			//var jwtExpDate = new Date(1000 * jwtPayload.exp);
			token = tokenCookie.token;
			var tokenExpireDate = new Date(tokenCookie.tokenExpireDate);
			var nowDate = new Date();
			// console.log("nowDate", this.getFormattedDate(nowDate));
			// console.log("tokenExpireDate", this.getFormattedDate(tokenExpireDate));
			if (nowDate > tokenExpireDate) {
				// console.log("UtilOauth.getToken nowDate > tokenExpireDate");
				this.refreshToken();
				var tokenCookie = UtilCookie.jsonGet(RepoVar.get(RepoVar.keyEnum.PAGE).tokenCookieName);
				if (tokenCookie !== undefined && tokenCookie != null && tokenCookie.token != undefined && tokenCookie.token != null && tokenCookie.token.access_token !== undefined && tokenCookie.token.access_token !== null) {
					token = tokenCookie.token;
				} else {
					token = {
						"access_token": "refreshTokenExpired"
					};
				}
			}
		}

		return token;
	}

	static addTokenToUrl(url) {
		if (UtilOAuth.getToken() !== null) {
			if (url.indexOf("?") === -1) {
				url += "?access_token=" + UtilOAuth.getToken().access_token;
			} else {
				url += "&access_token=" + UtilOAuth.getToken().access_token;
			}
		}
		return url;
	}

	static getBearerHeader() {
		var result = "";
		var jwt = this.getToken();
		if (jwt !== null) {
			result = "Bearer " + jwt.access_token;
		}
		return result;
	}

	static logout() {
		var tokenCookie = UtilCookie.jsonGet(RepoVar.get(RepoVar.keyEnum.PAGE).tokenCookieName);
		if (tokenCookie !== undefined && tokenCookie != null && tokenCookie.token != undefined && tokenCookie.token != null && tokenCookie.token.access_token !== undefined && tokenCookie.token.access_token !== null) {
			var tokenExpireDate = new Date(tokenCookie.tokenExpireDate);
			var nowDate = new Date();
			// console.log("nowDate", this.getFormattedDate(nowDate));
			// console.log("tokenExpireDate", this.getFormattedDate(tokenExpireDate));
			if (nowDate <= tokenExpireDate) {
				var url = Config.apiLogout;
				var data = {};
				UtilAjax.sendRequest(this, UtilAjax.requestTypeEnum.GET, UtilAjax.responseDataTypeEnum.HTML, UtilAjax.mimeTypeEnum.HTML, RepoVar.get(RepoVar.keyEnum.SERVERAPI).apiDomainPort + url, data, false, true, null, null, true, null, null);
			}
			UtilCookie.remove(RepoVar.get(RepoVar.keyEnum.PAGE).tokenCookieName);
			// console.log(RepoVar.get(RepoVar.keyEnum.PAGE).url.urlFullArray[5]);
			// console.log(RepoVar.get(RepoVar.keyEnum.PAGE).url);
			if (RepoVar.get(RepoVar.keyEnum.PAGE).url.urlFullArray[5] === "fp#" || RepoVar.get(RepoVar.keyEnum.PAGE).url.urlFullArray[6] === "profile") {
				UtilCommon.goToUrlHome();
			} else if (RepoVar.get(RepoVar.keyEnum.PAGE).url.urlFullArray[6] === "cart") {
				if (RepoVar.get(RepoVar.keyEnum.SHOP).vitrin.id != null) {
					url = RepoVar.get(RepoVar.keyEnum.PAGE).homePage + "shop/" + RepoVar.get(RepoVar.keyEnum.SHOP).vitrin.id + "/";
				}
				UtilCommon.goToUrl(url + "#/cart/1");
				window.location.reload();
			} else {
				window.location.reload();
			}
		}
	}


	static changeCurrentUpru(upruId) {
		var url = "/general/changeCurrentUserPosition/id/" + parseInt(upruId, 10);
		var data = {};
		var result = UtilAjax.sendRequest(this, UtilAjax.requestTypeEnum.GET, UtilAjax.responseDataTypeEnum.JSON, UtilAjax.mimeTypeEnum.JSON, RepoVar.get(RepoVar.keyEnum.SERVERAPI).apiUrl + url, data, false, false, null, null, true, null, null);
		if (result.status === 200) {
			this.fillLoggedInModel();
		}
	}

	static changeDefaultUpru(upruId) {
		var url = "/user/setDefaultUserPosition";
		var data = {
			"userPositionId": parseInt(upruId, 10)
		};
		var result = UtilAjax.sendRequest(this, UtilAjax.requestTypeEnum.POST_JSON, UtilAjax.responseDataTypeEnum.JSON, UtilAjax.mimeTypeEnum.JSON, RepoVar.get(RepoVar.keyEnum.SERVERAPI).apiUrl + url, data, false, false, null, null, true, null, null);
		if (result.status === 200) {
			this.fillLoggedInModel();
		}
	}

	static getFormattedDate(inputDate) {
		// console.log("inputDate",inputDate)
		if (inputDate !== null) {
			var month = inputDate.getMonth() + 1;
			var day = inputDate.getDate();
			var year = inputDate.getFullYear();
			var hours = inputDate.getHours();
			var minutes = inputDate.getMinutes();
			var seconds = inputDate.getSeconds();
			var strTime = hours + ":" + minutes + ":" + seconds;
			return year + "/" + month + "/" + day + " " + strTime;
		} else {
			return null;
		}
	}

}

export {
	UtilOAuth
};
