# HandyWebView
一个方便使用的WebView库

#### CoreWebView

1 对原生WebView进行了二次封装，进行了一些例如自适应任意大小的pc网页，JavaScript支持，cookie支持等常规的设置

2 解决在5.0以上不显示htts图片的问题

3 解决3.0到4.2期间WebView的挂马漏洞风险

#### ProgressWebView

- 继承自CoreWebView，添加了顶部横向的页面加载进度条。

#### CoreWebViewClient

- 替换WebView加载失败时的展示页面

- 可以通过下面的方式自定义自己的页面风格（用来点击刷新的view请设置android:id="@+id/reload"）

```
 CoreWebViewClient.setErrorViewLayout(@LayoutRes int errorLayout)
 
```

#### 可在项目中添加依赖
1 Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2 Add the dependency
```
dependencies {
	       compile 'com.github.BeijingSnail:HandyWebView:1.2'
	}
```
3 记得添加网络权限...
```
<uses-permission android:name="android.permission.INTERNET"></uses-permission>
```
##### 系统默认的错误页面

![Image text](https://github.com/BeijingSnail/HandyWebView/blob/master/images/systemComes.png)

##### 替换后的效果图

![Image text](https://github.com/BeijingSnail/HandyWebView/blob/master/images/custom.png)

