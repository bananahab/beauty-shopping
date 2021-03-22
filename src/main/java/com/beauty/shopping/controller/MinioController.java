package com.beauty.shopping.controller;

import com.beauty.shopping.common.api.CommonResult;
import com.beauty.shopping.dto.MinioUploadDto;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.policy.PolicyType;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wuzhenxian
 * @date 2021/03/17
 */
@Api(tags = "MinioController")
@Controller
@RequestMapping("/minio")
@Slf4j
public class MinioController {
    @Value("${minio.endpoint}")
    private String endPoint;
    @Value("${minio.bucketName}")
    private String bucketName;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    @ApiOperation("文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult upload(@RequestParam("file")MultipartFile file) {
        try {
            //创建一个minIO的Java客户端
            MinioClient minioClient = new MinioClient(endPoint, accessKey, secretKey);
            boolean isExist = minioClient.bucketExists(bucketName);
            if (isExist) {
                log.info("存储桶已经存在");
            } else {
                //创建存储桶并设置只读权限
                minioClient.makeBucket(bucketName);
                minioClient.setBucketPolicy(bucketName, "*.*", PolicyType.READ_ONLY);
            }

            String fileName = file.getOriginalFilename();
            long nowDate = System.currentTimeMillis();
            //设置存储对象名称
            String objectName = nowDate + fileName;
            //使用putObject上传一个文件到存储桶中
            minioClient.putObject(bucketName, objectName, file.getInputStream(), file.getContentType());
            log.info("文件上传成功");
            MinioUploadDto minioUploadDto = new MinioUploadDto();
            minioUploadDto.setName(objectName);
            minioUploadDto.setUrl(endPoint + "/" + bucketName + "/" + objectName);
            return CommonResult.success(minioUploadDto);
        } catch (Exception e) {
            log.error("上传文件发送错误：{}", e.getMessage());
        }
        return CommonResult.failed("文件上传失败");
    }

    @ApiOperation("文件删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("objectName") String objectName) {
        try {
            MinioClient minioClient = new MinioClient(endPoint, accessKey, secretKey);
            minioClient.removeObject(bucketName, objectName);
            log.info("删除文件成功");
            return CommonResult.success("删除文件成功");
        } catch (Exception e) {
            log.error("删除文件错误：{}", e.getMessage());
        }
        return CommonResult.failed("删除文件失败");
    }

}
