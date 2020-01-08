$( document ).ready(function() {
$("#uploadFormJpeg").submit(function() {
var bucket = new AWS.S3({params: {Bucket: 'insertjpeg'}});
var uploadFiles = $('#upFileJpeg')[0];
var upFile = uploadFiles.files[0];
if (upFile) {
console.log("si hubo archivo");
var uploadParams = {Key: upFile.name, ContentType: upFile.type, Body: upFile};
bucket.upload(uploadParams).on('httpUploadProgress', function(evt) {
}).send(function(err, data) {
$('#upFileJpeg').val(null);
$("#showMessage").show();
});
}
return false;
});
});
