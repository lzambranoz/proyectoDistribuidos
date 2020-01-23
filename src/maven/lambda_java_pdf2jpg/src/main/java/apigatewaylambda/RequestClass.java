package apigatewaylambda;

public class RequestClass {
    String srcbucket;
    String srckey;
    String dstbucket;
    String dstkey;

    public String getsrcbucket() { return this.srcbucket; }
    public void setsrcbucket(String srcbucket) { this.srcbucket = srcbucket; }

    public String getSrckey() { return this.srckey; }
    public void setSrckey(String srckey) { this.srckey = srckey; }

    public String getdstbucket() { return this.dstbucket; }
    public void setdstbucket(String dstbucket) { this.dstbucket = dstbucket; }

    public String getDstkey() { return this.dstkey; }
    public void setDstkey(String dstkey) { this.dstkey = dstkey; }

    public RequestClass(String srcbucket, String srckey, String dstbucket, String dstkey) {
        this.srcbucket = srcbucket;
        this.srckey = srckey;
        this.dstbucket = dstbucket;
        this.dstkey = dstkey;
    }

    public RequestClass() { }
}
