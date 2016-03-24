package com.epam.jmp.taskmanager.service.rest.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.epam.jmp.taskmanager.exception.ServiceRestException;

@Path("/file")
public class FileServiceRest {

	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "D://Test/";

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader)
			{

		Response responce = null;
		String fileName = contentDispositionHeader.getFileName();
		String filePath = SERVER_UPLOAD_LOCATION_FOLDER + fileName;

		try {
			saveFile(fileInputStream, filePath);
		} catch (ServiceRestException e) {
			responce = Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
						  .entity("Fail to upload" + fileName).build();
		}

		String output = "File saved to server location : " + filePath;
		responce = Response.status(Response.Status.OK.getStatusCode()).entity(output).build();
		return responce;

	}

	
	@GET
	@Path("/download/{fileName}")
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public Response downloadFile(@PathParam("imageName") String fileName) {
		Response responce = null;
		File file = null;
		try {
			file = new File(SERVER_UPLOAD_LOCATION_FOLDER + fileName);
			ResponseBuilder responceBuilder = Response.ok((Object) file);
			responceBuilder.header("Content-Disposition",String.format("attachment; filename=%s", fileName));
			responce = responceBuilder.build();
		} catch (Exception e) {
			responce = Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
					  .entity("Fail to download:" + fileName).build();
		}
		return responce;
	}
	
	private void saveFile(InputStream uploadedInputStream, String serverLocation)
			throws ServiceRestException {

		try {
			OutputStream outpuStream = new FileOutputStream(new File(
					serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {
			throw new ServiceRestException(e);
		}

	}

}
