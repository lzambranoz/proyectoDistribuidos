package apigatewaylambda;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;

public class Pdf2jpg implements RequestHandler<RequestClass, String> {
    private final String JPG_TYPE = (String) "jpg";
    private final String JPG_MIME = (String) "image/jpg";

    public String handleRequest(RequestClass request, Context context) {
        LambdaLogger logger = context.getLogger();

        String srcBucket = request.srcbucket;
        logger.log("lazal aws lambda converter - src_bucket name: " + srcBucket);

        String srcKey = request.srckey;
        logger.log("lazal aws lambda converter - src_key name: " + srcKey);

        String dstBucket = request.dstbucket;
        logger.log("lazal aws lambda converter - dst_bucket name: " + srcBucket);

        String dstKey = request.dstkey;
        logger.log("lazal aws lambda converter - dst_key name: " + srcKey);

        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
            S3Object s3Object = s3Client.getObject(new GetObjectRequest(srcBucket, srcKey));
            logger.log("lazal aws lambda converter - object obtained as InputStream from (" + srcBucket + ", " + srcKey + ").");

            InputStream objectData = s3Object.getObjectContent();
            PDDocument pd = PDDocument.load(objectData);
            PDFRenderer pr = new PDFRenderer(pd);
            BufferedImage bi = pr.renderImageWithDPI (0, 300);
            logger.log("lazal aws lambda converter - image rendered from pd");

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write (bi, JPG_TYPE, os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            logger.log("lazal aws lambda converter - image generated");

            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(os.size());
            meta.setContentType(JPG_MIME);
            logger.log("lazal aws lambda converter - metadata for image ready");

            logger.log("lazal aws lambda converter - uploading image: " + dstKey + " to " + dstBucket);
            s3Client.putObject(dstBucket, dstKey, is, meta);
            logger.log("lazal aws lambda converter - image uploaded");

            return "Ok";

        } catch(IOException e){
            logger.log("lazal aws lambda converter - IO exception");
            throw new RuntimeException(e);
        }
    }
}
