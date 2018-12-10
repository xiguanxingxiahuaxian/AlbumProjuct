1项目持续更新，暂时退出一个简版

UpLoadSimpleActivity 上传activity

AlbumActivity 相册打开activity

2启动例子代码

    ShowImageCoreBean core =
                new ShowImageCoreBean
                        .Builder()
                        .columSize(2)
                        .imageCheckSize(9)
                        .build();
        Intent intent =new Intent(Work121001.this, UpLoadSimpleActivity.class);
        intent.putExtra("corebean",core);
        startActivity(intent);

