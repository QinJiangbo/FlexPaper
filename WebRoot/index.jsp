<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>

<!DOCTYPE HTML>
<html>
<head>
<title>FlexPaper Index</title>

<!-- import the scripts support -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/flexpaper.js"></script>
<script type="text/javascript" src="js/flexpaper_flash.js"></script>
<script type="text/javascript" src="js/flexpaper_flash_debug.js"></script>
<script type="text/javascript" src="js/script.js"></script>

<!-- define the style of the page -->
<link rel="stylesheet" type="text/css" href="css/flexpaper.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

</head>
${msg}
<body>
	<div class="top">
		<div class="head">FlexPaper Preview</div>
	</div>
	
	<div class="down">
		<div id="upload">
			<form action="UploadServlet" method="post" enctype="multipart/form-data">
				<div class="item">
					<input type="file" name="uploadFile" id="uploadFile"/>
				</div>
				<div class="item">
					<div class="label1">Title</div><div class="item-form"><input type="text" name="title" id="title"
						class="form-control" /></div>
				</div>
				<div class="item">
					<div class="label1">Catalog</div><div class="item-form"><input type="text" name="catalog" id="catalog"
						class="form-control" /></div>
				</div>
				<div class="item">
					<div class="label1">Author</div><div class="item-form"><input type="text" name="author" id="author"
						class="form-control" /></div>
				</div>
				<div class="item">
					<div class="label1">Publish Date</div><div class="item-form"><input type="text" name="publishDate" id="publishDate"
						class="form-control" /></div>
				</div>
				<div class="btn-item">
					<button type="submit" class="btn btn-success button">Upload</button>
				</div>
			</form>
			<form action="PreviewServlet?path=${file_path}" method="post">
				<div class="btn-item">
					<button type="submit" class="btn btn-primary button">Preview</button>
				</div>
				<div class="btn-item">
					<a class="btn btn-danger button" id="allfiles" href="files.jsp">All Files</a>
				</div>
			</form>
		</div>
		<div id="viewerPlaceHolder"></div>
		<script type="text/javascript">
			$('#viewerPlaceHolder').FlexPaperViewer({
				config : {
					SwfFile : escape('swf/${name}.swf'),
					Scale : 0.6,
					ZoomTransition : 'easeOut',
					ZoomTime : 0.5,
					ZoomInterval : 0.2,
					FitPageOnLoad : true,
					FitWidthOnLoad : false,
					FullScreenAsMaxWindow : false,
					ProgressiveLoading : false,
					MinZoomSize : 0.2,
					MaxZoomSize : 5,
					SearchMatchAll : false,
					InitViewMode : 'Portrait',
					ViewModeToolsVisible : true,
					ZoomToolsVisible : true,
					NavToolsVisible : true,
					CursorToolsVisible : true,
					SearchToolsVisible : true,
					localeChain : 'en_US'
				}
			});
		</script>
</body>
</html>
