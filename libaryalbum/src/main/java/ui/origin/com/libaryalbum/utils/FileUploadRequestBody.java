package ui.origin.com.libaryalbum.utils;



import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;
import ui.origin.com.libaryalbum.impl.UploadService;

/**
 * 项目名称：BlueSeaAndroid
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/7/18 14:37
 * 修改备注
 */
public class FileUploadRequestBody extends RequestBody {

    RequestBody requestBody;
    UploadService uploadService;
    private BufferedSink bufferedSink;

    public FileUploadRequestBody(File file, UploadService uploadService) {
        this.requestBody = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        this.uploadService = uploadService;
    }

    public FileUploadRequestBody(RequestBody requestBody, UploadService uploadService) {
        this.requestBody = requestBody;
        this.uploadService = uploadService;
    }

    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (bufferedSink == null) {
            //包装
            bufferedSink = Okio.buffer(sink(sink));
        }
        //写入
        requestBody.writeTo(bufferedSink);
        //必须调用flush，否则最后一部分数据可能不会被写入
        bufferedSink.flush();
    }
    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            //当前写入字节数
            long bytesWritten = 0L;
            //总字节长度，避免多次调用contentLength()方法
            long contentLength = 0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0) {
                    //获得contentLength的值，后续不再调用
                    contentLength = contentLength();
                }
                //增加当前写入的字节数
                bytesWritten += byteCount;
                //回调上传接口
                uploadService.onProgress(bytesWritten, contentLength, bytesWritten == contentLength);
            }
        };
    }
}
