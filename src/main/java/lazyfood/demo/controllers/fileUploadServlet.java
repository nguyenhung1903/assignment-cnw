package lazyfood.demo.controllers;

import java.io.*;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;

class UploadDetail implements Serializable {
    private long fileSize;
    private String fileName, uploadStatus;
    private static final long serialVersionUID = 1L;

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }
}

@WebServlet(name = "fileUploadServlet", value = "/fileUploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

public class fileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String UPLOAD_DIR = "uploadedFiles";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<html><body>");
        out.print("<h3>File Upload:</h3>");
        out.print("<form method='post' action='fileUploadServlet' enctype='multipart/form-data'>");
        out.print("<input type='file' name='file'/>");
        out.print("<input type='submit' value='upload'/>");
        out.print("</form>");
        out.print("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applicationPath = getServletContext().getRealPath(""),
                uploadPath = applicationPath + File.separator + UPLOAD_DIR;
        File fileUploadDirectory = new File(uploadPath);
        if (!fileUploadDirectory.exists()) {
            fileUploadDirectory.mkdirs();
        }
        String fileName = "";
        UploadDetail details = null;
        ArrayList<UploadDetail> fileList = new ArrayList<UploadDetail>();
        for (Part part : request.getParts()) {
            fileName = extractFileName(part);
            details = new UploadDetail();
            details.setFileName(fileName);
            details.setFileSize(part.getSize() / 1024);
            try {
                part.write(uploadPath + File.separator + fileName);
                System.out.println(uploadPath + File.separator + fileName);
                details.setUploadStatus("Success");
            } catch (IOException ioObj) {
                details.setUploadStatus("Failure : " + ioObj.getMessage());
            }
            fileList.add(details);
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(fileList));
        out.flush();
    }

    private String extractFileName(Part part) {
        String fileName = "",
                contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return fileName;
    }

}
