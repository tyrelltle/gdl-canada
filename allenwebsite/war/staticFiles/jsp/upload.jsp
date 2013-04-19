<%@page import="java.io.*,com.allen.website.DBBean.Picture,javax.jdo.PersistenceManager,com.allen.website.PMF"%>
<%
	
	String contentType=request.getContentType();
	if(contentType!=null)
	{
		DataInputStream in=new DataInputStream(request.getInputStream());
		
		int formDataLength=request.getContentLength();
		byte dataBytes[]=new byte[formDataLength];
		int bytesRead=0;
		int totalBytesRead=0;
		
		while(totalBytesRead<formDataLength)
		{
			bytesRead=in.read(dataBytes,totalBytesRead,formDataLength);
			totalBytesRead+=bytesRead;
		}
		String file=new String(dataBytes);
		String saveFile = file.substring(file.indexOf("filename=\"")+10);
		saveFile = saveFile.substring(saveFile.lastIndexOf("\\")+ 1,saveFile.indexOf("\""));
		int lastIndex = contentType.lastIndexOf("=");
        String boundary = contentType.substring(lastIndex + 1,contentType.length());
        int pos;
        //extracting the index of file 
        pos = file.indexOf("filename=\"");
        pos = file.indexOf("\n", pos) + 1;
        pos = file.indexOf("\n", pos) + 1;
        pos = file.indexOf("\n", pos) + 1;
        int boundaryLocation = file.indexOf(boundary, pos) - 4;
        int startPos = ((file.substring(0, pos)).getBytes()).length;
        int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
        // creating a new file with the same name and writing the content in new file
        
        byte finalbyte[]=new byte[(endPos - startPos)+1];
         System.arraycopy(dataBytes,startPos,finalbyte,0,endPos - startPos);      
        
        //store to db
        if(finalbyte.length>0)
	     { Picture movie = new Picture();
	         movie.setTitle(saveFile);
	         movie.setImageType(request.getContentType());
	
	         // Set the movie's promotional image by passing in the bytes pulled
	         // from the image fetched via the URL Fetch service
	         movie.setImage(finalbyte);
	
	         //...
	
	         PersistenceManager pm = PMF.get().getPersistenceManager();
	         try {
	             // Store the image in App Engine's datastore
	             pm.makePersistent(movie);
	         } finally { 
	             pm.close();
	         }
        }
        response.sendRedirect("../../gallaryAdmin.jsp");
	}
%>