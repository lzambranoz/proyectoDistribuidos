import sys
import os
import convertapi
import tempfile


def word_to_pdf(_in):
    convertapi.api_secret = os.environ['CONVERT_API_SECRET']
    upload_io = convertapi.UploadIO(open(_in, 'rb'))
    saved_files = convertapi.convert('pdf', { 'File': upload_io }).save_files(tempfile.gettempdir())
    print("The PDF saved to %s" % saved_files)
    
word_to_pdf(sys.argv[1])
