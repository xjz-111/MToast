#### MToast
```
一个简单的Toast，使用者可以配置全局属性，也可单个控制属性，也可自定义View等。
```
#### 一. 功能介绍
* 支持全局配置Toast统一Config
* 支持单个控制icon等
* 支持单个配置Config
* 支持自定义View
* 控制时长使用同系统Toast
#### 二. 具体使用
1. 添加依赖和配置
``` gradle
  // 在project的build.gradle中添加
  repositories {
      ...
      maven { url 'https://jitpack.io' }
  }
  
  dependencies {
    // 替换成最新版本
    implementation 'com.github.xjz-111:mToasr:*.*.*'
    ...
  }
```
2. 具体使用[task-api](https://github.com/xjz-111/task-api)
* 初始化工作可以使用 - [Android启动任务管理器](https://github.com/xjz-111/task-api)
* 初始化全局配置参见 - [MToast.Config](https://github.com/xjz-111/MToast/blob/master/mToast/src/main/java/com/leslie/mtoast/MToast.java)
``` java
  // 初始化全局配置
  MToast.configGlobal(getApplicationContext())
                .iconResId(R.mipmap.a)
                .textSize(16)
                .typeface(Typeface.DEFAULT)
                .duration(MToast.LENGTH_SHORT)
                .gravity(Gravity.CENTER, 0, 0)
                .paddingTop(15)
                .paddingRight(15);
  
  // 简单显示
  MToast.show("AJSDASJDH");

  // 带时间控制
  MToast.show("带时间控制", MToast.LENGTH_LONG);

  // 带icon
  MToast.show("带icon", getResources().getDrawable(R.mipmap.ic_launcher));

  // 带Config
  MToast.show("带Config", MToast.config().duration(MToast.LENGTH_LONG).iconResId(R.mipmap.ic_launcher).typeface(Typeface.DEFAULT_BOLD));

  // 自定义view
  View view = LayoutInflater.from(this).inflate(com.leslie.mtoast.R.layout.mtoast, null);
  view.setBackgroundResource(R.drawable.toast_bg);
  ImageView imageView = view.findViewById(com.leslie.mtoast.R.id.toast_icon);
  TextView textView = view.findViewById(com.leslie.mtoast.R.id.toast_text);
  textView.setTextColor(Color.GREEN);
  textView.setText("自定义view");
  imageView.setImageResource(R.mipmap.a);
  MToast.show(view);

  // 自定义view - 带config
  MToast.show(view, MToast.config().duration(MToast.LENGTH_LONG).gravity(Gravity.BOTTOM, 0, 100));

```
