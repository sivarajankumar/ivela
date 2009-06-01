/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.services;

import br.ufc.ivela.commons.Constants;
import br.ufc.ivela.ejb.interfaces.QuestionRemote;
import javax.mail.BodyPart;
import javax.mail.internet.MimeMultipart;
import javax.ws.rs.ConsumeMime;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author emanuelle
 */
@Path("uploadFile")
public class UploadFileResource {
    
    private String path = Constants.AUDIO_UPLOAD_PATH;
    private QuestionRemote questionRemote;

    @Path("/upload/{id}")
    @POST
    @ConsumeMime("multipart/form-data")
    public void uploadFile(@PathParam("id") String id, MimeMultipart form) {
        //System.out.println("upload file:::::::::::::::::"+ id);
        //System.out.println("oilllllllllllllllllllllllllllllllllllllllllll"+form);
        try {
            int c = form.getCount();
            //System.out.println("oippppppppppppppppppppppppl"+c);
            if (c > 0) {
                BodyPart body = form.getBodyPart(id);
                
                //System.out.println(body);
                //System.out.println(body.getContentType());
                //System.out.println(body.getFileName());
                
                //questionRemote.addQuestionText(null, body.getInputStream());
               // String mimetype = new MimetypesFileTypeMap().getContentType(new java.io.File(path + body.getFileName()));
              //  unzip(new File(UPLOAD_PATH + body.getFileName()), new File(PROCESS_PATH));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
