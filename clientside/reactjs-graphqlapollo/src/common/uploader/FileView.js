import React, {useState} from "react";

import Downloader from "js-file-downloader";

import Grid from "@material-ui/core/Grid";
import Fab from "@material-ui/core/Fab";
import DownloadIcon from "@material-ui/icons/CloudDownload";
import EyeIcon from "@material-ui/icons/RemoveRedEye";
import DeleteIcon from "@material-ui/icons/Delete";


export default function FileView(props) {

	const statusEnum = {
		"ADDED": "ADDED", //فایل جدید آپلود شده است
		"DELETED": "DELETED",  //فایل حذف شده است
		"EXISTED": "EXISTED", //فایل از قبل وجود داشته و بدون تغییر مانده است
	};

	let initialState = {
		"error": null,
		"dataIsLoaded": true,
		"objectList": props.objectList,
		"urlBase": props.urlBase,
		"thumbnailWidth": "80px",
		"thumbnailHeight": "auto",
		"hasDownload": props.hasDownload,
		"hasView": props.hasView,
		"hasDelete": props.hasDelete,
	};

	//تعریف متغیر state آپلودر
	const [fileViewData, setFileViewData] = useState(initialState);

	const onChange = (e) => {
		let objectList = fileViewData.objectList;
		let index = e.currentTarget.getAttribute("index");
		if (objectList[index] !== undefined) {
			objectList[index]["statusEnum"] = statusEnum.DELETED;

			setFileViewData({"objectList": objectList},
				function () {
					if (props.onChange) {
						props.onChange(JSON.parse(JSON.stringify(fileViewData)), objectList[index]);
					}
				});
		}
	};


	const onClick=(e)=> {
		const { objectList } = fileViewData;
		let index = e.currentTarget.getAttribute("index");
		let fileViewModel = objectList[index];
		if (fileViewModel !== undefined) {
			if (props.onClick) {
				props.onClick(fileViewModel);
			}
		}
	};

	const onView = (e)=> {
		const { objectList, urlBase } = fileViewData;
		let index = e.currentTarget.getAttribute("index");
		let fileViewModel = objectList[index];
		if (fileViewModel !== undefined) {
			window.open(urlBase + fileViewModel.hashedPath + "/");
		}
	};

	const onDownload = (e) => {
		const { objectList, urlBase } = fileViewData;
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

	const getSizeTitle = (sizeInByte) => {
		let sizeTitle = "";
		if (sizeInByte < 1000000) {
			sizeTitle = parseInt(sizeInByte / 1024) + " کیلوبایت";
		} else {
			sizeTitle = parseInt(sizeInByte / 1024) + " مگابایت";
		}
		return sizeTitle;
	};


		let fileViewHtmlList = [];
			fileViewHtmlList = fileViewData.objectList.map(function (fileViewModel, index) {
				if (fileViewModel.statusEnum !== statusEnum.DELETED) {
					let isImage = false;
					let hasAction = false;
					let htmlDownload = <div></div>;
					let htmlView = <div></div>;
					let htmlDelete = <div></div>;
					let url = "";
					let title = "نام فایل : " + fileViewModel.fullName + "\n حجم فایل:" + getSizeTitle(fileViewModel.size);
					if (fileViewModel.hashedPath !== undefined && fileViewModel.hashedPath !== null && fileViewModel.hashedPath !== "") {
						url = fileViewData.urlBase + fileViewModel.hashedPath + "/";
						title += "\n آخرین تغییر:" + fileViewModel.lastModifiedDate.year + "/" + fileViewModel.lastModifiedDate.month + "/" + fileViewModel.lastModifiedDate.day;
						if (fileViewData.hasDownload) {
							hasAction = true;
							htmlDownload =
								<div index={index} onClick={onDownload}>
									<Fab color="primary" aria-label="دانلود" className="buttonFileView">
										<DownloadIcon/>
									</Fab>
								</div>;
						}
						if (fileViewData.hasView) {
							hasAction = true;
							htmlView =
								<div index={index} onClick={onView}>
									<Fab color="primary" aria-label="دانلود" className="buttonFileView">
										<EyeIcon/>
									</Fab>
								</div>;
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
					if (fileViewData.hasDelete) {
						hasAction = true;
						htmlDelete =
							<div index={index} onClick={onChange}>
								<Fab color="primary" aria-label="دانلود" className="buttonRemoveFileView">
									<DeleteIcon/>
								</Fab>
							</div>;
					}

					if (isImage) {
						if (hasAction) {
							return (<React.Fragment key={Math.random()}>
								<div className="boxImgFileView">
									<div className="divParentImg">
										<Grid container spacing={1}>
											<img title={title} index={index} onClick={onClick} src={url}
												 width={fileViewData.thumbnailWidth}
												 height={fileViewData.thumbnailHeight}
												 style={{"cursor": "pointer"}}/>
										</Grid>
									</div>
									<Grid container spacing={1}>
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
									<Grid container spacing={1}>
										<img title={title} index={index} onClick={onClick} src={url}
											 width={fileViewData.thumbnailWidth} height={fileViewData.thumbnailHeight}
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
										<Grid container spacing={1}>
											<div title={title} index={index} onClick={onClick}
												 className={"fi fi-" + fileViewModel.extension} style={{
												"width": fileViewData.thumbnailWidth,
												"height": "88px",
											}}>
												<div className="fi-content" style={{
													"textAlign": "center",
													"fontSize": "25px",
												}}>{fileViewModel.extension.toUpperCase()}</div>
											</div>
										</Grid>
									</div>
									<Grid container spacing={1}>
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
									<Grid container spacing={1}>
										<div title={title} index={index} onClick={onClick}
											 className={"fi fi-" + fileViewModel.extension} style={{
											"width": fileViewData.thumbnailWidth,
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
			});
		return (<React.Fragment>
				<Grid container spacing={1}>
					{fileViewHtmlList}
				</Grid>
		</React.Fragment>);
}


