package com.framework.common.util;

import com.framework.common.web.WebContextFactory;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by hqq on 2015/8/26.
 */
public class FileUtil {
    public static String saveFile(Integer customerId,MultipartFile upload,String tag){
        File dir = new File(WebContextFactory.localUploadPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String path = customerId + tag+"."+ FilenameUtils.getExtension(upload.getOriginalFilename());
        File source = new File(WebContextFactory.localUploadPath+ path );
        try {
            upload.transferTo(source);
        } catch (Exception e) {
            return null;
        }

        return path;
    }
}
