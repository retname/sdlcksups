package com.ordermanager.common.utils.http;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageFileUtil {





    /**
     * @param pdfPath  pdf文件的路径
     * @param savePath 图片保存的地址
     * @throws IOException
     */
    public static void pdf2img(String pdfPath, String savePath) throws IOException {
        PDDocument document = PDDocument.load(new File(pdfPath));
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        for (int page = 0; page < document.getNumberOfPages(); ++page)
        {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
            ImageIOUtil.writeImage(bim, savePath , 300);
        }
        document.close();
    }


    /**
     * 二进制流转成pdf，或png
     *
     * @param urlStr  网络资源
     * @param imgPath
     * @param imgName
     * @return
     */
    public static String saveToImgByStr(String urlStr, String imgPath, String imgName,String newUrl) {

        String localPdf = "";
        FileOutputStream fos = null;
        InputStream in = null;

        try {

            URL url = new URL(urlStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            //设置超时时间 20秒
            httpURLConnection.setConnectTimeout(20 * 1000);
            //防止屏蔽程序抓取而返回403错误
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            in = httpURLConnection.getInputStream();
            File file = new File(imgPath, imgName);//可以是任何图片格式.jpg,.png等
            localPdf = newUrl + "/pdf/" + file.getName();
            fos = new FileOutputStream(file);

            byte[] b = new byte[1024];
            int nRead = 0;
            while ((nRead = in.read(b)) != -1) {
                fos.write(b, 0, nRead);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return localPdf;
    }

    public static void main(String[] args) {






    }


}
