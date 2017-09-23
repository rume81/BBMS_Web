<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content=""/>
<meta name="keywords" content=""/>
<link rel="shortcut icon" href="${rc.contextPath}/css/common/images/favicon1.ico"/>
<title>入札借入管理システム操作マニュアル</title>
<link rel="stylesheet" href="${rc.contextPath}/css/common/style.css">
<script src="${rc.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/ajaxHelper.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/login.js"></script>
</head>
<body>
  <body>
<div class="container">
	<section id="content">
		<form action="">
			<h1>ログインフォーム</h1>
			<div>
				<input type="text" placeholder="ユーザー名" required="" id="username" name="username" />
			</div>
			<div>
				<input type="password" placeholder="パスワード" required="" id="password" name="password" onkeydown="if (event.keyCode == 13) document.getElementById('login').click()"/>
			</div>
			<div>
				<input type="button" value="ログイン" id="login" onclick="loginValided('${rc.contextPath}');">
				<a href="#">パスワードを忘れましたか？</a>
				<!--<a href="#">Register</a>-->
			</div>
		</form><!-- form -->
		<div class="button">
			<!--<a href="#">Download source file</a>-->
			<span class="error" id="errorMsg"></span>
		</div><!-- button -->
	</section><!-- content -->
</div><!-- container -->
</body>

    
   