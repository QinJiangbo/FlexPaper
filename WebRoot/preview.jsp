<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
<title>FlexPaper Preview</title>

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

<body>
	<div class="top">
		<div class="head">FlexPaper Preview</div>
	</div>

	<div class="down">
		<div id="upload">
			<div class="btn-item">
				<a class="btn btn-primary button" id="index" href="index.jsp">Index</a>
			</div>
			<div class="btn-item">
				<a class="btn btn-danger button" id="allfiles" href="files.jsp">All Files</a>
			</div>
		</div>
		<div id="viewerPlaceHolder"></div>
		<script type="text/javascript">
			$('#viewerPlaceHolder').FlexPaperViewer({
				config : {
					SwfFile : escape('swf/Hello.swf'),
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
	</div>
</body>
</html>
