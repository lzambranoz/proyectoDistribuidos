package apigatewaylambda;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.ObjectMetadata;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.StringBuffer;
import javax.imageio.ImageIO;

public class Jpg2png implements RequestHandler<RequestClass, String> {
    // format of the converted file
    private final String PNG_TYPE = (String) "png";
    private final String PNG_MIME = (String) "image/png";

    public String handleRequest(RequestClass request, Context context) {
        LambdaLogger logger = context.getLogger();

        String srcBucket = request.srcbucket;
        logger.log("sunjing aws lambda converter - src_bucket name: " + srcBucket);

        String srcKey = request.srckey;
        logger.log("sunjing aws lambda converter - src_key name: " + srcKey);

        String dstBucket = request.dstbucket;
        logger.log("sunjing aws lambda converter - dst_bucket name: " + dstBucket);

        String dstKey = request.dstkey;
        logger.log("sunjing aws lambda converter - dst_key name: " + dstKey);


        try {
            // Download the image from S3 into a stream
            AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
            S3Object s3Object = s3Client.getObject(new GetObjectRequest(srcBucket, srcKey));
            logger.log("sunjing aws lambda converter - object obtained as InputStream from (" + srcBucket + ", " + srcKey + ").");

            InputStream objectData = s3Object.getObjectContent();
            BufferedImage bi = ImageIO.read(objectData);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write (bi, PNG_TYPE, os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            logger.log("sunjing aws lambda converter - png generated");

            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(os.size());
            meta.setContentType(PNG_MIME);
            logger.log("sunjing aws lambda converter - metadata for png ready");

            logger.log("sunjing aws lambda converter - uploading png: " + dstKey + " to " + dstBucket);
            s3Client.putObject(dstBucket, dstKey, is, meta);
            logger.log("sunjing aws lambda converter - png uploaded");

            return "Ok";

        } catch(IOException e){
            logger.log("sunjing aws lambda converter - IO exception");
            throw new RuntimeException(e);
        }

    }
}
