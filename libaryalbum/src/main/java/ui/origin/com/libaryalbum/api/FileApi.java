package ui.origin.com.libaryalbum.api;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import ui.origin.com.libaryalbum.bean.ImageBean;


/**
 * 项目名称：Album
 * 类描述：
 * 创建人：maw@neuqsoft.com
 * 创建时间： 2018/11/28 16:11
 * 修改备注
 */
public interface FileApi {

    @Multipart
    @POST("anonymous/apply/infofile")
    Call<ImageBean> uploadFilesWithParts(@Query("whichidcard") String idcard,
                                         @Part() List<MultipartBody.Part> parts);
}
